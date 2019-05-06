package com.ycs.base.spring.jms.consumer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.ycs.base.domain.pojo.JmsPojo;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;
import com.ycs.base.spring.SpringContextHolder;

@Component
public class HiCommonConsumer extends AbsConsumer {
	private static Logger logger = HiLog.getLogger("JMS_CONSUMER.trc");

	public HiCommonConsumer() {
		this.normal = false;
	}

	@SuppressWarnings("unchecked")
	protected Object handle(Object convertedMessage, boolean isRedelivered)
			throws HiBusinessReturnException, HiBusinessAbortException {
		JmsPojo pojo = null;
		Object result = null;
		try {
			pojo = (JmsPojo) convertedMessage;
			pojo.setRedelivered(isRedelivered);
			pojo.getTransData().put("ISDELIVER", Boolean.valueOf(isRedelivered));
			logger.info("HiCommonConsumer handle:" + pojo.getMsgId() + "\t" + isRedelivered + "\t" + pojo.getTransData());
			Object e = SpringContextHolder.getBean(pojo.getService());
			Class clazz = e.getClass();
			Method method = clazz.getDeclaredMethod(pojo.getMethod(), new Class[]{pojo.getClazz()});
			result = method.invoke(e, new Object[]{pojo.getTransData()});
		} catch (SecurityException | NoSuchMethodException arg7) {
			logger.error(pojo.toString(), arg7);
		} catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException arg8) {
			logger.error(pojo.toString(), arg8);
		}
		return result;
	}
}