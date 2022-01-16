package com.hexu.joycar.pojo;

import java.util.Date;

/**
 * 手机充值卡实体类
 * @author hexu
 *
 */
public class RechargeCard {
	
	/**
	 * 充值卡id
	 */
	private int cardId;
	
	/**
	 * 充值卡卡号
	 */
	private String cardNumber;
	
	/**
	 * 充值卡密码
	 */
	private String cardPassword;
	
	/**
	 * 充值卡金额
	 */
	private int cardValue;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 充值卡状态
	 * 0:正常
	 * 1：已使用
	 * 2：已过期
	 */
	private String cardStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最近一次修改时间
	 */
	private Date modifiedTime;

	public RechargeCard() {
		super();
	}

	public RechargeCard(int cardId, String cardNumber, String cardPassword,
			int cardValue, Date startTime, Date endTime, String cardStatus,
			Date createTime, Date modifiedTime) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardPassword = cardPassword;
		this.cardValue = cardValue;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cardStatus = cardStatus;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
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
		return "RechargeCard [cardId=" + cardId + ", cardNumber=" + cardNumber
				+ ", cardPassword=" + cardPassword + ", cardStatus="
				+ cardStatus + ", cardValue=" + cardValue + ", createTime="
				+ createTime + ", endTime=" + endTime + ", modifiedTime="
				+ modifiedTime + ", startTime=" + startTime + "]";
	}
	
}
