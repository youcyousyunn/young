package com.ycs.work.wechat.itf.convertor.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycs.work.wechat.itf.convertor.WxJsConvertor;
import com.ycs.work.wechat.itf.dto.BaseITFRequestDto;
import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFConvertException;

public class HiWxObjectConvertor extends WxJsConvertor{
    
    private ObjectMapper objectMapper = new ObjectMapper();
    private String jsonPrefix;
    private JsonEncoding jacksonEncoding;

    public void setEncoding(String encoding) {
        super.setEncoding(encoding);
        jacksonEncoding = getJsonEncoding(encoding);
    }

    private JsonEncoding getJsonEncoding(String encoding) {
        for (JsonEncoding jacksonEncoding : JsonEncoding.values()) {
            if (encoding.equals(jacksonEncoding.getJavaName())) {
                return jacksonEncoding;
            }
        }
        return JsonEncoding.UTF8;
    }

    public String getJsonPrefix() {
        return jsonPrefix;
    }

    public void setJsonPrefix(String jsonPrefix) {
        this.jsonPrefix = jsonPrefix;
    }

    public HiWxObjectConvertor() {
        this.objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    /* （non Javadoc）
     * Title: writeInternal<br/>
     * <p>Description: <br/>
     * @param request
     * @return
     * @throws HiITFConvertException
     */
    @Override
    protected <T extends BaseITFRequestDto> T writeInternal(T request) throws HiITFConvertException {
        if (null == request) {
            return request;
        }
        return request;
    }

    /* （non Javadoc）
     * Title: readInternal<br/>
     * <p>Description: <br/>
     * @param response
     * @param dtoClazz
     * @return
     * @throws HiITFConvertException
     */
    @Override
    protected <T extends BaseITFResponseDto> T readInternal(byte[] response, Class<T> dtoClazz)
            throws HiITFConvertException {
        if (null == response || response.length == 0) {
            return null;
        }

        try {
            T responseDto = this.objectMapper.readValue(new String(response,
                    encoding), dtoClazz);
            return responseDto;
        } catch (IOException e) {
            throw new HiITFConvertException(e);
        }
    }

}
