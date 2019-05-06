package com.ycs.base.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * 安全哈希算法 
 * @author youcyousyunn
 * @date 2018年3月27日
 */
public class SHA1 {

	public static String SHA_1(String decript) {
		try {
			MessageDigest e = MessageDigest.getInstance("SHA-1");
			e.update(decript.getBytes());
			byte[] messageDigest = e.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; ++i) {
				String shaHex = Integer.toHexString(messageDigest[i] & 255);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}

				hexString.append(shaHex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException arg5) {
			arg5.printStackTrace();
			return "";
		}
	}

	public static byte[] SHA_2(byte[] data) {
		byte[] bytes = (byte[]) null;

		try {
			MessageDigest sha = MessageDigest.getInstance("sha-1");
			sha.update(data);
			bytes = sha.digest();
		} catch (NoSuchAlgorithmException arg3) {
			arg3.printStackTrace();
		}

		return bytes;
	}

	public static String WXSHA_1(String encStr) {
		String signature = null;

		try {
			MessageDigest e = MessageDigest.getInstance("SHA-1");
			e.reset();
			e.update(encStr.getBytes("UTF-8"));
			signature = byteToHex(e.digest());
		} catch (NoSuchAlgorithmException arg2) {
			arg2.printStackTrace();
		} catch (UnsupportedEncodingException arg3) {
			arg3.printStackTrace();
		}

		return signature;
	}

	private static String byteToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		byte[] result = hash;
		int arg2 = hash.length;

		for (int arg3 = 0; arg3 < arg2; ++arg3) {
			byte b = result[arg3];
			formatter.format("%02x", new Object[]{Byte.valueOf(b)});
		}

		String arg5 = formatter.toString();
		formatter.close();
		return arg5;
	}

	public static void main(String[] args) {
		String str = SHA_1("123456");
		System.out.println("SHA-1加密结果:" + str);
	}

}
