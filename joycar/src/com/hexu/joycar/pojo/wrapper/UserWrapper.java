package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.User;

/**
 * 用户包装类
 * @author hexu
 *
 */
public class UserWrapper extends User{
	
	/**
	 * 账号状态名
	 * '0'-->'正常'
	 * '1'-->'已注销'
	 */
	private String accountStatusName;
	
	/**
	 * 手机用户名
	 */
	private String phoneUserName;
	
	/**
	 * 手机用户性别0：男 1：女
	 */
	private String phoneUserSex;
	
	/**
	 * 手机用户性别名0：男 1：女
	 */
	private String phoneUserSexName;
	
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
	 * 手机账号状态名
	 */
	private String phoneStatusName;

	public UserWrapper() {
		super();
	}

	

	public UserWrapper(String accountStatusName, String phoneUserName,
			String phoneUserSex, String phoneUserSexName, int phoneUserAge,
			String idNumber, double phoneFee, String phoneStatus,
			String phoneStatusName) {
		super();
		this.accountStatusName = accountStatusName;
		this.phoneUserName = phoneUserName;
		this.phoneUserSex = phoneUserSex;
		this.phoneUserSexName = phoneUserSexName;
		this.phoneUserAge = phoneUserAge;
		this.idNumber = idNumber;
		this.phoneFee = phoneFee;
		this.phoneStatus = phoneStatus;
		this.phoneStatusName = phoneStatusName;
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

	public String getPhoneUserSexName() {
		return phoneUserSexName;
	}

	public void setPhoneUserSexName(String phoneUserSexName) {
		this.phoneUserSexName = phoneUserSexName;
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

	public String getPhoneStatusName() {
		return phoneStatusName;
	}

	public void setPhoneStatusName(String phoneStatusName) {
		this.phoneStatusName = phoneStatusName;
	}



	public String getAccountStatusName() {
		return accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}



	@Override
	public String toString() {
		return "UserWrapper [accountStatusName=" + accountStatusName
				+ ", idNumber=" + idNumber + ", phoneFee=" + phoneFee
				+ ", phoneStatus=" + phoneStatus + ", phoneStatusName="
				+ phoneStatusName + ", phoneUserAge=" + phoneUserAge
				+ ", phoneUserName=" + phoneUserName + ", phoneUserSex="
				+ phoneUserSex + ", phoneUserSexName=" + phoneUserSexName
				+ ", getAccountStatusName()=" + getAccountStatusName()
				+ ", getIdNumber()=" + getIdNumber() + ", getPhoneFee()="
				+ getPhoneFee() + ", getPhoneStatus()=" + getPhoneStatus()
				+ ", getPhoneStatusName()=" + getPhoneStatusName()
				+ ", getPhoneUserAge()=" + getPhoneUserAge()
				+ ", getPhoneUserName()=" + getPhoneUserName()
				+ ", getPhoneUserSex()=" + getPhoneUserSex()
				+ ", getPhoneUserSexName()=" + getPhoneUserSexName()
				+ ", getAccountStatus()=" + getAccountStatus()
				+ ", getCardNumber()=" + getCardNumber() + ", getCreateTime()="
				+ getCreateTime() + ", getModifiedTime()=" + getModifiedTime()
				+ ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getUserAccount()=" + getUserAccount() + ", getUserId()="
				+ getUserId() + ", getUserPassword()=" + getUserPassword()
				+ ", getUserPicture()=" + getUserPicture() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
