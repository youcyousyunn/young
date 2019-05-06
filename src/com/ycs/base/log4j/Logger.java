package com.ycs.base.log4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ycs.base.encrypt.ByteConvHelper;
import com.ycs.base.exception.HiException;
import com.ycs.base.property.SystemPropertyConfigure;

public class Logger implements Closeable {
	public static int DEFAULT_LIMIT_SIZE = 20971520;
	public static int DEFAULT_LIMIT_LINES = 20;
	public static int DEFAULT_QUEUE_MAX_SIZE = 1000;
	public static Logger dummyLogger;
	public static final String SYS_LOG = "SYS.log";
	private static SimpleDateFormat sdf;
	protected int limitsLines;
	protected int limitsSize;
	protected Level level;
	protected IFileName fileName;
	protected boolean hasOfHead;
	protected ILogCache logCache;
	protected String msgId;
	
	static {
		dummyLogger = new Logger(new LogFileName("DUMMY.log"), Level.ERROR);
		sdf = new SimpleDateFormat("MM-dd HH:mm:ss,SSS");
	}

	public static Logger getLogger(String name) {
		return new Logger(new TrcFileName(name), Level.DEBUG);
	}

	public Logger(String name) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(new TrcFileName(name));
	}

	public Logger(String name, String level) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(new TrcFileName(name), toLevel(level));
	}

	public Logger(String name, Level level) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(new TrcFileName(name), level);
	}

	public Logger(IFileName name) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(name);
	}

	public Logger(IFileName name, String level) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(name, toLevel(level));
	}

	public Logger(IFileName name, Level level) {
		this.limitsLines = DEFAULT_LIMIT_LINES;
		this.limitsSize = DEFAULT_LIMIT_SIZE;
		this.level = Level.DEBUG;
		this.fileName = null;
		this.hasOfHead = true;
		this.construct(name, level);
	}

	protected void construct(IFileName name) {
		String tmp1 = SystemPropertyConfigure.getProperty("log.level", "DEBUG");
		this.construct(name, toLevel(tmp1));
	}

	protected void construct(IFileName name, Level level) {
		String tmp2 = SystemPropertyConfigure.getProperty("log.limits_lines", "20");
		this.limitsLines = NumberUtils.toInt(tmp2);
		tmp2 = SystemPropertyConfigure.getProperty("log.limits_size", "20");
		this.limitsSize = NumberUtils.toInt(tmp2) * 1024 * 1024;
		if (this.limitsSize <= 0) {
			this.limitsSize = DEFAULT_LIMIT_SIZE;
		}

		this.level = level;
		this.fileName = name;
		this.logCache = new DirectLogCache(this.limitsSize);
	}

	public void setLevel(org.apache.log4j.Level level) {
		this.level = Level.toLevel(level.toInt());
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}

	public static Logger getLogger(Class clazz) {
		String name = clazz.getName();
		int idx1 = name.lastIndexOf(46);
		if (idx1 != -1) {
			name = name.substring(idx1 + 1);
		}

		return getLogger(name);
	}

	public boolean isDebugEnabled() {
		return this.level.toInt() <= 10000;
	}

	public void debug(Object msg) {
		this.debug(new Object[]{msg});
	}

	public void debug(Object msg1, Object msg2) {
		this.debug(new Object[]{msg1, msg2});
	}

	public void debug(Object msg1, Object msg2, Object msg3) {
		this.debug(new Object[]{msg1, msg2, msg3});
	}

	public void debug(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.debug(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void debug(Object msg, Throwable t) {
		this.debug(new Object[]{msg}, t);
	}

	public void debug(Object msg1, Object msg2, Throwable t) {
		this.debug(new Object[]{msg1, msg2}, t);
	}

	public void debug(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.debug(new Object[]{msg1, msg2, msg3}, t);
	}

	public void debug(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.debug(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void debug(Object[] msgs) {
		if (this.isDebugEnabled()) {
			this.log(Level.DEBUG, msgs);
		}
	}

	public void debug(Object[] msgs, Throwable t) {
		if (this.isDebugEnabled()) {
			this.log(Level.DEBUG, msgs, t);
		}
	}

	public boolean isInfoEnabled() {
		return this.level.toInt() <= 20000;
	}

	public void info(Object msg) {
		this.info(new Object[]{msg});
	}

	public void info(Object msg1, Object msg2) {
		this.info(new Object[]{msg1, msg2});
	}

	public void info(Object msg1, Object msg2, Object msg3) {
		this.info(new Object[]{msg1, msg2, msg3});
	}

	public void info(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.info(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void info(Object msg, Throwable t) {
		this.info(new Object[]{msg}, t);
	}

	public void info(Object msg1, Object msg2, Throwable t) {
		this.info(new Object[]{msg1, msg2}, t);
	}

	public void info(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.info(new Object[]{msg1, msg2, msg3}, t);
	}

	public void info(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.info(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void info(Object[] msgs, Throwable t) {
		if (this.isInfoEnabled()) {
			this.log(Level.INFO, msgs, t);
		}
	}

	public void info(Object[] msgs) {
		if (this.isInfoEnabled()) {
			this.log(Level.INFO, msgs);
		}
	}

	public boolean isInfo2Enabled() {
		return this.level.toInt() <= 20002;
	}

	public void info2(Object msg) {
		this.info2(new Object[]{msg});
	}

	public void info2(Object msg1, Object msg2) {
		this.info2(new Object[]{msg1, msg2});
	}

	public void info2(Object msg1, Object msg2, Object msg3) {
		this.info2(new Object[]{msg1, msg2, msg3});
	}

	public void info2(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.info2(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void info2(Object msg, Throwable t) {
		this.info2(new Object[]{msg}, t);
	}

	public void info2(Object msg1, Object msg2, Throwable t) {
		this.info2(new Object[]{msg1, msg2}, t);
	}

	public void info2(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.info2(new Object[]{msg1, msg2, msg3}, t);
	}

	public void info2(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.info2(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void info2(Object[] msgs, Throwable t) {
		if (this.isInfo2Enabled()) {
			this.log(Level.INFO2, msgs, t);
		}
	}

	public void info2(Object[] msgs) {
		if (this.isInfo2Enabled()) {
			this.log(Level.INFO2, msgs);
		}
	}

	public boolean isInfo3Enabled() {
		return this.level.toInt() <= 20003;
	}

	public void info3(Object msg) {
		this.info3(new Object[]{msg});
	}

	public void info3(Object msg1, Object msg2) {
		this.info3(new Object[]{msg1, msg2});
	}

	public void info3(Object msg1, Object msg2, Object msg3) {
		this.info3(new Object[]{msg1, msg2, msg3});
	}

	public void info3(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.info3(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void info3(Object msg, Throwable t) {
		this.info3(new Object[]{msg}, t);
	}

	public void info3(Object msg1, Object msg2, Throwable t) {
		this.info3(new Object[]{msg1, msg2}, t);
	}

	public void info3(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.info3(new Object[]{msg1, msg2, msg3}, t);
	}

	public void info3(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.info3(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void info3(Object[] msgs, Throwable t) {
		if (this.isInfo3Enabled()) {
			this.log(Level.INFO3, msgs, t);
		}
	}

	public void info3(Object[] msgs) {
		if (this.isInfo3Enabled()) {
			this.log(Level.INFO3, msgs);
		}
	}

	public boolean isWarnEnabled() {
		return this.level.toInt() <= 30000;
	}

	public void warn(Object msg) {
		this.warn(new Object[]{msg});
	}

	public void warn(Object msg1, Object msg2) {
		this.warn(new Object[]{msg1, msg2});
	}

	public void warn(Object msg1, Object msg2, Object msg3) {
		this.warn(new Object[]{msg1, msg2, msg3});
	}

	public void warn(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.warn(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void warn(Object msg, Throwable t) {
		this.warn(new Object[]{msg}, t);
	}

	public void warn(Object msg1, Object msg2, Throwable t) {
		this.warn(new Object[]{msg1, msg2}, t);
	}

	public void warn(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.warn(new Object[]{msg1, msg2, msg3}, t);
	}

	public void warn(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.warn(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void warn(Object[] msgs) {
		if (this.isWarnEnabled()) {
			this.log(Level.WARN, msgs);
		}
	}

	public void warn(Object[] msgs, Throwable t) {
		if (this.isWarnEnabled()) {
			this.log(Level.WARN, msgs, t);
		}
	}

	public boolean isErrorEnabled() {
		return this.level.toInt() <= '鱀';
	}

	public void error(Object msg) {
		this.error(new Object[]{msg});
	}

	public void error(Object msg1, Object msg2) {
		this.error(new Object[]{msg1, msg2});
	}

	public void error(Object msg1, Object msg2, Object msg3) {
		this.error(new Object[]{msg1, msg2, msg3});
	}

	public void error(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.error(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void error(Object msg, Throwable t) {
		this.error(new Object[]{msg}, t);
	}

	public void error(Object msg1, Object msg2, Throwable t) {
		this.error(new Object[]{msg1, msg2}, t);
	}

	public void error(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.error(new Object[]{msg1, msg2, msg3}, t);
	}

	public void error(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.error(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void error(Object[] msgs) {
		this.log(Level.ERROR, msgs);
	}

	public void error(Object[] msgs, Throwable t) {
		this.log(Level.ERROR, msgs, t);
	}

	public boolean isFatalEnabled() {
		return this.level.toInt() <= '썐';
	}

	public void fatal(Object msg) {
		this.fatal(new Object[]{msg});
	}

	public void fatal(Object msg1, Object msg2) {
		this.fatal(new Object[]{msg1, msg2});
	}

	public void fatal(Object msg1, Object msg2, Object msg3) {
		this.fatal(new Object[]{msg1, msg2, msg3});
	}

	public void fatal(Object msg1, Object msg2, Object msg3, Object msg4) {
		this.fatal(new Object[]{msg1, msg2, msg3, msg4});
	}

	public void fatal(Object msg, Throwable t) {
		this.fatal(new Object[]{msg}, t);
	}

	public void fatal(Object msg1, Object msg2, Throwable t) {
		this.fatal(new Object[]{msg1, msg2}, t);
	}

	public void fatal(Object msg1, Object msg2, Object msg3, Throwable t) {
		this.fatal(new Object[]{msg1, msg2, msg3}, t);
	}

	public void fatal(Object msg1, Object msg2, Object msg3, Object msg4, Throwable t) {
		this.fatal(new Object[]{msg1, msg2, msg3, msg4}, t);
	}

	public void fatal(Object[] msgs) {
		this.log(Level.FATAL, msgs);
		this.log((IFileName) (new LogFileName("SYS.log")), (Level) Level.FATAL, (Object[]) msgs);
	}

	public void fatal(Object[] msgs, Throwable t) {
		this.log(Level.FATAL, msgs, t);
		this.log(new LogFileName("SYS.log"), Level.FATAL, msgs, t);
	}

	protected void log(Level level, Object[] msgs, Throwable t) {
		this.log(this.fileName, level, msgs, t);
	}

	protected void log(IFileName name, Level level, Object[] msgs, Throwable t) {
		StringBuilder buffer = new StringBuilder(81);
		this.println(name, buffer, level, msgs);
		buffer.append(t.toString());
		buffer.append(SystemUtils.LINE_SEPARATOR);
		StackTraceElement[] elements = t.getStackTrace();

		for (int t1 = 0; elements != null && t1 < elements.length; ++t1) {
			if (this.msgId != null) {
				buffer.append(this.msgId);
				buffer.append(' ');
			}
			buffer.append("        at ");
			buffer.append(elements[t1].toString());
			buffer.append(SystemUtils.LINE_SEPARATOR);
			if (t1 == this.limitsLines && t1 < elements.length - 1) {
				if (this.msgId != null) {
					buffer.append(this.msgId);
					buffer.append(' ');
				}

				buffer.append("        Truncated...........");
				buffer.append(SystemUtils.LINE_SEPARATOR);
				break;
			}
		}
		Throwable arg9;
		if (t instanceof HiException) {
			arg9 = ((HiException) t).getNestedException();
		} else {
			arg9 = t.getCause();
		}
		while (arg9 != null) {
			buffer.append("Nested Exception:");
			buffer.append(SystemUtils.LINE_SEPARATOR);
			buffer.append(arg9.toString());
			buffer.append(SystemUtils.LINE_SEPARATOR);
			elements = arg9.getStackTrace();
			for (int e = 0; elements != null && e < elements.length; ++e) {
				if (this.msgId != null) {
					buffer.append(this.msgId);
					buffer.append(' ');
				}
				buffer.append("           ");
				buffer.append(elements[e].toString());
				buffer.append(SystemUtils.LINE_SEPARATOR);
				if (e == this.limitsLines && e < elements.length - 1) {
					if (this.msgId != null) {
						buffer.append(this.msgId);
						buffer.append(' ');
					}
					buffer.append("        Truncated..........");
					buffer.append(SystemUtils.LINE_SEPARATOR);
					break;
				}
			}
			if (arg9 instanceof HiException) {
				arg9 = ((HiException) arg9).getNestedException();
			} else {
				arg9 = arg9.getCause();
			}
		}

		try {
			this.logCache.put(new LogInfo(name, buffer));
		} catch (IOException arg8) {
			arg8.printStackTrace();
		}
	}

	protected void log(IFileName name, Level level, Object[] msgs) {
		StringBuilder buffer = new StringBuilder(81);
		this.println(name, buffer, level, msgs);
		try {
			this.logCache.put(new LogInfo(name, buffer));
		} catch (IOException arg5) {
			;
		}
	}

	protected void log(Level level, Object[] msgs) {
		this.log(this.fileName, level, msgs);
	}

	protected void println(IFileName name, StringBuilder buffer, Level level, Object[] msgs) {
		if (this.msgId != null) {
			;
		}

		if (this.hasOfHead) {
			buffer.append(sdf.format(new Date()));
			buffer.append(' ');
			buffer.append(level.toString());
			buffer.append(' ');
			buffer.append('-');
			buffer.append(' ');
		}

		int i;
		for (i = 0; i < msgs.length; ++i) {
			if (msgs[i] instanceof byte[]) {
				buffer.append(ByteConvHelper.binToAscStr((byte[]) ((byte[]) msgs[i])));
			} else {
				buffer.append(String.valueOf(msgs[i]));
			}

			if (i != msgs.length - 1) {
				buffer.append(':');
			}
		}

		if (name.getLineLength() != -1) {
			for (i = buffer.toString().getBytes().length; i < name.getLineLength(); ++i) {
				buffer.append(' ');
			}
		}

		buffer.append(SystemUtils.LINE_SEPARATOR);
	}

	public static Level toLevel(String strLev) {
		Level level = null;
		if (!StringUtils.equals(strLev, "0") && !StringUtils.equalsIgnoreCase(strLev, "no")) {
			if (!StringUtils.equals(strLev, "1") && !StringUtils.equalsIgnoreCase(strLev, "yes")) {
				if (StringUtils.equals(strLev, "2")) {
					level = Level.INFO2;
				} else if (StringUtils.equals(strLev, "3")) {
					level = Level.INFO3;
				} else if (StringUtils.equals(strLev, "4")) {
					level = Level.WARN;
				} else if (StringUtils.equals(strLev, "5")) {
					level = Level.ERROR;
				} else {
					level = Level.toLevel(strLev, Level.ERROR);
				}
			} else {
				level = Level.INFO;
			}
		} else {
			level = Level.ERROR;
		}

		return level;
	}

	public void setHasOfHead(boolean hasOfHead) {
		this.hasOfHead = hasOfHead;
	}

	public void clear() {
		this.logCache.clear();
	}

	public void flush() {
		try {
			this.logCache.flush();
		} catch (IOException arg1) {
			arg1.printStackTrace();
		}
	}

	public void close() {
		try {
			this.logCache.close();
		} catch (IOException arg1) {
			arg1.printStackTrace();
		}
	}

	public void setFixSizeable(boolean fixsizeable) {
		this.fileName.setFixedSizeable(fixsizeable);
	}

	public void setLineLength(int lineLength) {
		this.fileName.setLineLength(lineLength);
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
