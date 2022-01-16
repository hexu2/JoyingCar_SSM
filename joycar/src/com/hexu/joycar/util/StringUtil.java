package com.hexu.joycar.util;


/**
 * 字符串非空校验工具
 * @author hexu
 *
 */
public class StringUtil {
	/**
	 *   
	 * @param msg
	 * @return  boolean
	 */
	public static boolean isEmpty(String msg){
		if(null == msg ||"".equals(msg.trim())){
			return true;
		}else{
			return false;
			
		}
		
	}
	
}
