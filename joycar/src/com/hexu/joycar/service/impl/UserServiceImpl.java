package com.hexu.joycar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.PhoneUserMapper;
import com.hexu.joycar.mapper.RechargeCardMapper;
import com.hexu.joycar.mapper.RecordMapper;
import com.hexu.joycar.mapper.UserMapper;
import com.hexu.joycar.pojo.PhoneUser;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.service.UserService;
import com.hexu.joycar.util.Constant;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.StringUtil;

/**
 * 用户业务逻辑接口实现类
 * @author hexu
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PhoneUserMapper phoneUserMapper;
	
	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired
	private RechargeCardMapper rechargeCardMapper;

	/**
	 * 添加user用户
	 * @param user
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void addUser(User user) throws JoyCarException {
		//1,基础校验（账号，密码，手机号，是否为空
		if (StringUtil.isEmpty(user.getUserAccount())) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(user.getUserPassword())) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(user.getPhoneNumber())) {// 手机号为空
			throw new JoyCarException(ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR_MSG);
		}
		
		//2,判断账号是否已存在(账号不能重复)
		UserWrapper userWrapperFromDB = null;
		try {
			userWrapperFromDB = userMapper.queryByAccount(user.getUserAccount());
		} catch (Exception e1) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e1);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		if(null != userWrapperFromDB){//已存在
			throw new JoyCarException(ErrorCode.USER_ACCOUNT_IS_EXIST_ERROR,
					ErrorCode.USER_ACCOUNT_IS_EXIST_ERROR_MSG);
		}
		
		//3,判断手机号是否存在（必须存在手机用户表中）
		PhoneUserWrapper phoneUserWrapperFromDB = null;
		try {
			phoneUserWrapperFromDB = phoneUserMapper.queryByPhoneNumber(user.getPhoneNumber());
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		if(null == phoneUserWrapperFromDB){//该手机账号已存在
			throw new JoyCarException(ErrorCode.PHONE_NUMBER_IS_NOT_EXIST_ERROR,
					ErrorCode.PHONE_NUMBER_IS_NOT_EXIST_ERROR_MSG);
		}
		
		//4,判断手机号状态是否可用
		if(!Constant.PHONE_ACCOUNT_STATUS.equalsIgnoreCase(phoneUserWrapperFromDB.getPhoneStatus())){
			throw new JoyCarException(ErrorCode.PHONE_STATUS_IS_ABNORMAL_ERROR,
					ErrorCode.PHONE_STATUS_IS_ABNORMAL_ERROR_MSG);
		}

		//5,设置手机用户默认账号状态 为0：正常
		user.setAccountStatus(Constant.USER_ACCOUNT_STATUS);
		
		//5,添加用户
		try {
			userMapper.add(user);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		
	}

	/**
	 * 根据账号查询user用户
	 * @param userAccount
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public UserWrapper queryByAccount(String userAccount)
			throws JoyCarException {
		//1,基础校验（账号，密码，手机号，是否为空
		if (StringUtil.isEmpty(userAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} 
		
		//2,查询用户
		UserWrapper userWrapper = null;
		try {
			userWrapper = userMapper.queryByAccount(userAccount);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//3,返回用户对象
		return userWrapper;
	}

	/**
	 * user用户登录
	 * @param userAccount
	 * @param userPassword
	 * @return UserWrapper
	 * @throws JoyCarException
	 */
	@Override
	public UserWrapper userLogin(String userAccount, String userPassword)
			throws JoyCarException {
		//1,基础校验（账号，密码是否为空）
		if (StringUtil.isEmpty(userAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		} else if (StringUtil.isEmpty(userPassword)) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("userPassword", userPassword);
		//2,查询用户
		UserWrapper userWrapper = null;
		try {
			userWrapper = userMapper.queryByAccountAndPwd(map);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//3,返回用户对象
		return userWrapper;
	}

	/**
	 * 缴费
	 * @param recordId 记录id
	 * @param userAccount 用户账号
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void payRecord(int recordId, String userAccount)
			throws JoyCarException {
		//1.基础校验
		if (StringUtil.isEmpty(userAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		}
		
		//2.根据recordId查出记录
		RecordWrapper recordWrapperFromDB = null;
		try {
			recordWrapperFromDB = recordMapper.queryById(recordId);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//3.根据userAccount查出用户
		UserWrapper userWrapperFromDB = null;
		try {
			userWrapperFromDB = userMapper.queryByAccount(userAccount);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//4.判断用户用户话费余额是否够
		Double fee = recordWrapperFromDB.getActionMoney();//罚金
		
		Double phoneFee = userWrapperFromDB.getPhoneFee();//话费余额
		
		//4.1不够：提示话费余额不足
		if (fee > phoneFee) {
			throw new JoyCarException(ErrorCode.PHONE_FEE_IS_NOT_ENOUGH,
					ErrorCode.PHONE_FEE_IS_NOT_ENOUGH_MSG);
		}
		
		//4.2够：
		//5.修改手机号的余额（通过查出的用户手机号查出）
		//构造更新手机表的条件
		PhoneUser phoneUser = new PhoneUser();
		phoneUser.setPhoneNumber(userWrapperFromDB.getPhoneNumber());
		phoneUser.setPhoneFee(phoneFee - fee);
		
		System.out.println("userWrapperFromDB.getPhoneNumber()-------------->:" + userWrapperFromDB.getPhoneNumber());
		System.out.println("fee-------------->:" + fee);
		System.out.println("phoneFee-------------->:" + phoneFee);
		System.out.println("phoneFee - fee-------------->:" + (phoneFee - fee));
		
		try {
			phoneUserMapper.updatePhoneFeeByPhoneNumber(phoneUser);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//6.修改记录的状态为1，已缴费
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("recordId", recordId);
		map.put("recordStatus", Constant.RECORD_STATUS_IS_YES_PAY);
		
		try {
			recordMapper.updateRecordStatusById(map);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}

	/**
	 * 根据id修改用户user
	 * @param user
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void editUser(User user) throws JoyCarException {
		//1.基础数据校验
		//1.基础校验
		if (StringUtil.isEmpty(user.getUserAccount())) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(user.getUserPassword())) {// 密码为空
			throw new JoyCarException(ErrorCode.USERPWD_IS_EMPTY_ERROR,
					ErrorCode.USERPWD_IS_EMPTY_ERROR_MSG);
		}
		
		try {
			userMapper.update(user);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}

	/**
	 * 多条件分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param userAccount
	 * @param phoneNumber
	 * @param accountStatus
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public PageModel<UserWrapper> queryAllByConditions(int pageNo,
			int pageSize, String userAccount, String phoneNumber,
			String accountStatus) throws JoyCarException {
		//1,拼接userAccount
		if(userAccount != null){
			userAccount = "%" + userAccount.trim() +"%";
		}
		
		if("".equalsIgnoreCase(userAccount)){
			userAccount = null;
		}
		
		//拼接phoneNumber
		if(phoneNumber != null){
			phoneNumber = phoneNumber.trim();
		}
		
		if("".equalsIgnoreCase(phoneNumber)){
			phoneNumber = null;
		}
		//phoneNumber
		if(accountStatus != null){
			accountStatus = accountStatus.trim();
		}
		
		if("".equalsIgnoreCase(accountStatus)){
			accountStatus = null;
		}
		
		//2.构造查询条件
		PageModel<UserWrapper> pageModel = new PageModel<UserWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("phoneNumber", phoneNumber);
		map.put("accountStatus", accountStatus);
		map.put("pageModel", pageModel);
		
		List<UserWrapper> userWrapperList = null;
		
		try {
			userWrapperList = userMapper.queryAllByConditions(map);
			pageModel.setDataList(userWrapperList);
			pageModel.setCnt(userMapper.getCntByConditions(map));
			if(0 == userMapper.getCntByConditions(map)){
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
	 * 用户充值
	 * @param cardNumber
	 * @param cardPassword
	 * @param userAccount
	 * @throws JoyCarException
	 */
	@Override
	public void recharge(String cardNumber, String cardPassword,
			String userAccount) throws JoyCarException {
		//1.基础校验
		if (StringUtil.isEmpty(cardNumber)) {// 账号为空
			throw new JoyCarException(ErrorCode.CARD_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.CARD_NUMBER_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(cardPassword)) {// 密码为空
			throw new JoyCarException(ErrorCode.CARD_STATUS_IS_EMPTY_ERROR,
					ErrorCode.CARD_STATUS_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(userAccount)) {// 用户账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		}
		
		//2,根据卡号和密码去查是否有此卡及状态是否可用
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("cardPassword", cardPassword);
		RechargeCardWrapper rechargeCardWrapper = null;
		try {
			rechargeCardWrapper = rechargeCardMapper.queryByCardNumberAndPassword(map);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		if(null == rechargeCardWrapper){//卡号或密码错误
			throw new JoyCarException(ErrorCode.CARD_NUMBER_OR_PWD_IS_ERROR,
					ErrorCode.CARD_NUMBER_OR_PWD_IS_ERROR_MSG);
		}
		
		if(rechargeCardWrapper.getCardStatus().equalsIgnoreCase("1")){//被使用过了
			throw new JoyCarException(ErrorCode.CARD_NUMBER_OR_PWD_IS_ERROR,
					ErrorCode.CARD_NUMBER_OR_PWD_IS_ERROR_MSG);
		}
		
		if(rechargeCardWrapper.getCardStatus().equalsIgnoreCase("2")){//已过期
			throw new JoyCarException(ErrorCode.CARD_OVER_TIME_ERROR,
					ErrorCode.CARD_OVER_TIME_ERROR_MSG);
		}
		
		//3userAccount查询用户
		UserWrapper userWrapper = null;
		try {
			userWrapper = userMapper.queryByAccount(userAccount);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//4,更新密保卡状态
		Map<String, Object> mapForUpdateCard =  new HashMap<String, Object>();
		mapForUpdateCard.put("cardId", Integer.valueOf(rechargeCardWrapper.getCardId()));
		mapForUpdateCard.put("cardValue", 0);
		mapForUpdateCard.put("cardStatus", 1);
		try {
			rechargeCardMapper.updateRechargeCard(mapForUpdateCard);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//5,更新手机对应的话费
		PhoneUser phoneUser = new PhoneUser();
		phoneUser.setPhoneFee(userWrapper.getPhoneFee()+rechargeCardWrapper.getCardValue());
		phoneUser.setPhoneNumber(userWrapper.getPhoneNumber());
		
		try {
			phoneUserMapper.updatePhoneFeeByPhoneNumber(phoneUser);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		
	}
	
	
	
}
