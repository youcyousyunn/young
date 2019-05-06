package com.ycs.base.spring.jms.consumer;

import java.sql.Date;
import java.sql.Time;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.spring.bo.LogJnlBo;
import com.ycs.base.spring.domain.po.LogJnlPo;

@Component
public class LogJnlConsumer extends AbsConsumer {
	@Autowired
	private LogJnlBo logJnlBo;

	public LogJnlConsumer() {
		this.normal = false;
	}

	protected Object handle(Object convertedMessage, boolean isRedelivered)
			throws HiBusinessReturnException, HiBusinessAbortException {
		HiRequestInfo reqInf = (HiRequestInfo) convertedMessage;
		LogJnlPo logJnlPo = new LogJnlPo();
		logJnlPo.setAppCnl(StringUtils.trimToEmpty(reqInf.getAppCnl()));
		logJnlPo.setAppVer(StringUtils.trimToEmpty(reqInf.getAppVersion()));
		logJnlPo.setMsgCd(StringUtils.trimToEmpty(reqInf.getMsgCd()));
		logJnlPo.setMsgId(StringUtils.trimToEmpty(reqInf.getMsgId()));
		logJnlPo.setMsgInf(StringUtils.trimToEmpty(reqInf.getMsgInf()));
		logJnlPo.setNodId(StringUtils.trimToEmpty(reqInf.getNodId()));
		logJnlPo.setRegId(StringUtils.trimToEmpty(reqInf.getInsId()));
		if (reqInf.getRequestData() != null) {
			logJnlPo.setReqDat(new String(reqInf.getRequestData()));
			if (1024 <= reqInf.getRequestData().length) {
				logJnlPo.setReqDat((new String(reqInf.getRequestData())).substring(0, 1000));
			}
		} else {
			logJnlPo.setReqDat("");
		}

		logJnlPo.setReqDt(new Date(reqInf.getRequestTime()));
		logJnlPo.setReqTm(new Time(reqInf.getRequestTime()));
		if (null != reqInf.getResponseData()) {
			logJnlPo.setRspDat(new String(reqInf.getResponseData()));
			if (1024 <= reqInf.getResponseData().length) {
				logJnlPo.setRspDat((new String(reqInf.getResponseData())).substring(0, 1000));
			}
		} else {
			logJnlPo.setRspDat("");
		}

		logJnlPo.setRspDt(new Date(reqInf.getResponseTime()));
		logJnlPo.setRspTm(new Time(reqInf.getResponseTime()));
		logJnlPo.setSip(StringUtils.trimToEmpty(reqInf.getRemoteIp()));
		logJnlPo.setTotTm((int) (reqInf.getResponseTime() - reqInf.getRequestTime()));
		logJnlPo.setTrmId(StringUtils.trimToEmpty(reqInf.getTermId()));
		logJnlPo.setTrmTyp(StringUtils.trimToEmpty(reqInf.getTermType()));
		logJnlPo.setCode(StringUtils.trimToEmpty(reqInf.getCode()));
		logJnlPo.setCodeDesc(StringUtils.trimToEmpty(reqInf.getDesc()));
		logJnlPo.setUserAgent(StringUtils.trimToEmpty(reqInf.getUserAgent()));
		logJnlPo.setUsrNo(StringUtils.trimToEmpty(reqInf.getUsrNo()));
		logJnlPo.setUsrToken(StringUtils.trimToEmpty(reqInf.getUsrToken()));
		logJnlPo.setTrmVer(StringUtils.trimToEmpty(reqInf.getTermVersion()));
		int ret = this.logJnlBo.saveLog(logJnlPo);
		if (ret != 1) {
			;
		}
		return Integer.valueOf(ret);
	}
}