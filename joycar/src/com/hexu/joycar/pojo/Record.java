package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 违章记录实体类
 * @author hexu
 *
 */
public class Record {
	
	/**
	 * 违章记录ID
	 */
	private int recordId;
	
	/**
	 * 罚单号
	 */
	private String recordNumber;
	
	/**
	 * 用户账号
	 */
	private String userAccount;
	
	/**
	 * 车牌号
	 */
	private String carNumber;
	
	/**
	 * 违章地点
	 */
	private int locationId;	
	
	/**
	 * 违章类型id
	 */
	private int actionId;
	

	/**
	 * 违章记录状态
	 * 0：已处理
	 * 1：未处理
	 */
	private String recordStatus;
	
	/**
	 * 违章详情
	 */
	private String recordDetail;
	
	/**
	 * 违章照片
	 */
	private String picture;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;
	
	public Record() {
		super();
	}

	public Record(int recordId, String recordNumber, String userAccount,
			String carNumber, int locationId, int actionId,
			String recordStatus, String recordDetail, String picture,
			Date createTime, Date modifiedTime) {
		super();
		this.recordId = recordId;
		this.recordNumber = recordNumber;
		this.userAccount = userAccount;
		this.carNumber = carNumber;
		this.locationId = locationId;
		this.actionId = actionId;
		this.recordStatus = recordStatus;
		this.recordDetail = recordDetail;
		this.picture = picture;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getRecordDetail() {
		return recordDetail;
	}

	public void setRecordDetail(String recordDetail) {
		this.recordDetail = recordDetail;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
		return "Record [actionId=" + actionId + ", carNumber=" + carNumber
				+ ", createTime=" + createTime + ", locationId=" + locationId
				+ ", modifiedTime=" + modifiedTime + ", picture=" + picture
				+ ", recordDetail=" + recordDetail + ", recordId=" + recordId
				+ ", recordNumber=" + recordNumber + ", recordStatus="
				+ recordStatus + ", userAccount=" + userAccount + "]";
	}
	
}
