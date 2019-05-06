package com.ycs.base.log4j.logger;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiSessionLogger {
	private static Logger logger = HiLog.getLogger("SESSION.trc");
	public static final String GET = "GET";
	public static final String SET = "SET";
	public static final String REMOVE = "REMOVE";

	public static void getInfo(String method, String sid, String attribute, Object value) {
		String msg = String.format("sid[%s], method[%s], attribute[%s], value[%s].",
				new Object[]{sid, method, attribute, value.toString()});
		logger.info(log(msg));
	}

	public static void addInfo(String method, String sid, String attribute, Object value) {
		String msg = String.format("sid[%s], method[%s], attribute[%s], value[%s].",
				new Object[]{sid, method, attribute, value.toString()});
		logger.info(log(msg));
	}

	public static void removeInfo(String method, String sid, String attribute) {
		String msg = String.format("sid[%s], method[%s], attribute[%s].", new Object[]{sid, method, attribute});
		logger.info(log(msg));
	}

	public static void error(String errCd, String errInf, Throwable t) {
		logger.error(busErrLog(errCd, errInf), t);
	}

	public static void error(String errCd, String errInf) {
		logger.error(busErrLog(errCd, errInf));
	}

	protected static String log(String msg) {
		return msg;
	}

	protected static String busErrLog(String errCd, String errInf) {
		return String.format(" errCd[%s], errInf[%s]",
				new Object[]{StringUtils.trimToEmpty(errCd), StringUtils.trimToEmpty(errInf)});
	}

}
