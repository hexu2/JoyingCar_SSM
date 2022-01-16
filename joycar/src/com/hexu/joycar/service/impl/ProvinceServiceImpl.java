package com.hexu.joycar.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.ProvinceMapper;
import com.hexu.joycar.pojo.Province;
import com.hexu.joycar.service.ProvinceService;

/**
 * 省份业务逻辑层接口实现类
 * @author hexu
 *
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{
	private Logger log = Logger.getLogger(ProvinceServiceImpl.class);

	
	@Autowired
	private ProvinceMapper provinceMapper;

	/**
	 * 查询所有省份
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public List<Province> queryAllProvice() throws JoyCarException {
		List<Province> provinceList = null;
		
		try {
			provinceList = provinceMapper.queryAllProvince();
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		return provinceList;
	}

}
