package com.ycs.base.spring.converter.interceptor.impl;

import java.io.IOException;

import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.base.spring.converter.interceptor.IRequestConvInterceptor;

public class RequestInfoInterceptor implements IRequestConvInterceptor {

	@Override
	public byte[] preHandler(byte[] inputMessage) throws IOException {
		HiRequestInfo.getCurInstance().setRequestData(inputMessage);
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
		}
		return object;
	}

}
