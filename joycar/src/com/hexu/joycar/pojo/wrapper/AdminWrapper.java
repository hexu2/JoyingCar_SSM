package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.Admin;

/**
 * admin 包装类
 * @author hexu
 *
 */
public class AdminWrapper extends Admin{
	
	/**
	 * 管理员用户账号状态名
	 */
	private String accountStatusName;

	public String getAccountStatusName() {
		return accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}
	
	
}
