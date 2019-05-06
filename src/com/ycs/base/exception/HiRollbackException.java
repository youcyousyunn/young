package com.ycs.base.exception;

import com.ycs.basbo.constants.HiMsgCdConstants;

/**
 * @description 回滚异常类
 * @author youcyousyunn
 * @date 2018年11月30日
 */
public class HiRollbackException extends HiException {
	private static final long serialVersionUID = -1572015922578124563L;

	public HiRollbackException(String message) {
		super(message);
		this.code = HiMsgCdConstants.SYS_FAIL;
	}

	public HiRollbackException(String code, String message) {
		this(message);
		this.code = code;
	}

	public HiRollbackException(Throwable nestedException) {
		super(nestedException);
		this.nestedException = nestedException;
		this.code = HiMsgCdConstants.SYS_FAIL;
	}

	public HiRollbackException(String message, Throwable throwable) {
		this(message);
		this.nestedException = throwable;
	}

	public HiRollbackException(String code, String message, Throwable throwable) {
		this(message, throwable);
		this.code = code;
	}

}
