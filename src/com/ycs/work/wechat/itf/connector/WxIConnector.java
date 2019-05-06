package com.ycs.work.wechat.itf.connector;

import java.util.Map;

import com.ycs.base.log4j.logger.HiITFCLogger;
import com.ycs.work.wechat.itf.ep.IEndPoint;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 连接基类
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface WxIConnector {
    /**
     * 建立连接
     * @param endPoint
     * @param methodTyp
     * @throws HiITFException
     */
    public void connect(IEndPoint endPoint, String methodTyp) throws HiITFException;

    /**
     * 发送请求
     * @param param
     * @param methodTyp
     * @param bodyTyp
     * @return byte[]
     * @throws HiITFException
     */
    public byte[] send(Map<?,?> param, String methodTyp, String bodyTyp) throws HiITFException;

    /**
     * 关闭连接
     * @param methodTyp
     * @throws HiITFException
     */
    public void close(String methodTyp) throws HiITFException;

    /**
     * 设置日志
     * @param logger
     */
    public void setLogger(HiITFCLogger logger);
    
}
