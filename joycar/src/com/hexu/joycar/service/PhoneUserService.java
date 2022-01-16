package com.hexu.joycar.service;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 手机用户业务逻辑接口层
 * @author hexu
 *
 */
public interface PhoneUserService {

	/**
	 * 根据手机号查询用户
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	PhoneUserWrapper queryByPhoneNumber(String phoneNumber)throws JoyCarException;

	/**
	 * 多条件分页查询所有手机用户
	 * @param pageNo
	 * @param pageSize
	 * @param phoneNumber
	 * @param phoneUserName
	 * @param phoneUserSex
	 * @param phoneStatus
	 * @return
	 * @throws JoyCarException
	 */
	PageModel<PhoneUserWrapper> queryAllByConditions(int pageNo, int pageSize, String phoneNumber, String phoneUserName, String phoneUserSex, String phoneStatus) throws JoyCarException;

	/**
	 * 修改手机用户状态根据手机号
	 * @param phoneNumber
	 * @param phoneStatus
	 * @throws JoyCarException
	 */
	void updateStatus(String phoneNumber, String phoneStatus)throws JoyCarException;
}
