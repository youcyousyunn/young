package com.ycs.work.wechat.itf.connector.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.log4j.logger.HiITFCLogger;
import com.ycs.work.wechat.itf.connector.WxIConnector;
import com.ycs.work.wechat.itf.ep.IEndPoint;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 微信https请求
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class WxHttpsConnector implements WxIConnector {

    private HiITFCLogger logger;
    private CloseableHttpClient client;
    private HttpGet getMethod;
    private HttpPost postMethod;
    private EntityBuilder entityBuilder;

    public HiITFCLogger getLogger() {
        return logger;
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public EntityBuilder getEntityBuilder() {
        return entityBuilder;
    }

    public void setClient(CloseableHttpClient client) {
        this.client = client;
    }

    public void setEntityBuilder(EntityBuilder entityBuilder) {
        this.entityBuilder = entityBuilder;
    }

    public HttpGet getGetMethod() {
        return getMethod;
    }

    public void setGetMethod(HttpGet getMethod) {
        this.getMethod = getMethod;
    }

    public HttpPost getPostMethod() {
        return postMethod;
    }

    public void setPostMethod(HttpPost postMethod) {
        this.postMethod = postMethod;
    }

    @Override
    public void connect(IEndPoint endPoint, String methodTyp) throws HiITFException {
        try {
            if ("POST".equals(methodTyp)) {
                postMethod.setURI(new URIBuilder().setPath((String) endPoint.getEndPoint()).build());
            } else {
                getMethod.setURI(new URIBuilder().setPath((String) endPoint.getEndPoint()).build());
            }

        } catch (URISyntaxException e) {
            throw new HiITFException(e);
        }
    }

    @Override
    public byte[] send(Map<?, ?> pram, String methodTyp, String bodyTyp) throws HiITFException {
        try {
            
            String caFilePath = (String) pram.get("cafilepath");
            String caFilePwd = (String) pram.get("cafilepwd");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(caFilePath));
            try {
                keyStore.load(instream, caFilePwd.toCharArray());
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, caFilePwd.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                    null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            this.client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            
        } catch (Exception e) {
            throw new HiITFException(HiMsgCdConstants.ITF_CONNECT_FAIL, "Https SSLConnection Error[" + e.getMessage() + "]");
        }
        
        byte[] resData = null;
        CloseableHttpResponse response = null;
        try {

            long l1 = System.currentTimeMillis();
            if ("POST".equals(methodTyp)) {

                // 键值对方式的报文
                if ("keyValue".equals(bodyTyp)) {
                    NameValuePair[] pair = new BasicNameValuePair[pram.size()];
                    int i = 0;
                    for (Entry<?, ?> entry : pram.entrySet()) {
                        pair[i] = new BasicNameValuePair(entry.getKey().toString(), (String) entry.getValue());
                        i += 1;
                    }
                    postMethod.setEntity(entityBuilder.setParameters(pair).build());
                }

                // json格式的报文
                if ("json".equals(bodyTyp)) {
                    StringEntity entity = new StringEntity((String) pram.get("json"), "UTF-8");
                    entity.setContentEncoding(entityBuilder.getContentEncoding());
                    entity.setContentType("application/json;charset=UTF-8");
                    if (null != entityBuilder.getContentType()) {
                        entity.setContentType(entityBuilder.getContentType().toString());
                    }
                    postMethod.setEntity(entity);
                }

                // xml格式的报文
                if ("xml".equals(bodyTyp)) {
                    StringEntity entity = new StringEntity((String) pram.get("xml"), "UTF-8");
                    entity.setContentEncoding(entityBuilder.getContentEncoding());
                    entity.setContentType("application/xml;charset=UTF-8");
                    if (null != entityBuilder.getContentType()) {
                        entity.setContentType(entityBuilder.getContentType().toString());
                    }
                    postMethod.setEntity(entity);
                }
                
                // keyValueAndJson格式的报文
                if ("keyValueAndJson".equals(bodyTyp)) {
                    //key=parm1|pram2|
                    //value=value1|value2|
                    
                    String [] keys = String.valueOf(pram.get("key")).split("\\|");
                    String [] valus = String.valueOf(pram.get("value")).split("\\|");
                    
                    StringBuffer urlBuf = new StringBuffer();
                    urlBuf.append(postMethod.getURI().toString() + "?");
                    for (int i = 0; i < keys.length; i++) {
                        urlBuf.append(keys[i]+"="+valus[i]);
                        if(i!=(keys.length-1)){
                            urlBuf.append("&");
                        }
                    }
                    URI uri = new URI(urlBuf.toString());
                    postMethod.setURI(uri);
                    
                    StringEntity entity = new StringEntity((String) pram.get("json"), "UTF-8");
                    entity.setContentEncoding(entityBuilder.getContentEncoding());
                    entity.setContentType("application/json;charset=UTF-8");
                    if (null != entityBuilder.getContentType()) {
                        entity.setContentType(entityBuilder.getContentType().toString());
                    }
                    postMethod.setEntity(entity);
                }
                response = client.execute(postMethod);
            } else {
                StringBuffer urlBuf = new StringBuffer();
                urlBuf.append(getMethod.getURI().toString() + "?");

                int i = 0;
                for (Entry<?, ?> entry : pram.entrySet()) {
                    if (i == pram.size() - 1) {
                        urlBuf.append(entry.getKey() + "=" + entry.getValue());
                    } else {
                        urlBuf.append(entry.getKey() + "=" + entry.getValue() + "&");
                    }
                    i += 1;
                }
                URI uri = new URI(urlBuf.toString());
                getMethod.setURI(uri);
                response = client.execute(getMethod);
            }

            long totms = System.currentTimeMillis() - l1;
            int status = response.getStatusLine().getStatusCode();
            HiBizLogger.info("和外围通信返回的结果:" + status);
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_MOVED_PERMANENTLY
                    || (status == HttpStatus.SC_MOVED_TEMPORARILY) || (status == HttpStatus.SC_SEE_OTHER)
                    || (status == HttpStatus.SC_TEMPORARY_REDIRECT)) {

                resData = EntityUtils.toByteArray(response.getEntity());
                if ("POST".equals(methodTyp)) {
                    this.logger.recvInfo(postMethod.getURI().toString(), totms, status, resData);
                } else {
                    this.logger.recvInfo(getMethod.getURI().toString(), totms, status, resData);
                }
            } else {

                // 记录请求失败日志
                if ("POST".equals(methodTyp)) {
                    this.logger.recvError(postMethod.getURI().toString(), totms, status, resData);
                } else {
                    this.logger.recvError(getMethod.getURI().toString(), totms, status, resData);
                }
                throw new HiITFException(HiMsgCdConstants.ITF_SEND_FAIL, "Https Response code[" + status + "]");
            }

        } catch (IOException e) {
            throw new HiITFException(e);
        } catch (URISyntaxException e) {
            throw new HiITFException(e);
        } finally {

            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    if ("POST".equals(methodTyp)) {
                        this.logger.warn("[" + postMethod.getURI().toString() + "]' response close failed", e);
                    } else {
                        this.logger.warn("[" + getMethod.getURI().toString() + "]' response close failed", e);
                    }
                }
            }
        }

        return resData;
    }

    @Override
    public void close(String methodTyp) throws HiITFException {
        if ("POST".equals(methodTyp)) {
            this.postMethod.releaseConnection();
        } else {
            this.getMethod.releaseConnection();
        }
        try {
            this.client.close();
        } catch (IOException e) {
            throw new HiITFException(e);
        }
    }

    @Override
    public void setLogger(HiITFCLogger logger) {
        this.logger = logger;
    }
}
