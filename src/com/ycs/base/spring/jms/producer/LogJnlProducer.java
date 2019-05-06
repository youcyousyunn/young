package com.ycs.base.spring.jms.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.log4j.logger.HiBizLogger;

@Component
public class LogJnlProducer {

	public static final String LOG_EXG = "logExg";
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void log(HiRequestInfo reqInfo) {
		try {
			this.amqpTemplate.convertAndSend(LOG_EXG, (String) null, reqInfo);
		} catch (AmqpException e) {
			HiBizLogger.info("LogJnlProducer error", e);
		}
	}

}
