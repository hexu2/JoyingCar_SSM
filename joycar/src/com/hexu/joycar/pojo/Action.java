package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 违章行为实体类
 * @author hexu
 *
 */
public class Action {
	
	/**
	 * 违章行为id
	 */
	private int actionId;
	
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
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public Action() {
		super();
	}

	public Action(int actionId, String actionName, double actionMoney,
			int actionScore, Date createTime, Date modifiedTime) {
		super();
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionMoney = actionMoney;
		this.actionScore = actionScore;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
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
		return "Action [actionId=" + actionId + ", actionMoney=" + actionMoney
				+ ", actionName=" + actionName + ", actionScore=" + actionScore
				+ ", createTime=" + createTime + ", modifiedTime="
				+ modifiedTime + "]";
	}
	
}
