package com.hexu.joycar.mapper;

import java.util.List;
import java.util.Map;

import com.hexu.joycar.pojo.Car;
import com.hexu.joycar.pojo.wrapper.CarWrapper;

/**
 * 车辆持久层接口
 * @author hexu
 *
 */
public interface CarMapper {
	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * carType ,车辆类型
	 * check_status，0：正常 1：已注销 年检状态
	 * pageModel
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<CarWrapper> queryAllByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据userAccount查询所有车辆记录
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	List<CarWrapper> queryAllByUserAccount(String userAccount)throws Exception;
	
	/**
	 * 条件查询的结果总数
	 * userAccount ，完全匹配
	 * carType ,车辆类型
	 * check_status，0：正常 1：已注销 年检状态
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getCntByConditions(Map<String, Object> map)throws Exception;
	
	/**
	 * 添加车辆
	 * userAccount
	 * carNumber
	 * carBodyNumber
	 * carType
	 * @param car
	 * @throws Exception
	 */
	void addCar(Car car)throws Exception;
	
	/**
	 * 根据车牌号和车架号查询车辆
	 * 两个参数可为空
	 * @param carNumber
	 * @param carBodyNumber
	 * map
	 * @return
	 * @throws Exception
	 */
	List<CarWrapper> queryByCarNumberAndCarBodyNumber(Map<String, Object> map)throws Exception;
}
