package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.PhoneUser;

/**
 * 手机用户实体类包装类
 * @author hexu
 * @version 2017-2-23 下午11:26:07
 */
public class PhoneUserWrapper extends PhoneUser{

	/**
	 * 手机用户性别名0：男 1：女
	 */
	private String phoneUserSexName;
	
	/**
	 * 该手机账号状态名0：正常 1：已注销
	 */
	private String phoneStatusName;

	public PhoneUserWrapper() {
		super();
	}

	public PhoneUserWrapper(String phoneUserSexName, String phoneStatusName) {
		super();
		this.phoneUserSexName = phoneUserSexName;
		this.phoneStatusName = phoneStatusName;
	}

	public String getPhoneUserSexName() {
		return phoneUserSexName;
	}

	public void setPhoneUserSexName(String phoneUserSexName) {
		this.phoneUserSexName = phoneUserSexName;
	}

	public String getPhoneStatusName() {
		return phoneStatusName;
	}

	public void setPhoneStatusName(String phoneStatusName) {
		this.phoneStatusName = phoneStatusName;
	}

	@Override
	public String toString() {
		return "PhoneUserWrapper [phoneStatusName=" + phoneStatusName
				+ ", phoneUserSexName=" + phoneUserSexName + "]";
	}
	
}
