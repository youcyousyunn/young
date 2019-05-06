package com.ycs.work.wechat.itf.convertor;

import com.ycs.work.wechat.itf.dto.BaseITFRequestDto;
import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFConvertException;

/**
 * @description 微信相关转换器
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface WxIConvertor {
    
    /**
     * 将字节数据转换为接口传输对象
     * @param response
     * @return
     * @throws HiITFConvertException
     */
    public <T extends BaseITFResponseDto> T read(byte[] response,
            Class<T> dtoClazz) throws HiITFConvertException;

    /**
     * 将接口传输对象转换为字节数据
     * @param request
     * @return
     * @throws HiITFConvertException
     */
    public <T extends BaseITFRequestDto> T write(T request)
            throws HiITFConvertException;
}
