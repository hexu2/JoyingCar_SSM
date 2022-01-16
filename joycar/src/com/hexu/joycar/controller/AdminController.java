package com.hexu.joycar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.service.AdminService;
import com.hexu.joycar.service.CarService;
import com.hexu.joycar.service.PhoneUserService;
import com.hexu.joycar.service.RechargeCardService;
import com.hexu.joycar.service.RecordService;
import com.hexu.joycar.service.UserService;
import com.hexu.joycar.service.impl.AdminServiceImpl;
import com.hexu.joycar.util.Constant;
import com.hexu.joycar.util.MakeCertPic;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.PropertiesUtil;

/**
 * 管理员控制器
 * @author hexu
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private Logger log = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PhoneUserService phoneUserService;

	@Autowired
	private RechargeCardService rechargeCardService;
	
	/**
	 * 获得验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCodePic.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String getCodePic(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		//往客户端输出
		OutputStream os = response.getOutputStream();
		
		//1.生成验证码
		String serverCode = MakeCertPic.getCertPic(60, 20, os);

		//2.保存验证码对应的字符串
		request.getSession().setAttribute("serverCode", serverCode);
		log.info("serverCode---------------->:" + serverCode);
		return "adminLogin";
	}
	
	/**
	 * 管理员登陆
	 * @param adminAccount
	 * @param adminPassword
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/adminLogin.action", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody String adminLogin(String adminAccount, String adminPassword, String code, HttpSession session){
		
		log.info("adminAccount---------------->:" + adminAccount);
		log.info("adminPassword---------------->:" + adminPassword);
		log.info("code---------------->:" + code);
		
		String codeFromUtil = (String) session.getAttribute("serverCode");
		log.info("codeFromUtil---------------->:" + codeFromUtil);
		AdminWrapper adminWrapperQueryByAccount = adminService.queryByAccount(adminAccount);
		
		AdminWrapper adminWrapper = adminService.adminLogin(adminAccount, adminPassword);
				
		if(adminWrapperQueryByAccount == null){
			//1,账号不存在
			log.info("return 1---------------->:账号不存在");
			return "1";
		}
		
		if(adminWrapper == null){
			//2,密码错误
			log.info("return 2---------------->:密码错误");
			return "2";
		}
		
		String accountStatus  = adminWrapper.getAccountStatus();
		
		if(!"0".equalsIgnoreCase(accountStatus)){
			//3,账号已注销
			return "3";
		}
		
		if(!code.equalsIgnoreCase(codeFromUtil)){
			//4,验证码错误
			log.info("return 3---------------->:验证码错误");
			return "4";
		}
		
		//5，登陆成功
		log.info("return 5---------------->:登陆成功");
		session.setAttribute("adminWrapper", adminWrapper);
		return "5";
	}
	
	/**
	 * 根据条件分页查询管理员用户
	 * @param pageNo 页号
	 * @param pageSize 页面大小 
	 * @param adminAccount 账号 （模糊查询）
	 * @param accountStatus账号状态（完全匹配）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryAllByConditons.action",method={RequestMethod.POST,RequestMethod.GET})
	public String queryAllByConditons(int pageNo, String adminAccount, String accountStatus, HttpSession session)throws Exception{
		
		int pageSize = Constant.PAGESIZE;
		
		PageModel<AdminWrapper> pageModel = adminService.queryAllByConditions(pageNo, pageSize, adminAccount, accountStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("adminAccount", adminAccount);
		session.setAttribute("accountStatus", accountStatus);
		
		
		return "admin/userManage/adminUser/adminUser";
		
	}
	
	/**
	 * 根据id删除admin用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteById.action",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteById(int adminId,Model model)throws Exception{
		
		adminService.deleteById(adminId);

		model.addAttribute("operator", Constant.ADMIN_DELETE);
		
		return "promote/success";
	}
	/**
	 * 新增admin用户
	 * @param adminAccount账号
	 * @param adminPassword密码
	 * @param accountStatus状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addAdmin.action",method={RequestMethod.POST,RequestMethod.GET})
	public String addAdmin(String adminAccount, String adminPassword, String accountStatus,Model model)throws Exception{
		
		adminService.addAdmin(adminAccount, adminPassword, accountStatus);

		model.addAttribute("operator", Constant.ADMIN_ADD);
		
		return "promote/success";
	}
	
	/**
	 * 根据id查询为了修改用户
	 * @param adminAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidt.action",method={RequestMethod.GET})
	public String queryForEidt(String adminAccount,Model model)throws Exception{
		AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
	
		model.addAttribute("adminWrapperFromDB",adminWrapperFromDB);
	
		return "admin/userManage/adminUser/adminUserEdit";
	}
	
	/**
	 * 根据id查询为了修改用户
	 * @param adminId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtPwd.action",method={RequestMethod.GET})
	public String queryForEidtPwd(String adminAccount,Model model)throws Exception{
		AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
	
		model.addAttribute("adminWrapperFromDB",adminWrapperFromDB);
	
		return "admin/systemManage/adminUserPwdEdit";
	}
	
	/**
	 * 修改admin用户
	 * @param adminAccount
	 * @param adminPassword
	 * @param accountStatus
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editAdmin.action",method={RequestMethod.POST})
	public String editAdmin(Admin admin, Model model)throws Exception{
		adminService.editAdmin(admin);
		model.addAttribute("operator", Constant.ADMIN_EDIT);
		
		return "promote/success";
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
		return "admin/recordManage/record";
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

		return "admin/recordManage/car";
	}
	
	/**
	 * 模拟生成违章记录
	 * @param userAccount
	 * @param carNumber
	 * @param recordNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createRecord.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String createRecord(String userAccount, String carNumber,int recordNumber, Model model){
		log.info("createRecord---------------->:createRecord");
		
		log.info("userAccount---------------->:" + userAccount);
		
		log.info("carNumber---------------->:" + carNumber);
		
		log.info("recordNumber---------------->:" + recordNumber);
		
		adminService.createRecord(userAccount, carNumber, recordNumber);
		
		model.addAttribute("operator", Constant.ADD_RECORD);
		
		return "promote/success";
	}
	
	/**
	 * 系统退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/quit.action",method={RequestMethod.POST,RequestMethod.GET})
	public String quit(HttpSession session,Model model){
		log.info("quit---------------->:quit");
		session.removeAttribute("adminWrapper");
		model.addAttribute("operator", Constant.ADMIN_SYSTEM_QUIT);
		return "promote/success";
	}
	
	/**
	 * 多条件分页查询所有user用户
	 * @param pageNo
	 * @param userAccount
	 * @param phoneNumber
	 * @param accountStatus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/queryAllUserByconditions.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryAllUserByconditions(int pageNo,String userAccount, String phoneNumber,
			String accountStatus, HttpSession session){
		log.info("*********************************queryAllUserByconditions****************************************");
		
		log.info("userAccount---------------->:" + userAccount);
		
		log.info("phoneNumber---------------->:" + phoneNumber);
		
		log.info("accountStatus---------------->:" + accountStatus);
		
		int pageSize = Constant.PAGESIZE;
		PageModel<UserWrapper> pageModel =  userService.queryAllByConditions(pageNo, pageSize, userAccount, phoneNumber, accountStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("userAccount", userAccount);
		session.setAttribute("phoneNumber", phoneNumber);
		session.setAttribute("accountStatus", accountStatus);
		session.setAttribute("resource", Constant.PICTURE_RESOURCE);
		return "admin/userManage/user/user";
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
		UserWrapper userWrapperForUserInfo = userService.queryByAccount(userAccount);
		
		List<CarWrapper> carWrapperListForUserInfo = carService.queryByUserAccount(userAccount);
		
		model.addAttribute("resource", Constant.PICTURE_RESOURCE);
		model.addAttribute("userWrapperForUserInfo", userWrapperForUserInfo);
		model.addAttribute("carWrapperListForUserInfo", carWrapperListForUserInfo);
		
		return "admin/userManage/user/userInfo";
	}
	
	/**
	 * 根据id查询为了修改用户状态
	 * @param userAccount
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtStatus.action",method={RequestMethod.POST,RequestMethod.GET})
	public String queryForEidtStatus(String userAccount,Model model)throws Exception{
		//AdminWrapper adminWrapperFromDB = adminService.queryByAccount(adminAccount);
		
		UserWrapper userWrapperFromDB = userService.queryByAccount(userAccount);
		
		model.addAttribute("userWrapperFromDB",userWrapperFromDB);
	
		return "admin/userManage/user/userEdit";
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
		model.addAttribute("operator", Constant.ADMIN_EDIT_USER_STATUS);
		
		return "promote/success";
	}
	
	/**
	 * 多条件分页查询所有user用户
	 * @param pageNo
	 * @param userAccount
	 * @param phoneNumber
	 * @param accountStatus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/queryAllPhoneUserByconditions.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryAllPhoneUserByconditions(int pageNo, String phoneNumber, String phoneUserName,
			String phoneUserSex, String phoneStatus, HttpSession session){
		log.info("*********************************queryAllPhoneUserByconditions****************************************");
		
		log.info("phoneNumber---------------->:" + phoneNumber);
		
		log.info("phoneUserName---------------->:" + phoneUserName);
		
		log.info("phoneUserSex---------------->:" + phoneUserSex);
		
		log.info("phoneStatus---------------->:" + phoneStatus);
		
		int pageSize = Constant.PAGESIZE;
		PageModel<PhoneUserWrapper> pageModel =  phoneUserService.queryAllByConditions(pageNo, pageSize, phoneNumber, phoneUserName, phoneUserSex, phoneStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("phoneNumber", phoneNumber);
		session.setAttribute("phoneUserName", phoneUserName);
		session.setAttribute("phoneUserSex", phoneUserSex);
		session.setAttribute("phoneStatus", phoneStatus);
		
		return "admin/userManage/phoneUser/phoneUser";
	}
	
	/**
	 * 导入手机用户
	 * config/joycar/phoneUser.txt
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/importPhoneUser.action")
	public String importPhoneUser(Model model)throws Exception{
		log.info("*********************************importPhoneUser****************************************");
		
		
		adminService.importPhoneUser();
	
		model.addAttribute("operator", Constant.IMPORT_PHONEUSER_SUCCESS);
		
		return "promote/success";
	}
	
	
	/**
	 * 根据phoneNumber查询为了修改手机用户用户状态
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryForEidtPhoneUserStatus.action",method={RequestMethod.POST,RequestMethod.GET})
	public String queryForEidtPhoneUserStatus(String phoneNumber,Model model)throws Exception{
		log.info("*********************************queryForEidtPhoneUserStatus****************************************");
		log.info("phoneNumber---------------->:" + phoneNumber);
		
		PhoneUserWrapper phoneUserWrapperFromDB = phoneUserService.queryByPhoneNumber(phoneNumber);
		
		model.addAttribute("phoneUserWrapperFromDB",phoneUserWrapperFromDB);	
		return "admin/userManage/phoneUser/phoneUserEdit";
	}
	
	/**
	 * 修改手机状态
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editPhoneUserStatus.action",method={RequestMethod.POST,RequestMethod.GET})
	public String editPhoneUserStatus(String phoneNumber, String phoneStatus,Model model)throws Exception{
		log.info("*********************************importPhoneUser****************************************");
		log.info("phoneNumber---------------->:" + phoneNumber);
		log.info("phoneStatus---------------->:" + phoneStatus);
		
		phoneUserService.updateStatus(phoneNumber, phoneStatus);
		
		model.addAttribute("operator", Constant.UPDATE_PHONEUSER_STATUS_SUCCESS);
		
		return "promote/success";
	}
	
	
	/**
	 * 根据条件分页查询所有话费充值卡
	 * @param pageNo
	 * @param cardNumber
	 * @param cardStatus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/queryAllReChangeCardByconditions.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String queryAllReChangeCardByconditions(int pageNo, String cardNumber, String cardStatus,HttpSession session){
		log.info("*********************************queryAllReChangeCardByconditions****************************************");
		
		log.info("cardNumber---------------->:" + cardNumber);
		
		log.info("cardStatus---------------->:" + cardStatus);
		
		int pageSize = Constant.PAGESIZE;
		PageModel<RechargeCardWrapper> pageModel = rechargeCardService.queryAllReChageCardCardByCondition(pageNo, pageSize, cardNumber, cardStatus);
		session.setAttribute("pageModel", pageModel);
		session.setAttribute("cardNumber", cardNumber);
		session.setAttribute("cardStatus", cardStatus);

		return "admin/rechargeCardManage/rechargeCard";
	}
	
	/**
	 * 根据条件导出充值卡
	 * @param cardNumber
	 * @param cardStatus
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportReChargeCard.action", method = { RequestMethod.POST,RequestMethod.GET })
	public String exportReChargeCard(String cardNumber, String cardStatus,Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		log.info("*********************************exportReChargeCard****************************************");
		
		log.info("cardNumber---------------->:" + cardNumber);
		
		log.info("cardStatus---------------->:" + cardStatus);
		//得到服务端的文件名
		String fileName = rechargeCardService.exprotSecretCard(cardNumber, cardStatus);
		//设置下载的文件类型
		response.setContentType("multipart/form-data");
		//设置头文件
		response.setHeader("Content-Disposition","attachment;fileName="+fileName);
		//获取源文件
		FileInputStream fis=new FileInputStream(new File(PropertiesUtil.getKey("uploadPath")+fileName));
		//输出
		ServletOutputStream sos=response.getOutputStream();
		int count=0;
		byte [] bytes =new byte[1024];
		while((count =fis.read(bytes))!=-1){
			sos.write(bytes,0,count);
		}
		sos.close();
		sos.flush();
		fis.close();
		model.addAttribute("operator", Constant.EXPORT_SECRET_CARDS);
		
		return "promote/success";
	}
	
	
	/**
	 * 批量生成充值卡
	 * @param cardNumbers
	 * @param cardValue
	 * @param startTime
	 * @param endTime
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addRechargeCard.action",method={RequestMethod.POST,RequestMethod.GET})
	public String addRechargeCard(int cardNumbers, int cardValue,
			Date startTime, Date endTime,Model model)throws Exception{
		
		adminService.createRechargeCard(cardNumbers, cardValue, startTime, endTime);

		model.addAttribute("operator", Constant.CREATE_RECHARGE_CARD_SUCCESS);
		
		return "promote/success";
	}
}
