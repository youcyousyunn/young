package com.ycs.work.wechat.itf.exception;

public class HiITFConvertException extends HiITFException {

    /**
     * @param code
     * @param message
     * @param throwable
     */
    public HiITFConvertException(String code, String message,
            Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * @param code
     * @param message
     */
    public HiITFConvertException(String code, String message) {
        super(code, message);
    }

    /**
     * @param message
     * @param throwable
     */
    public HiITFConvertException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param message
     */
    public HiITFConvertException(String message) {
        super(message);
    }

    /**
     * @param nestedException
     */
    public HiITFConvertException(Throwable nestedException) {
        super(nestedException);
    }

}
