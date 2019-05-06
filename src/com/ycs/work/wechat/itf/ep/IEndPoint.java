package com.ycs.work.wechat.itf.ep;

/**
 * @description EndPoint
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface IEndPoint {

    /**
     * 获取连接端对象信息
     * @return Object
     */
    public Object getEndPoint();

    /**
     * 端信息
     * @return String
     */
    public String toString();
}
