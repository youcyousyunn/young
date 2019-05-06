package com.ycs.work.wechat.itf.interceptor;

import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFConvertException;

/**
 * @description 返回转换拦截器
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface IResponseConvInterceptor {

    /**
     * 转换前处理
     * @param data
     * @return byte[]
     * @throws HiITFConvertException
     */
    public byte[] preHandle(byte[] data) throws HiITFConvertException;

    /**
     * 转换后处理
     * @param data
     * @return 
     * @throws HiITFConvertException
     */
    public <T extends BaseITFResponseDto> T afterHandle(T data)
            throws HiITFConvertException;
    
}
