package com.hexu.joycar.util;

import java.util.Random;

/**
 * 验证码生成工具
 * 
 * @author hexu
 * 
 */
public class CodeUtil {

	public static String getCode(int codeLength) {
		
		String str = "abcdefghigklmnopqrstuvwxyzABCEEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < codeLength; i++){
			char x = str.charAt(new Random().nextInt(str.length()));
			sb.append(x);
		}
		
		return sb.toString();
	}
}
