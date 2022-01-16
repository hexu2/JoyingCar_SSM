package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 配置表实体类
 * @author hexu
 *
 */
public class Config {
	
	/**
	 * 配置记录id
	 */
	private int configId;
	
	/**
	 * 配置类型名
	 */
	private String configType;
	
	/**
	 * 配置类型的key
	 */
	private String configKey;
	
	/**
	 * 配置类型的value
	 */
	private String configValue;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public Config() {
		super();
	}

	public Config(int configId, String configType, String configKey,
			String configValue, Date createTime, Date modifiedTime) {
		super();
		this.configId = configId;
		this.configType = configType;
		this.configKey = configKey;
		this.configValue = configValue;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Config [configId=" + configId + ", configKey=" + configKey
				+ ", configType=" + configType + ", configValue=" + configValue
				+ ", createTime=" + createTime + ", modifiedTime="
				+ modifiedTime + "]";
	}
	
}
