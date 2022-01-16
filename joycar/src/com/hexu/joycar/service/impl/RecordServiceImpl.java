package com.hexu.joycar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.RecordMapper;
import com.hexu.joycar.pojo.wrapper.RecordWrapper;
import com.hexu.joycar.service.RecordService;
import com.hexu.joycar.util.PageModel;

/**
 * 违章记录业务逻辑接口层实现类
 * @author hexu
 *
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService{
	
	private Logger log = Logger.getLogger(RecordServiceImpl.class);
	
	@Autowired
	private RecordMapper recordMapper;
	
	
	/**
	 * 根据条件分页查询
	 * userAccount ，完全匹配
	 * recordNumber,罚单号
	 * carNumber，车牌号
	 * recordStatus，0：已缴费 1：未缴费 缴费状态
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws JoyCarException
	 */
	@Override
	public PageModel<RecordWrapper> queryAllByConditions(int pageNo,
			int pageSize, String userAccount, String recordNumber,
			String carNumber, String recordStatus) throws JoyCarException {
		//1,拼接userAccount
		if(userAccount != null){
			userAccount = userAccount.trim();
		}
		
		if("".equalsIgnoreCase(userAccount)){
			userAccount = null;
		}
		//2,拼接recordNumber
		if(recordNumber != null){
			recordNumber = recordNumber.trim();
		}
		
		if("".equalsIgnoreCase(recordNumber)){
			recordNumber = null;
		}	
		//3,拼接carNumber
		if(carNumber != null){
			carNumber = carNumber.trim();
		}
		
		if("".equalsIgnoreCase(carNumber)){
			carNumber = null;
		}	
		//4,拼接recordStatus
		if(recordStatus != null){
			recordStatus = recordStatus.trim();
		}
		
		if("".equalsIgnoreCase(recordStatus)){
			recordStatus = null;
		}	
		
		//5.构造查询条件
		//pageModel
		PageModel<RecordWrapper> pageModel = new PageModel<RecordWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccount", userAccount);
		map.put("recordNumber", recordNumber);
		map.put("carNumber", carNumber);
		map.put("recordStatus", recordStatus);
		map.put("pageModel", pageModel);
		
		List<RecordWrapper> recordWrappers = null;
		try {
			System.out.println("------------------------------------>:1");
			recordWrappers = recordMapper.queryAllByConditions(map);
			System.out.println("------------------------------------>:2");
			pageModel.setDataList(recordWrappers);
			System.out.println("------------------------------------>:3");
			pageModel.setCnt(recordMapper.getCntByConditions(map));
			System.out.println("------------------------------------>:4");
			if(0 == recordMapper.getCntByConditions(map)){
				pageModel.setPageNo2(0);
			}
		
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		
		//6.返回结果
		return pageModel;
	}

}
