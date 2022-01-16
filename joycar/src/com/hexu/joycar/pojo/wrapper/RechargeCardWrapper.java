package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.RechargeCard;

/**
 * 话费充值卡包装类
 * @author hexu
 *
 */
public class RechargeCardWrapper extends RechargeCard{

	private String cardStatusName;

	public RechargeCardWrapper() {
		super();
	}

	public RechargeCardWrapper(String cardStatusName) {
		super();
		this.cardStatusName = cardStatusName;
	}

	public String getCardStatusName() {
		return cardStatusName;
	}

	public void setCardStatusName(String cardStatusName) {
		this.cardStatusName = cardStatusName;
	}

	@Override
	public String toString() {
		return "RechargeCardWrapper [cardStatusName=" + cardStatusName + "]";
	}
	
	
}
