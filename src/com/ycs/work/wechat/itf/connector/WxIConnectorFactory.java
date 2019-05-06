package com.ycs.work.wechat.itf.connector;

import com.ycs.base.log4j.logger.HiITFCLogger;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 微信连接工厂
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface WxIConnectorFactory {

    /**
     * 构建连接器
     * @return
     * @throws HiITFException
     */
    public WxIConnector build() throws HiITFException;

    public void setLogger(HiITFCLogger logger);
    
}
