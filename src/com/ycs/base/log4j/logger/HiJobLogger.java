package com.ycs.base.log4j.logger;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiJobLogger extends AbsLogger {
	private static Logger logger = HiLog.getLogger("JOB.trc");

	public static void infoJob(String msg) {
		logger.info(jobLog(msg));
	}

	public static void error(String msg, Throwable t) {
		logger.error(jobLog(msg), t);
	}

}
