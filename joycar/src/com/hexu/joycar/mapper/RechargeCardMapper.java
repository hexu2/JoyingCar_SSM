package com.hexu.joycar.mapper;

import java.util.List;
import java.util.Map;

import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;

/**
 * 话费充值卡数据持久层接口
 * @author hexu
 *
 */
public interface RechargeCardMapper {

	/**
	 * 多条件查询所有（导出时使用）
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
	 * @param secretCard
	 * @return
	 * @throws Exception
	 */
	List<RechargeCardWrapper> queryByConditionForExport(Map<String, Object> map) throws Exception;
	
	
	/**
	 * 根据条件分页查询
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<RechargeCardWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据条件分页查询总数结果
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据卡号和密码查询充值卡
	 * cardPassword
	 * @param map
	 * @return
	 * @throws Exception
	 */
	RechargeCardWrapper queryByCardNumberAndPassword(Map<String, Object> map)throws Exception;

	/**
	 * 根据id修改充值卡（充值时使用）
	 * cardId
	 * cardValue
	 * cardStatus
	 * @param map
	 * @throws Exception
	 */
	void updateRechargeCard(Map<String, Object> map)throws Exception;
	
	/**
	 * 批量生成充值卡
	 * cardNumbers  生成的数量
	 * cardValue 金额
	 * startTime 开始时间
	 * endTime 结束时间
	 * @param map
	 * @throws Exception
	 */
	void addRechargeCards(Map<String, Object> map) throws Exception;
}
