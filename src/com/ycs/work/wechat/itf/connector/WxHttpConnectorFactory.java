package com.ycs.work.wechat.itf.connector;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import com.ycs.base.log4j.logger.HiITFCLogger;
import com.ycs.work.wechat.itf.connector.impl.WxHttpConnector;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 微信http连接
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class WxHttpConnectorFactory implements WxIConnectorFactory {

    private HiITFCLogger logger;
    private HttpClientBuilder builder = HttpClients.custom().disableAutomaticRetries()
            .setRedirectStrategy(new LaxRedirectStrategy());
    private RequestConfig.Builder configBuilder = RequestConfig.custom();
    private String encoding;
    private String contentType;

    public HttpClientBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(HttpClientBuilder builder) {
        this.builder = builder;
    }

    public RequestConfig.Builder getConfigBuilder() {
        return configBuilder;
    }

    public void setConfigBuilder(RequestConfig.Builder configBuilder) {
        this.configBuilder = configBuilder;
    }

    public HiITFCLogger getLogger() {
        return logger;
    }

    @Override
    public WxIConnector build() throws HiITFException {
        CloseableHttpClient client = builder.build();
        WxHttpConnector connector = new WxHttpConnector();
        connector.setClient(client);

        RequestConfig config = configBuilder.build();
        HttpPost postMethod = new HttpPost();
        HttpGet getMethod = new HttpGet();
        postMethod.setConfig(config);
        getMethod.setConfig(config);
        connector.setPostMethod(postMethod);
        connector.setGetMethod(getMethod);

        EntityBuilder builder = EntityBuilder.create();
        if (null != contentType) {
            builder.setContentType(ContentType.create(contentType));
        }
        if (null != encoding) {
            builder.setContentEncoding(encoding);
        }
        connector.setEntityBuilder(builder);
        connector.setLogger(logger);
        return connector;
    }

    public void setConnTimeout(int connTimeout) {
        configBuilder.setConnectionRequestTimeout(connTimeout);
        configBuilder.setConnectTimeout(connTimeout);
    }

    public void setTimeout(int timeout) {
        configBuilder.setSocketTimeout(timeout);
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setLogger(HiITFCLogger logger) {
        this.logger = logger;
    }
}
