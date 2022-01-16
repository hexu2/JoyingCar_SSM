package com.hexu.joycar.mapper;

/**
 * 位置持久层接口
 * @author hexu
 *
 */
public interface ActionMapper {
	
	/**
	 * 得到位置记录的总数量
	 * @return
	 * @throws Exception
	 */
	int getCnt()throws Exception;
}
