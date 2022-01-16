package com.hexu.joycar.service;

import java.util.Date;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.Admin;
import com.hexu.joycar.pojo.wrapper.AdminWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 管理员业务逻辑接口层
 * @author hexu
 *
 */
public interface AdminService {
	
	/**
	 * 管理员登陆
	 * @param adminAccount 管理员账号
	 * @param adminPassword 管理员密码
	 * @return Admin
	 * @throws JoyCarException
	 */
	AdminWrapper adminLogin(String adminAccount, String adminPassword) throws JoyCarException;
	
	/**
	 * 根据账号查询管理员用户
	 * @param adminAccount
	 * @return AdminWrapper
	 * @throws Exception
	 */
	AdminWrapper queryByAccount(String adminAccount) throws JoyCarException;
	
	/**
	 * 根据条件分页查询管理员用户
	 * @param pageNo 页号
	 * @param pageSize 页面大小 
	 * @param adminAccount 账号 （模糊查询）
	 * @param accountStatus账号状态（完全匹配）
	 * @return PageModel<AdminWrapper>
	 * @throws JoyCarException
	 */
	PageModel<AdminWrapper> queryAllByConditions(int pageNo, int pageSize, String adminAccount, String accountStatus) throws JoyCarException;
	
	/**
	 * 根据id删除admin用户
	 * @param adminId
	 * @throws JoyCarException
	 */
	void deleteById(int adminId)throws JoyCarException;
	
	/**
	 * 添加admin用户
	 * @param adminAccount
	 * @param adminPassword
	 * @param accountStatusString
	 */
	void addAdmin(String adminAccount, String adminPassword, String accountStatus)throws JoyCarException;

	/**
	 * 修改用户根据id
	 * @param admin
	 * @throws JoyCarException
	 */
	void editAdmin(Admin admin)throws JoyCarException;
	
	/**
	 * 批量生成话费充值卡
	 * @param cardNumbers
	 * @param cardValue
	 * @param startTime
	 * @param endTime
	 * @throws JoyCarException
	 */
	void createRechargeCard(int cardNumbers,int cardValue, Date startTime, Date endTime)throws JoyCarException;
	
	/**
	 * 模拟生成违章记录
	 * @param userAccount 账号
	 * @param carNumber 车牌号
	 * @param recordNumber 生成条数
	 * @throws JoyCarException
	 */
	void createRecord(String userAccount, String carNumber,int recordNumber)throws JoyCarException;

	/**
	 * 导入手机用户
	 * config/joycar/phoneUser.txt
	 * @throws JoyCarException
	 */
	void importPhoneUser()throws JoyCarException;
}
