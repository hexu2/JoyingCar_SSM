package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 省份表
 * @author hexu
 *
 */
public class Province {
	
	/**
	 * 省份id
	 */
	private int provinceId;
	
	/**
	 * 省份名
	 */
	private String provinceName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public Province() {
		super();
	}

	public Province(int provinceId, String provinceName, Date createTime,
			Date modifiedTime) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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
		return "Province [createTime=" + createTime + ", modifiedTime="
				+ modifiedTime + ", provinceId=" + provinceId
				+ ", provinceName=" + provinceName + "]";
	}
	
	
}
