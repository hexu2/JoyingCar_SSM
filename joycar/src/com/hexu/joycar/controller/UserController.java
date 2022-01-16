package com.hexu.joycar.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.Province;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.service.CarService;
import com.hexu.joycar.service.PhoneUserService;
import com.hexu.joycar.service.ProvinceService;
import com.hexu.joycar.service.RecordService;
import com.hexu.joycar.service.UserService;
import com.hexu.joycar.service.impl.AdminServiceImpl;
import com.hexu.joycar.util.CodeUtil;
import com.hexu.joycar.util.Constant;
import com.hexu.joycar.util.MakeCertPic;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.PropertiesUtil;
import com.hexu.joycar.util.UUIDGenerator;

/**
 * 普通用户控制器
 * @author hexu
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhoneUserService phoneUserService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private ProvinceService provinceService;
	
	/**
	 * 获得验证码（注册页）
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCodeForRegister.action", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String getCodePic(String phoneNumber,HttpSession session) throws IOException{
		
		System.out.println("phoneNumber-------------->:" + phoneNumber);
		
		PhoneUserWrapper phoneUserWrapperFromDB = phoneUserService.queryByPhoneNumber(phoneNumber);
		
		if(null == phoneUserWrapperFromDB){//手机号不存在
			return "";
		}else{
			String code = CodeUtil.getCode(Constant.CODE_LENGTH);
			//long getCodeTime = System.currentTimeMillis();
			Date getCodeTime = new Date();
			
			session.setAttribute("code", code);
			session.setAttribute("getCodeTime", getCodeTime);
			return "tel:  "+ phoneNumber +"  code:  " +code; 
		}
	}
	
	/**
	 * 获得验证码（登录页）
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCodePicForLogin.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String getCodePicForLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		//往客户端输出
		OutputStream os = response.getOutputStream();
		
		//1.生成验证码
		String serverCode = MakeCertPic.getCertPic(60, 20, os);

		//2.保存验证码对应的字符串
		request.getSession().setAttribute("serverCode", serverCode);
		log.info("serverCode---------------->:" + serverCode);
		return "hexu/userLogin";
	}
	
	/**
	 * 用户注册
	 * @param file
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addUser.action",method={RequestMethod.POST})
	public String addGame(@RequestParam("file")CommonsMultipartFile file,User user,Model model,HttpSession session)throws Exception{
		@SuppressWarnings("unused")
		Date getCodeTime= (Date) session.getAttribute("getCodeTime");
		//构造随机图片名1.jpg
		//xxxxx.jpg
		//String randomStr = gameService.randomPicName();
		String randomStr = UUIDGenerator.getUUID();
		String originalFilename = file.getOriginalFilename();
		String [] ofn = originalFilename.split("\\.");
		String userPictureNew = randomStr+"."+ofn[ofn.length-1];
		
		
		file.transferTo(new File(PropertiesUtil.getKey("uploadPath") + userPictureNew));
		
		//file.transferTo(new File(PropertiesUtil.getKey("uploadPath") + file.getOriginalFilename()));
		//修改userPicture
		user.setUserPicture(userPictureNew);
		userService.addUser(user);
		model.addAttribute("operator", Constant.USER_ADD);
		
		return "promote/success";
	}
	
	/**
	 * 根据账号查询用户信息
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryUserInfo.action",method={RequestMethod.POST,RequestMethod.GET})
	public String queryUserInfo(String userAccount,Model model)throws Exception{
		UserWrapper userWrapper = userService.queryByAccount(userAccount);
		model.addAttribute("resource", Constant.PICTURE_RESOURCE);
		model.addAttribute("userWrapper", userWrapper);
		
		return "user/queryCenter/userInfo";
	}
	
	/**
	 * user登录
	 * @param userAccount
	 * @param userPassword
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/userLogin.action", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody String userLogin(String userAccount, String userPassword, String code, HttpSession session){
		log.info("userLogin---------------->:userLogin");
		
		log.info("userAccount---------------->:" + userAccount);
		log.info("userPassword---------------->:" + userPassword);
		log.info("code---------------->:" + code);
		
		String codeFromUtil = (String) session.getAttribute("serverCode");
		log.info("codeFromUtil---------------->:" + codeFromUtil);
		UserWrapper userWrapperQueryByAccount = userService.queryByAccount(userAccount);
		
		UserWrapper userWrapper = userService.userLogin(userAccount, userPassword);
				
		if(userWrapperQueryByAccount == null){
			//1,账号不存在
			log.info("return 1---------------->:账号不存在");
			return "1";
		}
		
		if(userWrapper == null){
			//2,密码错误
			log.info("return 2---------------->:密码错误");
			return "2";
		}
		
		String accountStatus  = userWrapper.getAccountStatus();
		
		if(!"0".equalsIgnoreCase(accountStatus)){
			//3,账号已注销
			return "3";
		}
		
		if(!code.equalsIgnoreCase(codeFromUtil)){
			//4,验证码错误
			log.info("return 3---------------->:验证码错误");
			return "4";
		}
		
		//5，登陆成功resource
		log.info("return 5---------------->:登陆成功");
		session.setAttribute("resource", Constant.PICTURE_RESOURCE);
		session.setAttribute("userWrapper", userWrapper);
		return "5";
	}
	
	/**
	 * 根据条件查询所有车辆
	 * @param pageNo
	 * @param userAccount
	 * @param checkStatus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/queryAllCarByconditions.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryAllCarByconditions(int pageNo, String userAccount, String checkStatus,String carType, HttpSession session){
		log.info("queryAllCarByconditions---------------->:queryAllCarByconditions");
		
		log.info("userAccount---------------->:" + userAccount);
		
		log.info("checkStatus---------------->:" + checkStatus);
		
		log.info("carType---------------->:" + carType);
		int pageSize = Constant.PAGESIZE;
		PageModel<CarWrapper> pageModel = carService.queryAllByConditions(pageNo, pageSize, userAccount,carType, checkStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("userAccount", userAccount);
		session.setAttribute("checkStatus", checkStatus);
		session.setAttribute("carType", carType);
		
		return "user/setCenter/car";
	}
	
	/**
	 * 根据条件分页查询所有违章记录
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws JoyCarException
	 */
	@RequestMapping(value = "/queryAllRecordByconditions.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryAllRecordByconditions(int pageNo,String userAccount, String recordNumber,String carNumber, String recordStatus, HttpSession session){
		log.info("queryAllRecordByconditions---------------->:queryAllRecordByconditions");
		
		log.info("userAccount---------------->:" + userAccount);
		
		log.info("recordNumber---------------->:" + recordNumber);
		
		log.info("carNumber---------------->:" + carNumber);
		
		log.info("recordStatus---------------->:" + recordStatus);
		
		int pageSize = Constant.PAGESIZE;
		PageModel<RecordWrapper> pageModel =  recordService.queryAllByConditions(pageNo, pageSize, userAccount, recordNumber, carNumber, recordStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("userAccount", userAccount);
		session.setAttribute("recordNumber", recordNumber);
		session.setAttribute("carNumber", carNumber);
		session.setAttribute("recordStatus", recordStatus);
		
		if(Constant.RECORD_STATUS_IS_YES_PAY.equalsIgnoreCase(recordStatus)){//已缴费0
			log.info("去已缴费页面---------------->:yesPay.jsp");
			return "user/payCenter/yesPay";
		}else if(Constant.RECORD_STATUS_IS_NO_PAY.equalsIgnoreCase(recordStatus)){//未缴费1
			log.info("去待缴费页面---------------->:yesPay.jsp");
			return "user/payCenter/noPay";
		}else{
			return "user/queryCenter/record";
		}
	}
	
	/**
	 * 根据条件分页查询消费记录
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws JoyCarException
	 */
	@RequestMapping(value = "/queryConsumeRecord.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryConsumeRecord(int pageNo,String userAccount, String recordNumber,String carNumber, String recordStatus, HttpSession session){
		log.info("queryConsumeRecord---------------->:queryConsumeRecord");
		
		log.info("userAccount---------------->:" + userAccount);
		
		log.info("recordNumber---------------->:" + recordNumber);
		
		log.info("carNumber---------------->:" + carNumber);
		
		log.info("recordStatus---------------->:" + recordStatus);
		
		int pageSize = Constant.PAGESIZE;
		PageModel<RecordWrapper> pageModel =  recordService.queryAllByConditions(pageNo, pageSize, userAccount, recordNumber, carNumber, recordStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("userAccount", userAccount);
		session.setAttribute("recordNumber", recordNumber);
		session.setAttribute("carNumber", carNumber);
		session.setAttribute("recordStatus", recordStatus);
		
		return "user/queryCenter/consumeRecord";
	}
	
	/**
	 * 根据recorId和userAccount缴纳罚金
	 * @param recordId
	 * @param userAccount
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/payRecord.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String payRecord(int recordId,String userAccount, Model model){
		log.info("payRecord---------------->:payRecord");
		
		log.info("recordId---------------->:" + recordId);
		
		log.info("userAccount---------------->:" + userAccount);
		
		userService.payRecord(recordId, userAccount);

		model.addAttribute("operator", Constant.PAY_SUCCESS);
		
		return "promote/success";
		
	}

	/**
	 * 添加车辆之前查询省份
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryBeforAddCar.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryBeforAddCar(Model model){
		log.info("String queryBeforAddCar,---------------->:queryBeforAddCar");
		
		List<Province> provinceList = null;
		
		provinceList = provinceService.queryAllProvice();

		log.info("provinceList---------------->:" + provinceList);
		model.addAttribute("provinceList", provinceList);
		
		return "user/setCenter/addCar";
		
	}
	

	/**
	 * 添加车辆
	 * @param userAccount
	 * @param carType
	 * @param carBodyNumber
	 * @param carNumberA
	 * @param carNumberB
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCar.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String addCar(String userAccount,String carType,String carBodyNumber,String carNumberA,String carNumberB, Model model){
		log.info("String queryBeforAddCar,---------------->:addCar");
		
		log.info("userAccount---------------->:" + userAccount);
		log.info("carType---------------->:" + carType);	
		log.info("carBodyNumber---------------->:" + carBodyNumber);
		
		
		log.info("carNumberA---------------->:" + carNumberA);
		log.info("carNumberB---------------->:" + carNumberB);
		
		carService.addCar(userAccount, carType, carBodyNumber, carNumberA, carNumberB);

		model.addAttribute("operator", Constant.CAR_ADD);
		
		return "promote/success";
		
	}
	
	/**
	 * 根据id查询为了修改用户状态
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtStatus.action",method={RequestMethod.GET})
	public String queryForEidtStatus(String userAccount,Model model)throws Exception{
		//AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
		
		UserWrapper userWrapperFromDB = userService.queryByAccount(userAccount);
		
		model.addAttribute("userWrapperFromDB",userWrapperFromDB);
	
		return "user/setCenter/userStatusEdit";
	}
	
	/**
	 * 修改user用户状态
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editStatus.action",method={RequestMethod.POST})
	public String editStatus(User user, Model model)throws Exception{
		log.info("editStatus---------------->:editStatus");
		userService.editUser(user);
		model.addAttribute("operator", Constant.USER_STATUS_EDIT);
		
		return "promote/success";
	}
	
	/**
	 * 根据id查询为了修改用户密码
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtPwd.action",method={RequestMethod.GET})
	public String queryForEidtPwd(String userAccount,Model model)throws Exception{
		//AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
		
		UserWrapper userWrapperFromDB = userService.queryByAccount(userAccount);
		
		model.addAttribute("userWrapperFromDB",userWrapperFromDB);
	
		return "user/setCenter/userPwdEdit";
	}
	
	/**
	 * 修改user用户密码
	 * @param userAccount
	 * @param userPassword
	 * @param accountStatus
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editPwd.action",method={RequestMethod.POST})
	public String editPwd(User user, Model model)throws Exception{
		log.info("editStatus---------------->:editStatus");
		userService.editUser(user);
		model.addAttribute("operator", Constant.USER_EDIT_PWD);
		
		return "promote/success";
	}
	
	/**
	 * 根据id查询为了修改用户头像
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtUserPicture.action",method={RequestMethod.GET})
	public String queryForEidtUserPicture(String userAccount,Model model)throws Exception{
		//AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
		
		UserWrapper userWrapperFromDB = userService.queryByAccount(userAccount);
		
		model.addAttribute("userWrapperFromDB",userWrapperFromDB);
	
		return "user/setCenter/userPictureEdit";
	}
	
	
	/**
	 * 修改user用户头像
	 * @param adminAccount
	 * @param adminPassword
	 * @param accountStatus
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editPicture.action",method={RequestMethod.POST})
	public String editPicture(@RequestParam("file")CommonsMultipartFile file,User user,Model model)throws Exception{
		log.info("editPicture---------------->:editPicture");
		
		
		//构造随机图片名1.jpg
		//xxxxx.jpg
		//String randomStr = gameService.randomPicName();
		String randomStr = UUIDGenerator.getUUID();
		String originalFilename = file.getOriginalFilename();
		String [] ofn = originalFilename.split("\\.");
		String userPictureNew = randomStr+"."+ofn[ofn.length-1];
		
		
		file.transferTo(new File(PropertiesUtil.getKey("uploadPath") + userPictureNew));
		
		//file.transferTo(new File(PropertiesUtil.getKey("uploadPath") + file.getOriginalFilename()));
		//修改userPicture
		user.setUserPicture(userPictureNew);
		userService.editUser(user);
		model.addAttribute("operator", Constant.USER_EDIT_PICTURE);
		return "promote/success";
	}
	
	/**
	 * 系统退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/quit.action",method={RequestMethod.GET})
	public String quit(HttpSession session,Model model){
		log.info("quit---------------->:quit");
		session.removeAttribute("userWrapper");
		model.addAttribute("operator", Constant.SYSTEM_QUIT);
		return "promote/success";
	}

	
	
	/**
	 * 根据id查询为了话费充值
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForRecharge.action",method={RequestMethod.GET})
	public String queryForRecharge(String userAccount,Model model)throws Exception{
		//AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
		
		UserWrapper userWrapperFromDB = userService.queryByAccount(userAccount);
		
		model.addAttribute("userWrapperFromDB",userWrapperFromDB);
	
		return "user/queryCenter/recharge";
	}
	
	/**
	 * 话费充值
	 * @param cardNumber
	 * @param cardPassword
	 * @param userAccount
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recharge.action",method={RequestMethod.GET,RequestMethod.POST})
	public String recharge(String cardNumber,String cardPassword,String userAccount,HttpSession session,Model model){
		log.info("*****************recharge********************");
		log.info("cardNumber---------------->:" + cardNumber);
		log.info("cardPassword---------------->:" + cardPassword);
		log.info("userAccount---------------->:" + userAccount);
		userService.recharge(cardNumber, cardPassword, userAccount);
		model.addAttribute("operator", Constant.RECHARGE_SUCCESS);
		return "promote/success";
	}
}
