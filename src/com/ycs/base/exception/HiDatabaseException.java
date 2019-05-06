package com.ycs.base.exception;

import com.ycs.basbo.constants.HiMsgCdConstants;

/**
 * @description 数据库异常类
 * @author youcyousyunn
 * @date 2018年11月30日
 */
public class HiDatabaseException extends HiException {
	private static final long serialVersionUID = -7860215464130838522L;

	public HiDatabaseException(String message) {
		super(message);
		this.code = HiMsgCdConstants.SYS_FAIL;
	}

	public HiDatabaseException(String code, String message) {
		this(message);
		this.code = code;
	}

	public HiDatabaseException(Throwable nestedException) {
		super(nestedException);
		this.nestedException = nestedException;
		this.code = HiMsgCdConstants.SYS_FAIL;
	}

	public HiDatabaseException(String message, Throwable throwable) {
		this(message);
		this.nestedException = throwable;
	}

	public HiDatabaseException(String code, String message, Throwable throwable) {
		this(message, throwable);
		this.code = code;
	}

}
