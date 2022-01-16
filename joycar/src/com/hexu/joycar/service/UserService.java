package com.hexu.joycar.service;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.UserWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 普通用户业务逻辑层接口
 * @author hexu
 *
 */
public interface UserService {

	/**
	 * 添加user用户
	 * @param user
	 * @throws JoyCarException
	 */
	void addUser(User user)throws JoyCarException;

	/**
	 * 根据账号查询user用户
	 * @param userAccount
	 * @return
	 * @throws JoyCarException
	 */
	UserWrapper queryByAccount(String userAccount)throws JoyCarException;

	/**
	 * user用户登录
	 * @param userAccount
	 * @param userPassword
	 * @return UserWrapper
	 * @throws JoyCarException
	 */
	UserWrapper userLogin(String userAccount, String userPassword)throws JoyCarException;
	
	/**
	 * 缴费
	 * @param recordId 记录id
	 * @param userAccount 用户账号
	 * @throws JoyCarException
	 */
	void payRecord(int recordId,String userAccount)throws JoyCarException;

	/**
	 * 根据id修改用户user
	 * @param user
	 * @throws JoyCarException
	 */
	void editUser(User user)throws JoyCarException;
	
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
	PageModel<UserWrapper> queryAllByConditions(int pageNo, int pageSize, String userAccount, String phoneNumber, String accountStatus) throws JoyCarException;

	/**
	 * 用户充值
	 * @param cardNumber
	 * @param cardPassword
	 * @param userAccount
	 * @throws JoyCarException
	 */
	void recharge(String cardNumber, String cardPassword, String userAccount )throws JoyCarException;
	
}
