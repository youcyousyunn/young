package com.ycs.base.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @description MD5加密工具类
 * @author youcyousyunn
 * @date 2018年11月30日
 */
public class EncryptUtil {

	/**
	 * 加密密码
	 * @param
	 * @return String
	 * @throws 
	 */
	public static String encryptPwd(String str) {
		MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            e.printStackTrace();  
            return "";  
        }
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int md5Bytes = 0; md5Bytes < charArray.length; ++md5Bytes) {
			byteArray[md5Bytes] = (byte) charArray[md5Bytes];
		}

		byte[] arg7 = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < arg7.length; ++i) {
			int val = arg7[i] & 255;
			if (val < 16) {
				hexValue.append("0");
			}

			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString().toUpperCase();
	}

	public static String getMD5ByApche(String str, String input_charset) {
		return DigestUtils.md5Hex(getContentBytes(str, input_charset));
	}

	private static byte[] getContentBytes(String content, String charset) {
		if (charset != null && !"".equals(charset)) {
			try {
				return content.getBytes(charset);
			} catch (UnsupportedEncodingException arg2) {
				throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
			}
		} else {
			return content.getBytes();
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String pwd = "123456";
		System.out.println(encryptPwd(pwd));
	}

}
