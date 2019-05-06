package com.ycs.base.log4j.logger;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiITFLogger extends AbsLogger {
	private static Logger itfLogger = HiLog.getLogger("TXN.trc");

	public static void info(String msg) {
		itfLogger.info(log(msg));
	}

	public static void infoITF(String msg) {
		itfLogger.info(httpLog(msg));
	}

	public static void warnITF(String msg) {
		itfLogger.warn(httpLog(msg));
	}

}
