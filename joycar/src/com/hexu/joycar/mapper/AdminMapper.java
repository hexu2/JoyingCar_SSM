package com.hexu.joycar.mapper;

import java.util.List;
import java.util.Map;

import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 管理员用户持久层接口
 * @author hexu
 *
 */
public interface AdminMapper {
	
	/**
	 * 分页查询所有管理员用户
	 * @param pageModel
	 * @return List<AdminMapper>
	 * @throws Exception
	 */
	List<AdminWrapper> queryAll(PageModel<AdminWrapper> pageModel)throws Exception;
	
	/**
	 * 根据条件分页查询
	 * adminAccount ，模糊匹配
	 * accountStatus，0：正常 1：已注销
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<AdminWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 条件查询的结果总数
	 * adminAccount ，模糊匹配
	 * accountStatus，0：正常 1：已注销
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据账号和密码查询管理员用户（登录用）
	 * @return AdminWrapper
	 * @throws Exception
	 */
	AdminWrapper queryByAccountAndPassword(Admin admin)throws Exception;
	
	/**
	 * 根据账号查询管理员用户
	 * @param adminAccount
	 * @return AdminWrapper
	 * @throws Exception
	 */
	AdminWrapper queryByAccount(String adminAccount)throws Exception;
	
	/**
	 * 添加新管理员
	 * @param admin
	 * @throws Exception
	 */
	void add(Admin admin)throws Exception;
	
	/**
	 * 根据id删除管理员用户
	 * @param admin
	 * @throws Exception
	 */
	void delete(int adminId)throws Exception;
	
	/**
	 * 根据id修改管理员用户
	 * @param admin
	 * @throws Exception
	 */
	void update(Admin admin)throws Exception;
}
