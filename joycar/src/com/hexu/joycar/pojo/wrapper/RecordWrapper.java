package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.Record;

/**
 * 违章记录实体类包装类
 * @author hexu
 *
 */
public class RecordWrapper extends Record{
	
	/**
	 * 位置名
	 */
	private String locationName;
	
	/**
	 * 违章行为名
	 */
	private String actionName;
	
	/**
	 * 罚款金额
	 */
	private double actionMoney;
	
	/**
	 * 扣分
	 */
	private int actionScore;
	
	/**
	 * 违章记录状态名
	 * 0：已处理
	 * 1：未处理
	 */
	private String recordStatusName;

	public RecordWrapper() {
		super();
	}

	public RecordWrapper(String locationName, String actionName,
			double actionMoney, int actionScore, String recordStatusName) {
		super();
		this.locationName = locationName;
		this.actionName = actionName;
		this.actionMoney = actionMoney;
		this.actionScore = actionScore;
		this.recordStatusName = recordStatusName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public double getActionMoney() {
		return actionMoney;
	}

	public void setActionMoney(double actionMoney) {
		this.actionMoney = actionMoney;
	}

	public int getActionScore() {
		return actionScore;
	}

	public void setActionScore(int actionScore) {
		this.actionScore = actionScore;
	}

	public String getRecordStatusName() {
		return recordStatusName;
	}

	public void setRecordStatusName(String recordStatusName) {
		this.recordStatusName = recordStatusName;
	}

	@Override
	public String toString() {
		return "RecordWrapper [actionMoney=" + actionMoney + ", actionName="
				+ actionName + ", actionScore=" + actionScore
				+ ", locationName=" + locationName + ", recordStatusName="
				+ recordStatusName + "]";
	}
	
}
