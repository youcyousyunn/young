package com.ycs.base.log4j.logger;

import java.util.concurrent.ConcurrentHashMap;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiBoLogger extends AbsLogger {
	private Logger logger;
	private static ConcurrentHashMap loggerCache = new ConcurrentHashMap();

	private HiBoLogger(String logId) {
		this.logger = HiLog.getLogger(logId + ".trc");
	}

	public static synchronized HiBoLogger getLogger(String cId) {
		if (loggerCache.contains(cId)) {
			return (HiBoLogger) loggerCache.get(cId);
		} else {
			HiBoLogger logger = new HiBoLogger(cId);
			loggerCache.put(cId, logger);
			return logger;
		}
	}

	public void infoEnter(String method, Object[] args) {
		this.logger.info(boEnter(method, args));
	}

	public void infoExit(String method, Object retVal, long time) {
		this.logger.info(boExit(method, retVal, time));
	}

}
