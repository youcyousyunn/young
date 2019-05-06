package com.ycs.base.spring.converter.interceptor.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.encrypt.XXTEA;
import com.ycs.base.log4j.logger.HiITFLogger;
import com.ycs.base.spring.bo.PubMsgBo;
import com.ycs.base.spring.converter.interceptor.IResponseConvInterceptor;
import com.ycs.base.spring.domain.po.PubMsgPo;

public class MsgProcRspConvInterceptor implements IResponseConvInterceptor {

	@Autowired
	private PubMsgBo pubMsgBo;

	@Override
	public void castPreHandle(Object object) {
		if (object instanceof BaseResponseDto) {
			BaseResponseDto bean = (BaseResponseDto) object;
			HiRequestInfo.getCurInstance().setMsgCd(bean.getRspCd());
			PubMsgPo pubMsgPo = this.pubMsgBo.queryMsgInf(bean.getRspCd());
			if (null != pubMsgPo) {
				if (null != bean.getRspInf() && !"".equals(bean.getRspInf())) {
					bean.setRspInf(pubMsgPo.getBmsgInf() + bean.getRspInf());
				} else {
					bean.setRspInf(pubMsgPo.getBmsgInf());
				}

				HiRequestInfo.getCurInstance().setMsgInf(pubMsgPo.getMsgInf());
			}
		}

	}

	@Override
	public byte[] castAfterHandle(byte[] jsonValue) {
		HiITFLogger.info("response msg data[" + new String(jsonValue) + "]");
		HiTransactionInfo hiTransInfo = HiTransactionInfo.getCurInstance();
		if ("Y".equals(hiTransInfo.getPwdSwitch())) {
			jsonValue = XXTEA.encryptWithBase64(jsonValue, "J58l2qudG8uCeae4JcWBl2en9zv7xvFa".getBytes()).getBytes();
			HiITFLogger.info("response encrypt data[" + new String(jsonValue) + "]");
		} else {
			HiITFLogger.info("response encrypt data[" + new String(jsonValue) + "]");
		}

		HiRequestInfo.getCurInstance().setResponseData(jsonValue);
		return jsonValue;
	}

}
