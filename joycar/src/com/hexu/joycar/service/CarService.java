package com.hexu.joycar.service;

import java.util.List;
import java.util.jar.JarException;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.util.PageModel;

/**
 * 车辆业务逻辑层接口
 * @author hexu
 *
 */
public interface CarService {


	/**
	 * 条件查询所有车辆
	 * @param userAccount 用户账号
	 * @param checkStatus 车辆年检状态
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 * @param userAccount 用户账号
	 * @param checkStatus 年检状态
	 * @return 
	 * @throws JoyCarException
	 */
	PageModel<CarWrapper> queryAllByConditions(int pageNo, int pageSize,String userAccount, String checkStatus, String carType)throws JoyCarException;

	/**
	 * 新增车辆
	 * @param userAccount 用户账号
	 * @param carType 车辆类型id
	 * @param carBodyNumber 车架号
	 * @param carNumberA 车牌号a段
	 * @param carNumberB 车牌号b段
	 * carNumber = carNumberA + carNumberB
	 * @throws JoyCarException
	 */
	void addCar(String userAccount,String carType,String carBodyNumber,String carNumberA,String carNumberB)throws JoyCarException;
	
	/**
	 * 根据用户账号查询车辆
	 * @param userAccount
	 * @return
	 * @throws JarException
	 */
	List<CarWrapper> queryByUserAccount(String userAccount)throws JarException;
}
