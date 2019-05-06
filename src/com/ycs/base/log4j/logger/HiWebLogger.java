package com.ycs.base.log4j.logger;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiWebLogger extends AbsLogger {
	private static Logger webLogger = HiLog.getLogger("WEB.trc");

	public static void info(String msg) {
		webLogger.info(log(msg));
	}

	public static void infoWeb(String msg) {
		webLogger.info(httpLog(msg));
	}

	public static void warnWeb(String msg) {
		webLogger.warn(httpLog(msg));
	}
	
}
