package com.hexu.joycar.service;

import java.util.Date;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 话费充值卡业务逻辑层接口
 * @author hexu
 *
 */
public interface RechargeCardService {

	/**
	 * 根据条件分页查询
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 * @return PageModel<RechargeCardWrapper>
	 * @throws JoyBeansException
	 */
	PageModel<RechargeCardWrapper> queryAllReChageCardCardByCondition(int pageNo, int pageSize,String cardNumber,String cardStatus)throws JoyCarException;

	/**
	 * 根据条件导出充值卡
	 * @param cardNumber
	 * @param cardStatus
	 * @return
	 * @throws Exception
	 */
	String exprotSecretCard(String cardNumber,String cardStatus)throws Exception;

	/**
	 * 批量生成话费充值卡
	 * @param cardNumbers
	 * @param cardValue
	 * @param startTime
	 * @param endTime
	 * @throws JoyCarException
	 */
	void addRechangeCards(int cardNumbers, int cardValue, Date startTime, Date endTime )throws JoyCarException;
	
}
