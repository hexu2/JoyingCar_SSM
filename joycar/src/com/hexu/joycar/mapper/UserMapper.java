package com.hexu.joycar.mapper;
import java.util.List;
import java.util.Map;
import com.hexu.joycar.pojo.User;
import com.hexu.joycar.pojo.wrapper.UserWrapper;

/**
 * 用户数据持久层接口
 * @author hexu
 *
 */
public interface UserMapper {
	
	/**
	 * 添加新用户
	 * @param user
	 */
	void add(User user)throws Exception;
	
	/**
	 * 根据账号查询用户
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	UserWrapper queryByAccount(String userAccount)throws Exception;

	/**
	 * 根据账号和密码查询用户
	 * key: userAccount
	 * key: userPassword
	 * @param map
	 * @return UserWrapper
	 */
	UserWrapper queryByAccountAndPwd(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据id修改user用户
	 * @param user
	 * @throws Exception
	 */
	void update(User user)throws Exception;
	
	/**
	 * 根据条件分页查询
	 * userAccount，模糊匹配
	 * phoneNumber，手机号
	 * accountStatus,0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<UserWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 条件查询的结果总数
	 * userAccount，模糊匹配
	 * phoneNumber，手机号
	 * accountStatus,0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;
}
