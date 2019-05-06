package com.ycs.base.spring.jms.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;

import com.rabbitmq.client.Channel;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.context.HiContext;
import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.domain.pojo.JmsPojo;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;
import com.ycs.base.spring.SpringContextHolder;

/**
 * @description 消息消费者抽象类
 * @author youcyousyunn
 * @date 2018年10月14日
 */
public abstract class AbsConsumer extends AbstractAdaptableMessageListener {
	private static Logger logger = HiLog.getLogger("JMS_CONSUMER.trc");
	protected boolean normal = true;
	protected String jobId;
	protected String messageConverterId = "rabbitmqJsonMessageConvertor";

	public AbsConsumer() {
		this.setMessageConverter((MessageConverter) SpringContextHolder.getBean(this.messageConverterId));
	}

	public void setMessageConverterId(String messageConverterId) {
		this.messageConverterId = messageConverterId;
	}

	public void onMessage(Message message, Channel channel) throws Exception {
		Object convertedMessage = null;
		try {
			convertedMessage = this.extractMessage(message);
			this.beforeHandle(convertedMessage);
		} catch (Exception arg11) {
			logger.error("AbsConsumer onMessage extractMessage:" + arg11.getMessage());
		}

		Object result = null;
		try {
			result = this.handle(convertedMessage, message.getMessageProperties().isRedelivered().booleanValue());
		} catch (Exception arg9) {
			logger.error("AbsConsumer onMessage handle:" + arg9.getMessage());
		} finally {
			this.afterHandle(result, convertedMessage);
		}
	}

	protected void beforeHandle(Object convertedMessage) throws HiBusinessReturnException {
		HiContext ctx = HiContext.createContext("currentContext", (HiContext) null);
		HiContext.pushCurrentContext(ctx);
		if (this.normal) {
			;
		}

		if (convertedMessage instanceof JmsPojo) {
			JmsPojo pojo = (JmsPojo) convertedMessage;
			this.jobId = pojo.getMsgId();
			this.initReqInfo(ctx.getRequestId());
			HiTransactionInfo transactionInfo = this.initConsumer(pojo.getService(), pojo.getMethod());
			if (null == transactionInfo) {
				throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTPRAM_FAIL, "接口请求参数异常");
			}
		}
	}
	
	protected abstract Object handle(Object arg0, boolean arg1) throws HiBusinessReturnException, HiBusinessAbortException;

	protected void afterHandle(Object result, Object convertedMessage) {
		HiContext currentContext = HiContext.popCurrentContext();
		currentContext.clear();
		currentContext = null;
	}
	
	private HiRequestInfo initReqInfo(String requestId) {
		HiRequestInfo reqInf = new HiRequestInfo();
		reqInf.setMsgId(requestId);
		reqInf.setCode(this.jobId);
		reqInf.setRequestTime(System.currentTimeMillis());
		reqInf.setSysCnl("NOTICE");
		HiRequestInfo.setCurInstance(reqInf);
		return reqInf;
	}

	private HiTransactionInfo initConsumer(String appId, String modId) {
		HiTransactionInfo transactionInfo = new HiTransactionInfo();
		transactionInfo.setAppId(appId);
		transactionInfo.setModId(modId);
		transactionInfo.setTxSwitch("Y");
		transactionInfo.setLogSwitch("Y");
		transactionInfo.setCode(this.jobId);
		transactionInfo.setDesc("");
		HiRequestInfo.getCurInstance().setDesc(transactionInfo.getDesc());
		HiRequestInfo.getCurInstance().setModId(transactionInfo.getModId());
		HiTransactionInfo.setCurInstance(transactionInfo);
		return transactionInfo;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}