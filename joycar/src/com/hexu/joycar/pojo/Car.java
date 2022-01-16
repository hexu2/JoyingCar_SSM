package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 机动车实体类
 * @author hexu
 *
 */
public class Car {
	
	/**
	 * 汽车id
	 */
	private int carId;
	
	/**
	 * 用户账号
	 */
	private String userAccount;
	
	/**
	 * 省份id
	 */
	private int provinceID;
	
	/**
	 * 车牌号
	 */
	private String carNumber;
	
	/**
	 * 车架号后六位
	 */
	private String carBodyNumber;
	
	/**
	 * 车型0：蓝牌小车 1：黄牌大车
	 */
	private String carType;
	
	/**
	 *  年检时间
	 */
	private Date checkTime;
	
	/**
	 *  下次年检时间
	 */
	private Date nextCheckTime;
	
	/**
	 * 年检状态0：已检 1：待检查
	 */
	private String checkStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;
	
	

	public Car() {
		super();
	}



	public Car(int carId, String userAccount, int provinceID, String carNumber,
			String carBodyNumber, String carType, Date checkTime,
			Date nextCheckTime, String checkStatus, Date createTime,
			Date modifiedTime) {
		super();
		this.carId = carId;
		this.userAccount = userAccount;
		this.provinceID = provinceID;
		this.carNumber = carNumber;
		this.carBodyNumber = carBodyNumber;
		this.carType = carType;
		this.checkTime = checkTime;
		this.nextCheckTime = nextCheckTime;
		this.checkStatus = checkStatus;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}



	public int getCarId() {
		return carId;
	}



	public void setCarId(int carId) {
		this.carId = carId;
	}



	public String getUserAccount() {
		return userAccount;
	}



	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}



	public int getProvinceID() {
		return provinceID;
	}



	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}



	public String getCarNumber() {
		return carNumber;
	}



	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}



	public String getCarBodyNumber() {
		return carBodyNumber;
	}



	public void setCarBodyNumber(String carBodyNumber) {
		this.carBodyNumber = carBodyNumber;
	}



	public String getCarType() {
		return carType;
	}



	public void setCarType(String carType) {
		this.carType = carType;
	}



	public Date getCheckTime() {
		return checkTime;
	}



	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}



	public Date getNextCheckTime() {
		return nextCheckTime;
	}



	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}



	public String getCheckStatus() {
		return checkStatus;
	}



	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
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
		return "Car [carBodyNumber=" + carBodyNumber + ", carId=" + carId
				+ ", carNumber=" + carNumber + ", carType=" + carType
				+ ", checkStatus=" + checkStatus + ", checkTime=" + checkTime
				+ ", createTime=" + createTime + ", modifiedTime="
				+ modifiedTime + ", nextCheckTime=" + nextCheckTime
				+ ", provinceID=" + provinceID + ", userAccount=" + userAccount
				+ "]";
	}


	
	
}
