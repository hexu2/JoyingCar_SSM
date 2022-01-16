package com.hexu.joycar.mapper;

/**
 * 违章行为持久层接口
 * @author hexu
 *
 */
public interface LocationMapper {
	
	/**
	 * 得到违章行为记录的总数量
	 * @return
	 * @throws Exception
	 */
	int getCnt()throws Exception;
}
