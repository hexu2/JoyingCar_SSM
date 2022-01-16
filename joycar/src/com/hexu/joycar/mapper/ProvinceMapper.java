package com.hexu.joycar.mapper;

import java.util.List;

import com.hexu.joycar.pojo.Province;

/**
 * 车辆持久层接口
 * @author hexu
 *
 */
public interface ProvinceMapper {
	
	/**
	 * 根据id查询省份
	 * @param provinceID
	 * @return
	 * @throws Exception
	 */
	List<Province> queryAllProvince()throws Exception;
	

}
