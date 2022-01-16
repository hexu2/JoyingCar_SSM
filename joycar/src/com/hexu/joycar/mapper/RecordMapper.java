package com.hexu.joycar.mapper;

import java.util.List;
import java.util.Map;

import com.hexu.joycar.pojo.Record;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;


/**
 * 车辆持久层接口
 * @author hexu
 *
 */
public interface RecordMapper {
	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<RecordWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 条件查询的结果总数
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 添加违章记录
	 * @param record
	 * userAccount
	 * carNumber
	 * locationId
	 * actionId
	 * recordStatus ：初始为1,即未缴费
	 * createTime
	 * @return
	 * @throws Exception
	 */
	void addRecord(Record record)throws Exception;
	
	/**
	 * 根据id查违章记录
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	RecordWrapper queryById(int recordId)throws Exception;
	
	/**
	 * 根据id修改记录状态
	 * key: recordId
	 * key: record_status
	 * @param map
	 * @throws Exception
	 */
	void updateRecordStatusById(Map<String, Object> map)throws Exception;
}
