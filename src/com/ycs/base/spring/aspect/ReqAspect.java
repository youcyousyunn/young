package com.ycs.base.spring.aspect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import com.alibaba.fastjson.JSON;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.encrypt.SHA1;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.log4j.logger.HiITFLogger;

public class ReqAspect {

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		Object[] args = pjp.getArgs();
		if (args != null && args.length == 1 && args[0] != null) {
			byte[] inputMessage = (byte[]) ((byte[]) args[0]);
			HiTransactionInfo hiTransInfo = HiTransactionInfo.getCurInstance();
			if (hiTransInfo.canLog()) {
				HiITFLogger.info("receive code data[" + new String(inputMessage) + "]");
			}
			if ("Y".equals(hiTransInfo.getPwdSwitch())) {
				String inputMsgStr = new String(inputMessage);
				if (hiTransInfo.canLog()) {
					HiITFLogger.info("receive decode data[" + inputMsgStr + "]");
				}
				if (inputMsgStr == null || inputMsgStr.length() <= 40) {
					throw new HiBusinessReturnException("T0001", "密文长度不对");
				}
				String signStr = inputMsgStr.substring(inputMsgStr.length() - 40, inputMsgStr.length());
				String messageSignStr = this.genMsgSignStr(inputMsgStr);
				if (hiTransInfo.canLog()) {
					HiITFLogger.info("receive decode signstr [" + messageSignStr + "]");
				}
				if (!signStr.equals(messageSignStr)) {
					throw new HiBusinessReturnException("T0002", "签名验证错误");
				}
				result = pjp.proceed();
			} else {
				result = pjp.proceed();
			}
		}
		return result;
	}

	private String genMsgSignStr(String inputMsgStr) {
		String messageStr = inputMsgStr.substring(0, inputMsgStr.length() - 40);
		Object paramMap = new HashMap();
		try {
			new HashMap();
			if (!TextUtils.isEmpty(messageStr)) {
				paramMap = (Map) JSON.parse(messageStr);
			}
		} catch (Exception arg9) {
			arg9.printStackTrace();
		}

		String[] keyArray = (String[]) ((Map) paramMap).keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		StringBuilder stringBuilder = new StringBuilder();
		String[] codes = keyArray;
		int messageSignStr = keyArray.length;
		for (int arg7 = 0; arg7 < messageSignStr; ++arg7) {
			String key = codes[arg7];
			stringBuilder.append(key).append(((Map) paramMap).get(key));
		}

		String arg10 = stringBuilder.toString();
		String arg11 = SHA1.SHA_1(arg10);
		return arg11;
	}
	
}
