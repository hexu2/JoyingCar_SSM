package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 违章地点/位置
 * @author hexu
 *
 */
public class Location {
	
	/**
	 * 位置id
	 */
	private int locationId;
	
	/**
	 * 位置名
	 */
	private String locationName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public Location() {
		super();
	}

	public Location(int locationId, String locationName, Date createTime,
			Date modifiedTime) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
		return "Location [createTime=" + createTime + ", locationId="
				+ locationId + ", locationName=" + locationName
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
}
