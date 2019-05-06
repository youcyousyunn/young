package com.ycs.base.log4j;

import java.io.Serializable;

import org.apache.log4j.Priority;

public class Level extends Priority implements Serializable {
	static final long serialVersionUID = 3491141966387921974L; // 序列化ID
	public static final int TRACE_INT = 5000;
	public static final int INFO_INT2 = 20002;
	public static final int INFO_INT3 = 20003;
	public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
	public static final Level FATAL = new Level('썐', "FATAL", 0);
	public static final Level ERROR = new Level('鱀', "ERROR", 3);
	public static final Level WARN = new Level(30000, "WARN", 4);
	public static final Level INFO = new Level(20000, "INFO", 6);
	public static final Level INFO2 = new Level(20002, "INFO2", 6);
	public static final Level INFO3 = new Level(20003, "INFO3", 6);
	public static final Level DEBUG = new Level(10000, "DEBUG", 7);
	public static final Level TRACE = new Level(5000, "TRACE", 7);
	public static final Level ALL = new Level(Integer.MIN_VALUE, "ALL", 7);

	protected Level(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}

	public static Level toLevel(String sArg) {
		return toLevel(sArg, DEBUG);
	}

	public static Level toLevel(int val) {
		return toLevel(val, DEBUG);
	}

	public static Level toLevel(int val, Level defaultLevel) {
		switch (val) {
			case Integer.MIN_VALUE :
				return ALL;
			case 5000 :
				return TRACE;
			case 10000 :
				return DEBUG;
			case 20000 :
				return INFO;
			case 20002 :
				return INFO2;
			case 20003 :
				return INFO3;
			case 30000 :
				return WARN;
			case 40000 :
				return ERROR;
			case 50000 :
				return FATAL;
			case Integer.MAX_VALUE :
				return OFF;
			default :
				return defaultLevel;
		}
	}

	public static Level toLevel(String sArg, Level defaultLevel) {
		if (sArg == null) {
			return defaultLevel;
		} else {
			String s = sArg.toUpperCase();
			return s.equals("ALL")
					? ALL
					: (s.equals("DEBUG")
							? DEBUG
							: (s.equals("INFO")
									? INFO
									: (s.equals("INFO2")
											? INFO2
											: (s.equals("INFO3")
													? INFO3
													: (s.equals("WARN")
															? WARN
															: (s.equals("ERROR")
																	? ERROR
																	: (s.equals("FATAL")
																			? FATAL
																			: (s.equals("OFF")
																					? OFF
																					: (s.equals("TRACE")
																							? TRACE
																							: defaultLevel)))))))));
		}
	}

}
