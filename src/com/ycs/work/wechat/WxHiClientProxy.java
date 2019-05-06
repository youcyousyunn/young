package com.ycs.work.wechat;

import java.util.Map;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.work.wechat.itf.client.WxIClient;
import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 发送微信模板消息
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class WxHiClientProxy {
    /**
     * 发送数据<br/>
     * @param clientName
     * @param requestDto
     * @param responseClazz
     * @return
     * @throws HiITFException
     */
    public static <T extends BaseITFResponseDto> T send(String clientName, Map<?, ?> pramMap, Class<T> responseClazz,
            String bodyTyp, String methodTyp) throws HiITFException {
        WxIClient client = WxHiClientManager.getClient(clientName);
        if (null == client) {
            throw new HiITFException(HiMsgCdConstants.ITF_CLIENT_NOT_EXIST, clientName);
        }
        T responseDto = client.send(pramMap, responseClazz, bodyTyp, methodTyp);
        return responseDto;
    }

    /**
     * 指定发送端，发送数据<br/>
     * @param clientName
     * @param endPointId
     * @param requestDto
     * @param responseClazz
     * @return
     * @throws HiITFException
     */
    public static <T extends BaseITFResponseDto> T send(String clientName, String endPointId, Map<?, ?> paramMap,
            Class<T> responseClazz, String bodyTyp, String methodTyp) throws HiITFException {
        WxIClient client = WxHiClientManager.getClient(clientName);
        if (null == client) {
            throw new HiITFException(HiMsgCdConstants.ITF_CLIENT_NOT_EXIST, clientName);
        }
        T responseDto = client.send(paramMap, endPointId, responseClazz, bodyTyp, methodTyp);
        return responseDto;
    }
}
