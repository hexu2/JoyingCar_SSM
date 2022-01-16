package com.hexu.joycar.pojo;

import java.util.Date;


/**
 * 普通用户实体类
 * @author hexu
 *
 */
public class User {
	
	/**
	 * 普通用户id
	 */
	private int userId;
	
	/**
	 * 普通用户账号
	 */
	private String userAccount;
	
	/**
	 * 普通用户密码
	 */
	private String userPassword;
	
	/**
	 * 用户头像
	 */
	private String userPicture;
	
	/**
	 * 手机号码
	 */
	private String phoneNumber;
	
	/**
	 * 车牌号
	 */
	private String cardNumber;
	
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

	public User() {
		super();
	}

	public User(int userId, String userAccount, String userPassword,
			String userPicture, String phoneNumber, String cardNumber,
			String accountStatus, Date createTime, Date modifiedTime) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userPicture = userPicture;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.accountStatus = accountStatus;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	
}
