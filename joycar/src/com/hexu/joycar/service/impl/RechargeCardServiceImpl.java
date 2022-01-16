package com.hexu.joycar.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hexu.joycar.exception.ErrorCode;
import com.hexu.joycar.exception.JoyCarException;
import com.hexu.joycar.mapper.RechargeCardMapper;
import com.hexu.joycar.pojo.wrapper.RechargeCardWrapper;
import com.hexu.joycar.service.RechargeCardService;
import com.hexu.joycar.util.DateUtil;
import com.hexu.joycar.util.PageModel;
import com.hexu.joycar.util.PropertiesUtil;

/**
 * 话费充值卡业务逻辑层接口实现类
 * @author hexu
 *
 */
@Service("rechargeCardService")
public class RechargeCardServiceImpl implements RechargeCardService{

	private Logger log = Logger.getLogger(RechargeCardServiceImpl.class);

	@Autowired
	private RechargeCardMapper rechargeCardMapper;
	
	
	@Override
	public void addRechangeCards(int cardNumbers, int cardValue,
			Date startTime, Date endTime) throws JoyCarException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 根据条件导出充值卡
	 * @param cardNumber
	 * @param cardStatus
	 * @return
	 * @throws Exception
	 */
	@Override
	public String exprotSecretCard(String cardNumber, String cardStatus)
			throws Exception {
		//1,拼接cardNumber
		if(cardNumber != null){
			cardNumber = cardNumber.trim();
		}
		
		if("".equalsIgnoreCase(cardNumber)){
			cardNumber = null;
		}
		
		//cardStatus
		if(cardStatus != null){
			cardStatus = cardStatus.trim();
		}
		
		if("".equalsIgnoreCase(cardStatus)){
			cardStatus = null;
		}
		
		//2，query data from DB
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("cardStatus", cardStatus);
		List<RechargeCardWrapper> cardWrapperList = null;
		try {
			cardWrapperList = rechargeCardMapper.queryByConditionForExport(map);
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		//3，导出cardWrapperList到指定位置
		String fileName = null;
		try {
			// Workbook book = Workbook.getWorkbook(new File("text.xls"));
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMddHHmmss");
			fileName =  sdf.format(new Date())+".xls";
			String urlAndFileName = PropertiesUtil.getKey("uploadPath") + fileName;
			WritableWorkbook book = Workbook
					.createWorkbook(new File(urlAndFileName));
			// 获得第一个工作表对象
			WritableSheet sheet1 = book.createSheet("第一页", 1000000000);
			// 参数
			Label labeTitle1 = new Label(0, 0, "ID(cardId)");
			Label labeTitle2 = new Label(1, 0, "卡号(cardNumber)");
			Label labeTitle3 = new Label(2, 0, "密码(cardPassword)");
			Label labeTitle4 = new Label(3, 0, "金额(cardValue)");
			Label labeTitle5 = new Label(4, 0, "开始时间(startTime)");
			Label labeTitle6 = new Label(5, 0, "结束时间(endTime)");
			Label labeTitle7 = new Label(6, 0, "状态(cardStatus)");

			sheet1.addCell(labeTitle1);
			sheet1.addCell(labeTitle2);
			sheet1.addCell(labeTitle3);
			sheet1.addCell(labeTitle4);
			sheet1.addCell(labeTitle5);
			sheet1.addCell(labeTitle6);
			sheet1.addCell(labeTitle7);

			for (int i = 1; i <= cardWrapperList.size(); i++) {
				Label lab1 = new Label(0, i, String.valueOf(cardWrapperList.get(i - 1).getCardId()));
				Label lab2 = new Label(1, i, cardWrapperList.get(i - 1).getCardNumber());
				Label lab3 = new Label(2, i, cardWrapperList.get(i - 1).getCardPassword());
				Label lab4 = new Label(3, i, String.valueOf(cardWrapperList.get(i - 1).getCardValue()));
				Label lab5 = new Label(4, i, DateUtil.date2Str(cardWrapperList.get(i - 1).getStartTime(),"yyyy-MM-dd"));
				Label lab6 = new Label(5, i, DateUtil.date2Str(cardWrapperList.get(i - 1).getEndTime(),"yyyy-MM-dd"));
				Label lab7 = new Label(6, i, cardWrapperList.get(i - 1).getCardStatusName());
				sheet1.addCell(lab1);
				sheet1.addCell(lab2);
				sheet1.addCell(lab3);
				sheet1.addCell(lab4);
				sheet1.addCell(lab5);
				sheet1.addCell(lab6);
				sheet1.addCell(lab7);
			}
			book.write();
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 根据条件分页查询
	 * cardNumber ，完全匹配
	 * cardStatus，0：正常 1：已使用 2：已过期
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 * @return PageModel<RechargeCardWrapper>
	 * @throws JoyBeansException
	 */
	@Override
	public PageModel<RechargeCardWrapper> queryAllReChageCardCardByCondition(
			int pageNo, int pageSize, String cardNumber, String cardStatus)
			throws JoyCarException {
		//1,拼接cardNumber
		if(cardNumber != null){
			cardNumber = cardNumber.trim();
		}
		
		if("".equalsIgnoreCase(cardNumber)){
			cardNumber = null;
		}
		
		//cardStatus
		if(cardStatus != null){
			cardStatus = cardStatus.trim();
		}
		
		if("".equalsIgnoreCase(cardStatus)){
			cardStatus = null;
		}
		
		//2,构造查询条件
		//pageModel
		PageModel<RechargeCardWrapper> pageModel = new PageModel<RechargeCardWrapper>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("cardStatus", cardStatus);
		map.put("pageModel", pageModel);
		
		List<RechargeCardWrapper> rechargeCardWrapperList = null;
		
		try {
			rechargeCardWrapperList = rechargeCardMapper.queryAllByConditions(map);
			pageModel.setDataList(rechargeCardWrapperList);
			pageModel.setCnt(rechargeCardMapper.getCntByConditions(map));
			if (0 == rechargeCardMapper.getCntByConditions(map)) {
				pageModel.setPageNo2(0);
			}
		
		} catch (Exception e) {
			log.info(ErrorCode.DATABASE_ERROR_MSG, e);
			throw new JoyCarException(ErrorCode.DATABASE_ERROR,
					ErrorCode.DATABASE_ERROR_MSG);
		}
		
		return pageModel;
	}

}
