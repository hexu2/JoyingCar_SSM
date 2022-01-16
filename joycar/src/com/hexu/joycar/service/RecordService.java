package com.hexu.joycar.service;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 违章记录业务逻辑层接口
 * @author hexu
 *
 */
public interface RecordService {
	/**
	 * 根据条件分页查询所有违章记录
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws JoyCarException
	 */
	PageModel<RecordWrapper> queryAllByConditions(int pageNo, int pageSize, String userAccount, String recordNumber,String carNumber, String recordStatus) throws JoyCarException;
}
