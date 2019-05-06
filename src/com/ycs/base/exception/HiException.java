package com.ycs.base.exception;

import com.ycs.basbo.constants.HiMsgCdConstants;

public class HiException extends Exception {
	private static final long serialVersionUID = 3085910745819133727L;
	protected String code;
	protected Throwable nestedException;
	
	public String getCode() {
		return this.code;
	}
	
	public HiException(String message) {
		super(message);
		this.nestedException = null;
		this.code = HiMsgCdConstants.SYS_FAIL;
	}
	
	public HiException(String code, String message) {
		this(message);
		this.code = code;
	}
	
	public HiException(Throwable nestedException) {
		super(nestedException);
		this.nestedException = null;
		this.nestedException = nestedException;
		this.code = HiMsgCdConstants.SYS_FAIL;
	}
	
	public HiException(String message, Throwable throwable) {
		this(message);
		this.nestedException = throwable;
	}

	public HiException(String code, String message, Throwable throwable) {
		this(message, throwable);
		this.code = code;
	}

	public Throwable getNestedException() {
		return this.nestedException;
	}

}
