package com.hexu.joycar.mapper;

import java.util.List;
import java.util.Map;
import com.hexu.joycar.pojo.PhoneUser;
import com.hexu.joycar.pojo.wrapper.PhoneUserWrapper;

/**
 * 手机用户数据持久层接口
 * @author hexu
 * 
 */
public interface PhoneUserMapper {
	
	/**
	 * 根据手机号查询用户
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	PhoneUserWrapper queryByPhoneNumber(String phoneNumber)throws Exception;
	
	/**
	 * 根据id修改话费余额
	 * @param phoneUser
	 * @throws Exception
	 */
	void updatePhoneFeeByPhoneNumber(PhoneUser phoneUser)throws Exception;

	/**
	 * 根据条件分页查询手机用户
	 * phoneNumber ，完全匹配
	 * phoneUserName，模糊查询
	 * phoneUserSex ，性别0：男 1：女
	 * phoneStatus，手机号状态0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<PhoneUserWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;

	/**
	 * 条件查询的结果总数
	 * phoneNumber ，完全匹配
	 * phoneUserName，模糊查询
	 * phoneUserSex ，性别0：男 1：女
	 * phoneStatus，手机号状态0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;

	/**
	 * 添加手机用户
	 * @param phoneUser
	 * @throws Exception
	 */
	void addPhoneUser(PhoneUser phoneUser)throws Exception;
	
	/**
	 * 根据phoneNumber修改手机用户状态
	 * phoneNumber
	 * phoneStatus
	 * @param phoneUserId
	 * @throws Exception
	 */
	void editStatus(Map<String, Object> map)throws Exception;

	/**
	 * 删除所有手机用户
	 * @throws Exception
	 */
	void deletePhoneUser()throws Exception;
}
