package com.ycs.work.wechat.itf.client;

import java.util.Map;

import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFException;

public interface WxIClient {
    public String getClientName();

    /**
     * 发送请求
     * @param reqeustDto
     * @return
     * @throws HiITFException
     */
    public <T extends BaseITFResponseDto> T send(Map<?, ?> pramMap, Class<T> responseClazz, String bodyTyp, String methodTyp) throws HiITFException;

    /**
     * 发送数据到指定端
     * @param reqeustDto
     * @param endPoint
     * @return
     * @throws HiITFException
     */
    public <T extends BaseITFResponseDto> T send(Map<?, ?> paramMap, String epId, Class<T> responseClazz, String bodyTyp, String methodTyp)
            throws HiITFException;
}
