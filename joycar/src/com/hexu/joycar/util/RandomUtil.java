package com.hexu.joycar.util;

import java.util.Random;

/**
 * 生成指定区间内的随机数
 * @author hexu
 *
 */
public class RandomUtil {
	
	/**
	 * 得到1-number之间的一个随机数
	 * @param number
	 * @return
	 */
	public static int getRandom(int number){
		Random rand = new Random();
		int randNum = rand.nextInt(number) + 1;
		return randNum;
	}
	
}
