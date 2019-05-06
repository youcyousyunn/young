package com.ycs.base.spring.jms.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.domain.pojo.JmsPojo;
import com.ycs.base.log4j.logger.HiJmsLogger;

@Component
public class HiCommonProducer {

	public static final String SYS_EXG = "sysExg";
	public static final String SYS_QUEUE = "sysQueue";
	public static final String LOG_QUEUE = "logQueue";
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void invokeService(String routeKey, JmsPojo jmsPojo) {
		try {
			HiJmsLogger.productInfo(jmsPojo.toString());
			this.amqpTemplate.convertAndSend(SYS_EXG, routeKey, jmsPojo);
		} catch (AmqpException e) {
			HiJmsLogger.productError(jmsPojo.toString(), e);
		}
	}

}
