package com.ycs.base.log4j.logger;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ycs.base.context.HiContext;
import com.ycs.base.context.HiRequestInfo;

public abstract class AbsLogger {

	protected static String log(String msg) {
		return String.format("MsgId[%s], %s", new Object[]{HiContext.getCurrentContext().getRequestId(), msg});
	}

	protected static String busErrLog(String errCd, String errInf) {
		return String.format("MsgId[%s], errCd[%s], errInf[%s]",
				new Object[]{HiContext.getCurrentContext().getRequestId(), StringUtils.trimToEmpty(errCd),
						StringUtils.trimToEmpty(errInf)});
	}

	protected static String httpLog(String msg) {
		return String.format("MsgId[%s], Req[%s] %s", new Object[]{HiContext.getCurrentContext().getRequestId(),
				HiRequestInfo.getCurInstance().getCode(), StringUtils.trimToEmpty(msg)});
	}

	protected static String jobLog(String msg) {
		return String.format("MsgId[%s], Job[%s] %s", new Object[]{HiContext.getCurrentContext().getRequestId(),
				HiRequestInfo.getCurInstance().getCode(), StringUtils.trimToEmpty(msg)});
	}

	protected static String boEnter(String method, Object[] args) {
		return String.format("MsgId[%s], method[%s], param[%s]",
				new Object[]{HiContext.getCurrentContext().getRequestId(), method,
						StringUtils.trimToEmpty(StringUtils.join(args, "|"))});
	}

	protected static String boExit(String method, Object retVal, long execTime) {
		return String.format("MsgId[%s], method[%s], return[%s], exec %sms.",
				new Object[]{HiContext.getCurrentContext().getRequestId(), method,
						StringUtils.trimToEmpty(String.valueOf(getString(retVal))), Long.valueOf(execTime)});
	}

	private static String getString(Object retVal) {
		String retStr = null;
		if (null == retVal) {
			retStr = null;
		} else if (!retVal.getClass().isArray()) {
			retStr = String.valueOf(retVal);
		} else if (retVal instanceof byte[]) {
			retStr = new String((byte[]) ((byte[]) retVal));
		} else if (retVal instanceof char[]) {
			retStr = new String((char[]) ((char[]) retVal));
		} else if (retVal instanceof int[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((int[]) ((int[]) retVal)), "|");
		} else if (retVal instanceof long[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((long[]) ((long[]) retVal)), "|");
		} else if (retVal instanceof short[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((short[]) ((short[]) retVal)), "|");
		} else if (retVal instanceof double[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((double[]) ((double[]) retVal)), "|");
		} else if (retVal instanceof float[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((float[]) ((float[]) retVal)), "|");
		} else if (retVal instanceof boolean[]) {
			retStr = StringUtils.join(ArrayUtils.toObject((boolean[]) ((boolean[]) retVal)), "|");
		} else {
			Object[] tmp = (Object[]) ((Object[]) retVal);
			retStr = StringUtils.join(tmp, "|");
		}

		return retStr;
	}

}
