package com.hexu.joycar.pojo.wrapper;

import com.hexu.joycar.pojo.Car;

/**
 * 汽车实体类包装类
 * @author hexu
 *
 */
public class CarWrapper extends Car{
	
	/**
	 * 车辆类型名
	 */
	private String carTypeName;
	
	/**
	 * 省份名
	 */
	private String provinceName;
	
	/**
	 * 年检状态名
	 */
	private String checkStatusName;

	public CarWrapper() {
		super();
	}

	public CarWrapper(String carTypeName, String provinceName,
			String checkStatusName) {
		super();
		this.carTypeName = carTypeName;
		this.provinceName = provinceName;
		this.checkStatusName = checkStatusName;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCheckStatusName() {
		return checkStatusName;
	}

	public void setCheckStatusName(String checkStatusName) {
		this.checkStatusName = checkStatusName;
	}

	@Override
	public String toString() {
		return "CarWrapper [carTypeName=" + carTypeName + ", checkStatusName="
				+ checkStatusName + ", provinceName=" + provinceName + "]";
	}




	

}
