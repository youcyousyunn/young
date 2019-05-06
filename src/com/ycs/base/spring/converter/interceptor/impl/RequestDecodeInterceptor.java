package com.ycs.base.spring.converter.interceptor.impl;

import java.io.IOException;

import com.ycs.basbo.constants.Constants;
import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.base.encrypt.XXTEA;
import com.ycs.base.log4j.logger.HiITFLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.converter.interceptor.IRequestConvInterceptor;

public class RequestDecodeInterceptor implements IRequestConvInterceptor {

	@Override
	public byte[] preHandler(byte[] inputMessage) throws IOException {
		HiTransactionInfo hiTransInfo = HiTransactionInfo.getCurInstance();
		if (hiTransInfo.canLog()) {
			HiITFLogger.info("receive code data[" + new String(inputMessage) + "]");
		}

		if ("Y".equals(hiTransInfo.getPwdSwitch())) {
			inputMessage = XXTEA.decryptWithBase64(inputMessage, "J58l2qudG8uCeae4JcWBl2en9zv7xvFa".getBytes());
			if (hiTransInfo.canLog()) {
				HiITFLogger.info("receive decode data[" + new String(inputMessage) + "]");
			}
		} else if (hiTransInfo.canLog()) {
			HiITFLogger.info("receive decode data[" + new String(inputMessage) + "]");
		}

		return inputMessage;
	}

	@Override
	public Object afterHandler(Object object) throws IOException {
		if (object instanceof BaseRequestDto) {
			BaseRequestDto request = (BaseRequestDto) object;
			HiRequestInfo requestInfo = HiRequestInfo.getCurInstance();
			requestInfo.setAppCnl(request.getChannelId());
			requestInfo.setAppVersion(request.getAppVersion());
			requestInfo.setTermId(request.getTermId());
			requestInfo.setTermType(request.getTermTyp());
			requestInfo.setTermVersion(request.getOsVersion());
			if (Constants.SESSION_STATUS_ON.equals(SystemPropertyConfigure.getParams(Constants.SESSION_RUN_MODE))) {
				request.setUsrNo(requestInfo.getUsrNo());
			}
		}

		return object;
	}

}
