package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 手机用户实体类
 * @author hexu
 *
 */
public class PhoneUser {
	
	/**
	 * 手机用户id
	 */
	private int phoneUserId;
	
	/**
	 * 手机号
	 */
	private String phoneNumber;
	
	/**
	 * 手机用户名
	 */
	private String phoneUserName;
	
	/**
	 * 手机用户性别0：男 1：女
	 */
	private String phoneUserSex;
	
	/**
	 * 手机用户年龄
	 */
	private int phoneUserAge;
	
	/**
	 * 用户身份证后六位
	 */
	private String idNumber;
	
	/**
	 * 话费余额
	 */
	private double phoneFee;
	
	/**
	 * 该手机账号状态0：正常 1：已注销
	 */
	private String phoneStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最经一次修改时间
	 */
	private Date modified_time;

	
	
	public PhoneUser() {
		super();
	}



	public PhoneUser(int phoneUserId, String phoneNumber, String phoneUserName,
			String phoneUserSex, int phoneUserAge, String idNumber,
			double phoneFee, String phoneStatus, Date createTime,
			Date modifiedTime) {
		super();
		this.phoneUserId = phoneUserId;
		this.phoneNumber = phoneNumber;
		this.phoneUserName = phoneUserName;
		this.phoneUserSex = phoneUserSex;
		this.phoneUserAge = phoneUserAge;
		this.idNumber = idNumber;
		this.phoneFee = phoneFee;
		this.phoneStatus = phoneStatus;
		this.createTime = createTime;
		modified_time = modifiedTime;
	}



	public int getPhoneUserId() {
		return phoneUserId;
	}



	public void setPhoneUserId(int phoneUserId) {
		this.phoneUserId = phoneUserId;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPhoneUserName() {
		return phoneUserName;
	}



	public void setPhoneUserName(String phoneUserName) {
		this.phoneUserName = phoneUserName;
	}



	public String getPhoneUserSex() {
		return phoneUserSex;
	}



	public void setPhoneUserSex(String phoneUserSex) {
		this.phoneUserSex = phoneUserSex;
	}



	public int getPhoneUserAge() {
		return phoneUserAge;
	}



	public void setPhoneUserAge(int phoneUserAge) {
		this.phoneUserAge = phoneUserAge;
	}



	public String getIdNumber() {
		return idNumber;
	}



	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}



	public double getPhoneFee() {
		return phoneFee;
	}



	public void setPhoneFee(double phoneFee) {
		this.phoneFee = phoneFee;
	}



	public String getPhoneStatus() {
		return phoneStatus;
	}



	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getModified_time() {
		return modified_time;
	}



	public void setModified_time(Date modifiedTime) {
		modified_time = modifiedTime;
	}



	@Override
	public String toString() {
		return "PhoneUser [createTime=" + createTime + ", idNumber=" + idNumber
				+ ", modified_time=" + modified_time + ", phoneFee=" + phoneFee
				+ ", phoneNumber=" + phoneNumber + ", phoneStatus="
				+ phoneStatus + ", phoneUserAge=" + phoneUserAge
				+ ", phoneUserId=" + phoneUserId + ", phoneUserName="
				+ phoneUserName + ", phoneUserSex=" + phoneUserSex + "]";
	}

	
	
}
