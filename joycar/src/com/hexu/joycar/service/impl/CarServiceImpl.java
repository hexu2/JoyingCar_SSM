package com.hexu.joycar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.CarMapper;
import com.hexu.joycar.pojo.Car;
import com.hexu.joycar.pojo.wrapper.CarWrapper;
import com.hexu.joycar.service.CarService;
import com.hexu.joycar.util.Constant;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.StringUtil;

/**
 * 车辆业务逻辑层接口实现类
 * @author hexu
 *
 */
@Service("carService")
public class CarServiceImpl implements CarService{

	private Logger log = Logger.getLogger(CarServiceImpl.class);

	@Autowired
	private CarMapper carMapper;
	
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
	@Override
	public PageModel<CarWrapper> queryAllByConditions(int pageNo, int pageSize,String userAccount,
			String checkStatus, String carType) throws JoyCarException {
		//1,fromat conditions
		//去空格userAccount
		if(userAccount != null){
			userAccount = userAccount.trim();
		}
		
		if("".equalsIgnoreCase(userAccount)){
			userAccount = null;
		}
		System.out.println("userAccount---------------------->:" + userAccount);
		
		//去空格checkStatus
		if(checkStatus != null){
			checkStatus = checkStatus.trim();
		}
		
		if("".equalsIgnoreCase(checkStatus)){
			checkStatus = null;
		}
		System.out.println("checkStatus---------------------->:" + checkStatus);
		
		//去空格carType
		if(carType != null){
			carType = carType.trim();
		}
		
		if("".equalsIgnoreCase(carType)){
			carType = null;
		}
		System.out.println("carType---------------------->:" + carType);
		
		
		
		//2.构造查询条件map
		PageModel<CarWrapper> pageModel = new PageModel<CarWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("checkStatus", checkStatus);
		map.put("carType", carType);
		map.put("pageModel", pageModel);
		
		
		List<CarWrapper> carWrapperList = null;
		
		try {
			carWrapperList = carMapper.queryAllByConditions(map);
			pageModel.setDataList(carWrapperList);
			pageModel.setCnt(carMapper.getCntByConditions(map));
			if(0 == carMapper.getCntByConditions(map)){
				pageModel.setPageNo2(0);
			}
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		return pageModel;
	}

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
	@Transactional
	@Override
	public void addCar(String userAccount, String carType,
			String carBodyNumber, String carNumberA, String carNumberB)
			throws JoyCarException {
		//基础数据校验
		if (StringUtil.isEmpty(userAccount)) {// 账号为空
			throw new JoyCarException(ErrorCode.USERACCOUNT_IS_EMPTY_ERROR,
					ErrorCode.USERACCOUNT_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(carBodyNumber)) {// 车架号为空
			throw new JoyCarException(ErrorCode.CAR_BODY_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.CAR_BODY_NUMBER_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(carNumberA)) {// 省份为空
			throw new JoyCarException(ErrorCode.PROVINCE_IS_EMPTY_ERROR,
					ErrorCode.PROVINCE_IS_EMPTY_ERROR_MSG);
		}else if (StringUtil.isEmpty(carNumberB)) {// 省份为空
			throw new JoyCarException(ErrorCode.CAR_NUMBER_IS_EMPTY_ERROR,
					ErrorCode.CAR_NUMBER_IS_EMPTY_ERROR_MSG);
		}
		
		
		
		String carNumber = carNumberA + carNumberB;
		
		System.out.println("userAccount--------------------------->:" + userAccount);
		System.out.println("carNumber--------------------------->:" + carNumber);
		System.out.println("carBodyNumber--------------------------->:" + carBodyNumber);
		
		//查询车牌号是否存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("carNumber", carNumber);
		map.put("carBodyNumber", null);
		
		List<CarWrapper> queryByCarNumber = null;
		try {
			queryByCarNumber = carMapper.queryByCarNumberAndCarBodyNumber(map);
		} catch (Exception e1) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		if(queryByCarNumber.size() > 0){
			throw new JoyCarException(ErrorCode.CAR_NUMBER_IS_EXIST_ERROR,
					ErrorCode.CAR_NUMBER_IS_EXIST_ERROR_MSG);
	
		}

		//查询车架号是否存在
		map.put("carNumber", null);
		map.put("carBodyNumber", carBodyNumber);
		
		List<CarWrapper> queryByCarBodyNumber = null;
		try {
			queryByCarBodyNumber = carMapper.queryByCarNumberAndCarBodyNumber(map);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		if(queryByCarBodyNumber.size() > 0){
			throw new JoyCarException(ErrorCode.CAR_BODY_NUMBER_IS_EXIST_ERROR,
					ErrorCode.CAR_BODY_NUMBER_IS_EXIST_ERROR_MSG);	
		}	

		//添加车辆		
		Car car = new Car();
		car.setUserAccount(userAccount);
		car.setCarType(carType);
		car.setCarNumber(carNumber);
		car.setCarBodyNumber(carBodyNumber);
		car.setCheckStatus(Constant.CHECK_STATUS_0);
		
		System.out.println("CheckStatus--------------------------->:" + Constant.CHECK_STATUS_1);
		
		try {
			carMapper.addCar(car);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
	}

	/**
	 * 根据用户账号查询车辆
	 * @param userAccount
	 * @return
	 * @throws JarException
	 */
	@Override
	public List<CarWrapper> queryByUserAccount(String userAccount)
			throws JarException {
		
		List<CarWrapper> carWrapperList = null;
		try {
			carWrapperList = carMapper.queryAllByUserAccount(userAccount);
		} catch (Exception e) {
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		return carWrapperList;
	}

}
