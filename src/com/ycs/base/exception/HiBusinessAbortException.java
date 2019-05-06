package com.ycs.base.exception;

/**
 * @description 业务中止异常类
 * @author youcyousyunn
 * @date 2018年3月27日
 */
public class HiBusinessAbortException extends HiRollbackException {
	private static final long serialVersionUID = 7397216793117790167L;

	public HiBusinessAbortException(String code, String message) {
		super(message);
		this.code = code;
	}

	public HiBusinessAbortException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

}
