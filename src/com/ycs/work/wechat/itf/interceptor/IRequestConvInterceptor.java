package com.ycs.work.wechat.itf.interceptor;

import com.ycs.work.wechat.itf.dto.BaseITFRequestDto;
import com.ycs.work.wechat.itf.exception.HiITFConvertException;

/**
 * @description 请求转换拦截器
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface IRequestConvInterceptor {

    /**
     * 转换前处理
     * @param data
     * @return byte[]
     * @throws HiITFConvertException
     */
    public byte[] afterHandle(byte[] data) throws HiITFConvertException;

    /**
     * 转换后处理
     * @param data
     * @return
     * @throws HiITFConvertException
     */
    public <T extends BaseITFRequestDto> T preHandle(T data)
            throws HiITFConvertException;
    
}
