package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 管理员实体类
 * @author hexu
 *
 */
public class Admin {
	
	/**
	 * 管理员id
	 */
	private int adminId;
	
	/**
	 * 管理员账号
	 */
	private String adminAccount;
	
	/**
	 * 管理员账号密码
	 */
	private String adminPassword;
	
	/**
	 * 账号状态
	 * '0'-->'正常'
	 * '1'-->'已注销'
	 */
	private String accountStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public Admin() {
		super();
	}

	public Admin(int adminId, String adminAccount, String adminPassword,
			String accountStatus, Date createTime, Date modifiedTime) {
		super();
		this.adminId = adminId;
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
		this.accountStatus = accountStatus;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Admin [accountStatus=" + accountStatus + ", adminAccount="
				+ adminAccount + ", adminId=" + adminId + ", adminPassword="
				+ adminPassword + ", createTime=" + createTime
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
}
