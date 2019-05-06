package com.ycs.msg.utils;

import java.util.Random;

/**
 * @description 生成随机code工具类
 * @author youcyousyunn
 * @date 2018年12月1日
 */
public class GenerateCodeUtil {
	private static final String CHARACTER_NUMBER_GATHER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 生成6位数随机验证码
	 * @param
	 * @return sb
	 */
	public synchronized static String generateEmailCode () {
		StringBuilder sb = new StringBuilder();
		char[] array = CHARACTER_NUMBER_GATHER.toCharArray();
		int length = array.length, index;
		for (int i=0; i<6; i++) {
			index = new Random().nextInt(length);
			sb.append(array[index]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateEmailCode());
	}
	
}
