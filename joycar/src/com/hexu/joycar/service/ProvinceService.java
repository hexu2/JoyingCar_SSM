package com.hexu.joycar.service;

import java.util.List;

import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.pojo.Province;

/**
 * 省份业务逻辑层接口
 * @author hexu
 *
 */
public interface ProvinceService {
		
	/**
	 * 查询所有省份
	 * @return
	 * @throws JoyCarException
	 */
	List<Province> queryAllProvice() throws JoyCarException;
}
