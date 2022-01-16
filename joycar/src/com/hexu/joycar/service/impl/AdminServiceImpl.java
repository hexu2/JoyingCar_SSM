package com.hexu.joycar.service.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.ActionMapper;
import com.hexu.joycar.mapper.AdminMapper;
import com.hexu.joycar.mapper.CarMapper;
import com.hexu.joycar.mapper.LocationMapper;
import com.hexu.joycar.mapper.PhoneUserMapper;
import com.hexu.joycar.mapper.RechargeCardMapper;
import com.hexu.joycar.mapper.RecordMapper;
import com.hexu.joycar.mapper.UserMapper;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.PhoneUser;
import com.hexu.joycar.pojo.Record;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.service.AdminService;
import com.hexu.joycar.util.Constant;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.RandomUtil;
import com.hexu.joycar.util.StringUtil;

/**
 * 管理员业务逻辑接口层实现类
 * @author hexu
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	private Logger log = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private ActionMapper actionMapper;
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	private RecordMapper recordMapper;

	@Autowired
	private PhoneUserMapper phoneUserMapper;
	
	@Autowired
	private RechargeCardMapper rechargeCardMapper;
		
	/**
	 * 管理员登陆
	 * @param adminAccount 管理员账号
	 * @param adminPassword 管理员密码
	 * @return AdminWrapper
	 * @throws JoyCarException
	 */
	@Override
	public AdminWrapper adminLogin(String adminAccount, String adminPassword)
			throws JoyCarException {
		//1.基础数据校验
		if (StringUtil.isEmpty(adminAccount)) {
			// 帐号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(adminPassword)) {
			// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		}
		
		//2.组装参数admin
		Admin admin = new Admin();
		admin.setAdminAccount(adminAccount);
		admin.setAdminPassword(adminPassword);
		
		//3.调用adminMapper
		AdminWrapper adminWrapper = null;
		
		try {
			adminWrapper = adminMapper.queryByAccountAndPassword(admin);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//4.返回结果
		return adminWrapper;
	}

	/**
	 * 根据账号查询管理员用户
	 * @param adminAccount
	 * @return AdminWrapper
	 * @throws Exception
	 */
	@Override
	public AdminWrapper queryByAccount(String adminAccount)
			throws JoyCarException {
		//1.基础数据校验
		if (StringUtil.isEmpty(adminAccount)) {
			// 帐号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		}
		
		//2.调用adminMapper
		AdminWrapper adminWrapper = null;
		
		try {
			adminWrapper = adminMapper.queryByAccount(adminAccount);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//4.返回结果
		return adminWrapper;
	}

	/**
	 * 根据条件分页查询管理员用户
	 * @param pageNo 页号
	 * @param pageSize 页面大小 
	 * @param adminAccount 账号 （模糊查询）
	 * @param accountStatus账号状态（完全匹配）
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public PageModel<AdminWrapper> queryAllByConditions(int pageNo, int pageSize,
			String adminAccount, String accountStatus) throws JoyCarException {
		//1,拼接adminAccount
		if(adminAccount != null){
			adminAccount = "%" + adminAccount.trim() +"%";
		}
		
		if("".equalsIgnoreCase(adminAccount)){
			adminAccount = null;
		}
		
		//accountStatus
		if(accountStatus != null){
			accountStatus = accountStatus.trim();
		}
		
		if("".equalsIgnoreCase(accountStatus)){
			accountStatus = null;
		}
		
		
		
		
		//2,构造查询条件
		//pageModel
		PageModel<AdminWrapper> pageModel = new PageModel<AdminWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminAccount", adminAccount);
		map.put("accountStatus", accountStatus);
		map.put("pageModel", pageModel);
		
		
		List<AdminWrapper> adminWrappers = null;
		try {
			adminWrappers = adminMapper.queryAllByConditions(map);
			pageModel.setDataList(adminWrappers);
			pageModel.setCnt(adminMapper.getCntByConditions(map));
			if(0 == adminMapper.getCntByConditions(map)){
				pageModel.setPageNo2(0);
			}
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		
		return pageModel;
	}

	/**
	 * 根据id删除admin用户
	 * @param adminId
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void deleteById(int adminId) throws JoyCarException {
		
		//基础校验
		if(StringUtil.isEmpty(String.valueOf(adminId))){
			throw new JoyCarException(ErrorCode.ADMINID_IS_EMPTY_ERROR,
					ErrorCode.ADMINID_IS_EMPTY_ERROR_MSG);
		}
		
		try {
			adminMapper.delete(adminId);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
	}

	/**
	 * 添加admin用户
	 * @param adminAccount
	 * @param adminPassword
	 * @param accountStatusString
	 */
	@Transactional
	@Override
	public void addAdmin(String adminAccount, String adminPassword,
			String accountStatus) throws JoyCarException {
		// 1.基础数据校验
		if (StringUtil.isEmpty(adminAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(adminPassword)) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(accountStatus)) {// 用户状态为空
			throw new JoyCarException(ErrorCode.ACCOUNT_STATUES,
					ErrorCode.ACCOUNT_STATUES_IS_EMPTY_ERROR_MSG);
		}
		
		AdminWrapper adminWrapperFromDB = null;
		try {
			adminWrapperFromDB = adminMapper.queryByAccount(adminAccount);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		if(null != adminWrapperFromDB){//账号不重复
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EXIT_ERROR,
					ErrorCode.USERACCOUNT_IS_EXIT_ERROR_MSG);
		}
		
		Admin admin = new Admin();
		admin.setAccountStatus(accountStatus);
		admin.setAdminAccount(adminAccount);
		admin.setAdminPassword(adminPassword);
		
		try {
			adminMapper.add(admin);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}

	/**
	 * 修改用户根据id
	 * @param admin
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void editAdmin(Admin admin) throws JoyCarException {
		// 1.基础数据校验
		if (StringUtil.isEmpty(admin.getAdminAccount())) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(admin.getAdminPassword())) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(admin.getAccountStatus())) {// 用户状态为空
			throw new JoyCarException(ErrorCode.ACCOUNT_STATUES,
					ErrorCode.ACCOUNT_STATUES_IS_EMPTY_ERROR_MSG);
		}
		
		try {
			adminMapper.update(admin);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}


	/**
	 * 模拟生成违章记录
	 * @param userAccount 账号
	 * @param carNumber 车牌号
	 * @param recordNumber 生成条数
	 * @throws JoyCarException
	 */
	@Override
	public void createRecord(String userAccount, String carNumber,
			int recordNumber) throws JoyCarException {
		System.out.println("userAccount----------->:" + userAccount);
		System.out.println("carNumber----------->:" + carNumber);
		System.out.println("recordNumber----------->:" + recordNumber);
		
		// 1.基础数据校验
		if (StringUtil.isEmpty(userAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(carNumber)) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		}
		//1.查询用户是否存在
		UserWrapper userWrapperFromDB= null;
		try {
			userWrapperFromDB = userMapper.queryByAccount(userAccount);
		} catch (Exception e1) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		if(userWrapperFromDB == null){
			//账号不存在
			throw new JoyCarException(ErrorCode.USER_ACCOUNT_IS_NOT_EXIST_ERROR,
					ErrorCode.USER_ACCOUNT_IS_NOT_EXIST_ERROR_MSG);
		}
		
		//2,判断该用户是否有该车辆
		List<CarWrapper> carWrapperList = null;
		
		try {
			carWrapperList = carMapper.queryAllByUserAccount(userAccount);
		} catch (Exception e1) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		boolean flag = false;
		for (CarWrapper carWrapper : carWrapperList) {
			if (carWrapper.getCarNumber().equals(carNumber)) {
				flag = true;
			}
		}
		
		if(flag == false){
			throw new JoyCarException(ErrorCode.USER_HAVE_NOT_THE_CAR_ERROR,
					ErrorCode.USER_HAVE_NOT_THE_CAR_ERROR_MSG);
		}

		
		Record record = new Record();
		record.setUserAccount(userAccount);
		record.setCarNumber(carNumber);
		//得到action记录总数
		int actionCnt = 0;
		try {
			actionCnt = actionMapper.getCnt();
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//得到location记录总数
		int locationCnt = 0;
		try {
			locationCnt = locationMapper.getCnt();
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//初始化record_status = "1";即未缴费
		record.setRecordStatus(Constant.RECORD_STATUS_IS_NO_PAY);
		
		//6.根据recordNumber循环插入数据库中{step2 and step3 包含在循环里}
		//效率比较低，优化：调用存储过程
		for(int i = recordNumber ;i  > 0;i--){
			//2.随机生成一个行为id
			record.setActionId(RandomUtil.getRandom(actionCnt));
			//3.随机生成一个记录id
			record.setLocationId(RandomUtil.getRandom(locationCnt));
			
			try {
				recordMapper.addRecord(record);
			} catch (Exception e) {
				throw new JoyCarException(ErrorCode.DATABASE_ERROR,
						ErrorCode.DATABASE_ERROR_MSG);
			}
			
		}
	}


	/**
	 * 导入手机用户
	 * config/joycar/phoneUser.txt
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void importPhoneUser() throws JoyCarException {
		PhoneUser phoneUser = new PhoneUser();
		
		BufferedReader br = null;
		
		
		//br = new BufferedReader(new File("config/joycar/phoneUser.txt"));
		//importPhoneUser.class.getClassLoader().getResourceAsStream(""config/joycar/phoneUser.txt"")
		try {
			br = new BufferedReader(new FileReader(new File("E:\\userPicture\\phoneUser\\phoneUser.txt")));
			String str = br.readLine();
			String[] strArray = null;
			PhoneUserWrapper phoneNumberFromDB = null;
			while(!StringUtil.isEmpty(str)){
				strArray = str.split("\\|");
				phoneUser.setPhoneNumber(strArray[0]);
				
				phoneUser.setPhoneUserName(strArray[1]);
				
				phoneUser.setPhoneUserSex(strArray[2]);
				
				phoneUser.setPhoneUserAge(Integer.valueOf(strArray[3]));

				phoneUser.setIdNumber(strArray[4]);
				phoneUser.setPhoneFee(Double.valueOf(strArray[5]));
				
				phoneUser.setPhoneStatus(strArray[6]);
				
				phoneNumberFromDB = phoneUserMapper.queryByPhoneNumber(strArray[0]);
				System.out.println("*************PhoneNumber*************:" + strArray[0]);
				if(phoneNumberFromDB == null){
					phoneUserMapper.addPhoneUser(phoneUser);
				}
				str = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 批量生成话费充值卡
	 * @param cardNumbers
	 * @param cardValue
	 * @param startTime
	 * @param endTime
	 * @throws JoyCarException
	 */
	@Override
	public void createRechargeCard(int cardNumbers, int cardValue,
			Date startTime, Date endTime) throws JoyCarException {
		//基础校验
		// 1.判断开始时间是否>=nowTime+7
		if (startTime.getTime() - System.currentTimeMillis() < 604800000) {
			throw new JoyCarException(ErrorCode.START_TIME_IS_ERROR,
					ErrorCode.START_TIME_IS_ERROR_MSG);
		} else if (endTime.getTime() < startTime.getTime()) {
			throw new JoyCarException(ErrorCode.END_TIME_IS_ERROR,
					ErrorCode.END_TIME_IS_ERROR_MSG);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumbers", cardNumbers);
		map.put("cardValue", cardValue);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
 		
		try {
			rechargeCardMapper.addRechargeCards(map);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}
	
	
	
	
}
