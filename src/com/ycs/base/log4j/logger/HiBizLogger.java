package com.ycs.base.log4j.logger;

import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;
import com.ycs.base.property.SystemPropertyConfigure;

public class HiBizLogger extends AbsLogger {
	private static Logger busLogger = HiLog.getLogger("BIZ.trc");

	private static boolean canLog() {
		HiTransactionInfo transactionInfo = HiTransactionInfo.getCurInstance();
		return null != transactionInfo ? transactionInfo.canLog() : SystemPropertyConfigure.isDevEnv();
	}

	public static void debug(String msg, Throwable t) {
		if (canLog()) {
			busLogger.debug(log(msg), t);
		}
	}

	public static void debug(String msg) {
		if (canLog()) {
			busLogger.debug(log(msg));
		}
	}

	public static void info(String msg, Throwable t) {
		if (canLog()) {
			busLogger.info(log(msg), t);
		}
	}

	public static void info(String msg) {
		if (canLog()) {
			busLogger.info(log(msg));
		}
	}

	public static void warn(String msg, Throwable t) {
		if (canLog()) {
			busLogger.warn(log(msg), t);
		}
	}

	public static void warn(String msg) {
		if (canLog()) {
			busLogger.warn(log(msg));
		}
	}

	public static void error(String errCd, String errInf, Throwable t) {
		busLogger.error(busErrLog(errCd, errInf), t);
	}

	public static void error(String errCd, String errInf) {
		busLogger.error(busErrLog(errCd, errInf));
	}

	public static void fatal(String errCd, String errInf, Throwable t) {
		busLogger.fatal(busErrLog(errCd, errInf), t);
	}

	public static void fatal(String errCd, String errInf) {
		busLogger.fatal(busErrLog(errCd, errInf));
	}

}
