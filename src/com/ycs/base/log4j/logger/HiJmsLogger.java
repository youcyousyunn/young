package com.ycs.base.log4j.logger;

import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;
import com.ycs.base.property.SystemPropertyConfigure;

public class HiJmsLogger extends AbsLogger {
	private static Logger logger = HiLog.getLogger("JMS.trc");

	private static boolean canLog() {
		HiTransactionInfo transactionInfo = HiTransactionInfo.getCurInstance();
		return null != transactionInfo ? transactionInfo.canLog() : SystemPropertyConfigure.isDevEnv();
	}

	public static void debug(String msg, Throwable t) {
		if (canLog()) {
			logger.debug(log(msg), t);
		}
	}

	public static void debug(String msg) {
		if (canLog()) {
			logger.debug(log(msg));
		}
	}

	public static void productInfo(String info) {
		String msg = String.format("PRODUCER send data[%s]", new Object[]{info});
		if (canLog()) {
			logger.info(log(msg));
		}
	}

	public static void productInfo(String info, Throwable t) {
		String msg = String.format("PRODUCER send data[%s]", new Object[]{info});
		if (canLog()) {
			logger.info(log(msg), t);
		}
	}

	public static void consumInfo(String info) {
		String msg = String.format("CONSUMER receive data[%s]", new Object[]{info});
		if (canLog()) {
			logger.info(log(msg));
		}
	}

	public static void consumInfo(String info, Throwable t) {
		String msg = String.format("CONSUMER receive data[%s]", new Object[]{info});
		if (canLog()) {
			logger.info(log(msg), t);
		}
	}

	public static void productError(String info) {
		String msg = String.format("PRODUCER send data error[%s]", new Object[]{info});
		if (canLog()) {
			logger.error(log(msg));
		}
	}

	public static void productError(String info, Throwable t) {
		String msg = String.format("PRODUCER send data error[%s]", new Object[]{info});
		if (canLog()) {
			logger.error(log(msg), t);
		}
	}

	public static void consumError(String info) {
		String msg = String.format("CONSUMER receive data error[%s]", new Object[]{info});
		if (canLog()) {
			logger.error(log(msg));
		}
	}

	public static void consumError(String info, Throwable t) {
		String msg = String.format("CONSUMER receive data error[%s]", new Object[]{info});
		if (canLog()) {
			logger.error(log(msg), t);
		}
	}

}
