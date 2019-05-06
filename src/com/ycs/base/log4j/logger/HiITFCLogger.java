package com.ycs.base.log4j.logger;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.log4j.HiLog;
import com.ycs.base.log4j.Logger;

public class HiITFCLogger extends AbsLogger {
	private Logger logger;
	private static ConcurrentHashMap loggerCache = new ConcurrentHashMap();

	private HiITFCLogger(String cId) {
		this.logger = HiLog.getLogger(cId + ".trc");
	}

	public static synchronized HiITFCLogger getCLogger(String cId) {
		if (loggerCache.contains(cId)) {
			return (HiITFCLogger) loggerCache.get(cId);
		} else {
			HiITFCLogger logger = new HiITFCLogger(cId);
			loggerCache.put(cId, logger);
			return logger;
		}
	}

	public void warn(String msg, Throwable e) {
		this.logger.warn(log(msg), e);
	}

	public void sign(String data, String signature) {
		this.logger.info(log(String.format("原报文[%d][%s],签名/加密[%s]",
				new Object[]{Integer.valueOf(data == null ? 0 : data.getBytes().length), data, signature})));
	}

	public void sendInfo(String endPoint, byte[] data) {
		this.logger.info(log(String.format("[%s]:发送数据[%d][%s]",
				new Object[]{endPoint, Integer.valueOf(data.length), StringUtils.trimToEmpty(new String(data))})));
	}

	public void recvInfo(String endPoint, long totms, Object retCode, byte[] data) {
		this.logger
				.info(log(String.format("[%s]:请求时间[%dms], 返回结果[%s], 接收数据[%d][%s]",
						new Object[]{endPoint, Long.valueOf(totms), retCode,
								Integer.valueOf(null == data ? -1 : data.length),
								StringUtils.trimToEmpty(new String(data))})));
	}

	public void recvError(String endPoint, long totms, Object retCode, byte[] data) {
		this.logger
				.info(log(String.format("[%s]:请求时间[%dms], 返回结果[%s], 接收数据[%d][%s]",
						new Object[]{endPoint, Long.valueOf(totms), retCode,
								Integer.valueOf(null == data ? -1 : data.length),
								StringUtils.trimToEmpty(new String(data))})));
	}

}
