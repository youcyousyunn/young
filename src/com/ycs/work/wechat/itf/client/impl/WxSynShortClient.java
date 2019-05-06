package com.ycs.work.wechat.itf.client.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.ycs.base.log4j.logger.HiITFCLogger;
import com.ycs.work.wechat.itf.client.WxIClient;
import com.ycs.work.wechat.itf.connector.WxIConnector;
import com.ycs.work.wechat.itf.connector.WxIConnectorFactory;
import com.ycs.work.wechat.itf.convertor.WxIConvertor;
import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.ep.IEndPoint;
import com.ycs.work.wechat.itf.exception.HiITFException;


public class WxSynShortClient implements WxIClient {
    protected HiITFCLogger logger;
    protected String clientName;
    /** convertor,转换器 */
    protected WxIConvertor convertor;
    /** connectorClazz,连接器 */
    protected WxIConnectorFactory connectorFactory;
    /** endPoint,远程端 */
    protected IEndPoint endPoint;
    protected Map<String, IEndPoint> endPoints;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        logger = HiITFCLogger.getCLogger(this.clientName);
    }

    public WxIConvertor getConvertor() {
        return convertor;
    }

    public void setConvertor(WxIConvertor convertor) {
        this.convertor = convertor;
    }

    public WxIConnectorFactory getConnectorFactory() {
        return connectorFactory;
    }

    public void setConnectorFactory(WxIConnectorFactory connectorFactory) {
        Assert.notNull(this.clientName, "'clientName' must not be null");
        this.connectorFactory = connectorFactory;
        this.connectorFactory.setLogger(logger);
    }

    public IEndPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(IEndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public Map<String, IEndPoint> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(Map<String, IEndPoint> endPoints) {
        this.endPoints = endPoints;
    }

    @Override
    public <T extends BaseITFResponseDto> T send(Map<?, ?> pramMap, Class<T> responseClazz, String bodyTyp, String methodTyp) throws HiITFException {
        return send(pramMap, this.endPoint, responseClazz, bodyTyp, methodTyp);
    }

    @Override
    public <T extends BaseITFResponseDto> T send(Map<?, ?> paramMap, String epId, Class<T> responseClazz, String bodyTyp, String methodTyp)
            throws HiITFException {
        // 指定远程端
        return send(paramMap, endPoints.get(epId), responseClazz, bodyTyp, methodTyp);
    }

    public <T extends BaseITFResponseDto> T send(Map<?, ?> paramMap, IEndPoint endPoint, Class<T> responseClazz,
            String bodyTyp, String methodTyp) throws HiITFException {

        // 转换发送数据
        // ArrayList<byte[]> request = convertor.write(requestDto);
        // BaseITFRequestDto request = convertor.write(pramMap);

        byte[] response = null;
        WxIConnector connector = null;
        try {
            //创建连接
            connector = this.connectorFactory.build();
            // 建立连接
            connector.connect(endPoint, methodTyp);
            
            // 发送数据
            response = connector.send(paramMap, methodTyp, bodyTyp);
            
        } catch (HiITFException e) {
            throw e;
        } catch (Throwable e) {
            throw new HiITFException(e);
        } finally {

            // 关闭连接
            try {
                if (null != connector) {
                    connector.close("POST");
                }
            } catch (HiITFException e) {
                this.logger.warn("client connector[" + this.clientName + "] close failed", e);
            }
        }

        // 转换返回数据
        T responseDto = null;
        if ("xml".equals(bodyTyp)) {
            Method[] methods = responseClazz.getMethods();
            String retValue = StringUtils.trimToEmpty(new String(response));
            try {
                T tclass = responseClazz.newInstance();
                for (Method method : methods) {
                    if ("setXmlBody".equals(method.getName())) {
                        method.invoke(tclass, retValue);
                        break;
                    }
                }
                responseDto = tclass;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | InstantiationException e) {
                return null;
            }
        } else {
            responseDto = convertor.read(response, responseClazz);
        }
        return responseDto;
    }
}
