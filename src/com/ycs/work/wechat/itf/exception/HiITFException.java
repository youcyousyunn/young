package com.ycs.work.wechat.itf.exception;

import com.ycs.base.exception.HiException;

/**
 * @description 发送微信信息模板异常
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class HiITFException extends HiException {

    /**
     * @param code
     * @param message
     * @param throwable
     */
    public HiITFException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * @param code
     * @param message
     */
    public HiITFException(String code, String message) {
        super(code, message);
    }

    /**
     * @param message
     */
    public HiITFException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param throwable
     */
    public HiITFException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param nestedException
     */
    public HiITFException(Throwable nestedException) {
        super(nestedException);
    }

}
