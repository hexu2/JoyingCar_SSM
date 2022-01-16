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
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.service.PhoneUserService;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.StringUtil;

/**
 * 手机用户逻辑接口实现类
 * @author hexu
 *
 */
@Service("phoneUserService")
public class PhoneUserServiceImpl implements PhoneUserService{

	private Logger log = Logger.getLogger(PhoneUserServiceImpl.class);

	@Autowired
	private PhoneUserMapper phoneUserMapper;
	
	/**
	 * 根据手机号查询用户
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	@Override
	public PhoneUserWrapper queryByPhoneNumber(String phoneNumber)
			throws JoyCarException {
		//1.基础数据校验
		if (StringUtil.isEmpty(phoneNumber)) {
			// 帐号为空
			throw new JoyCarException(ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR_MSG);
		}
		
		PhoneUserWrapper phoneUserWrapperFromDB = null;
		
		try {
			phoneUserWrapperFromDB = phoneUserMapper.queryByPhoneNumber(phoneNumber);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		return phoneUserWrapperFromDB;
	}

	/**
	 * 多条件分页查询所有手机用户
	 * @param pageNo
	 * @param pageSize
	 * @param phoneNumber 完全匹配
	 * @param phoneUserName 模糊匹配
	 * @param phoneUserSex 完全匹配
	 * @param phoneStatus 完全匹配
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public PageModel<PhoneUserWrapper> queryAllByConditions(int pageNo,
			int pageSize, String phoneNumber, String phoneUserName,
			String phoneUserSex, String phoneStatus) throws JoyCarException {
		//拼接phoneNumber
		if(phoneNumber != null){
			phoneNumber = phoneNumber.trim();
		}
		
		if("".equalsIgnoreCase(phoneNumber)){
			phoneNumber = null;
		}
		//phoneUserName
		if(phoneUserName != null){
			phoneUserName = "%" + phoneUserName.trim() +"%";
		}
		
		if("".equalsIgnoreCase(phoneUserName)){
			phoneUserName = null;
		}
		
		//phoneUserSex
		if(phoneUserSex != null){
			phoneUserSex = phoneUserSex.trim();
		}
		
		if("".equalsIgnoreCase(phoneUserSex)){
			phoneUserSex = null;
		}
		//phoneStatus
		if(phoneStatus != null){
			phoneStatus = phoneStatus.trim();
		}
		
		if("".equalsIgnoreCase(phoneStatus)){
			phoneStatus = null;
		}
		
		//2,构造查询条件
		PageModel<PhoneUserWrapper> pageModel = new PageModel<PhoneUserWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageModel", pageModel);
		map.put("phoneNumber", phoneNumber);
		map.put("phoneUserName", phoneUserName);
		map.put("phoneUserSex", phoneUserSex);
		map.put("phoneStatus", phoneStatus);
		
		List<PhoneUserWrapper> phoneUserWrapperList = null;
		try {
			phoneUserWrapperList = phoneUserMapper.queryAllByConditions(map);
			pageModel.setDataList(phoneUserWrapperList);
			pageModel.setCnt(phoneUserMapper.getCntByConditions(map));
			if(0 == phoneUserMapper.getCntByConditions(map)){
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
	 * 修改手机用户状态根据手机号
	 * @param phoneNumber
	 * @param phoneStatus
	 * @throws JoyCarException
	 */
	@Transactional
	@Override
	public void updateStatus(String phoneNumber, String phoneStatus)
			throws JoyCarException {
		//1.基础数据校验
		if (StringUtil.isEmpty(phoneNumber)) {
			// 帐号为空
			throw new JoyCarException(ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.PHONE_NUMBER_IS_EMPTY_ERROR_MSG);
		}else if(StringUtil.isEmpty(phoneStatus)){//状态为空
			throw new JoyCarException(ErrorCode.PHONE_STATUS_IS_EMPTY_ERROR,
					ErrorCode.PHONE_STATUS_IS_EMPTY_ERROR_MSG);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNumber", phoneNumber);
		map.put("phoneStatus", phoneStatus);
		
		try {
			phoneUserMapper.editStatus(map);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
	}
	
}
