package com.ycs.base.exception;

/**
 * @description 业务返回异常类
 * @author youcyousyunn
 * @date 2018年11月30日
 */
public class HiBusinessReturnException extends HiRollbackException {
	private static final long serialVersionUID = -5588437933278364249L;
	
	public HiBusinessReturnException(String code, String message) {
		super(message);
		this.code = code;
	}

	public HiBusinessReturnException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

}
