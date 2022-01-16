package com.hexu.joycar.util;

import java.util.UUID;

/**
 * java uuid生成器
 * @author Administrator
 *
 */
public class UUIDGenerator {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
