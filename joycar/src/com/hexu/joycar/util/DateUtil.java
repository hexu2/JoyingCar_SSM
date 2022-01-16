package com.hexu.joycar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期字符串转换工具 
 * @author hexu
 *
 */
public class DateUtil {
	/**
	 * 日期转换成字符串
	 * @param date Date类型日期
	 * @param fromat 需要转换成的格式
	 * @return 
	 */
	public static String date2Str(Date date,String fromat){
		SimpleDateFormat sdf = new SimpleDateFormat(fromat);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转换成日期
	 * @param dateStr string类型字符串
	 * @param fromat 格式
	 * @return
	 */
	public static Date Str2date(String dateStr,String fromat){
		SimpleDateFormat sdf = new SimpleDateFormat(fromat);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
