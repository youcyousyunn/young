package com.ycs.work.wechat.itf.convertor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

import com.ycs.work.wechat.itf.dto.BaseITFRequestDto;
import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFConvertException;
import com.ycs.work.wechat.itf.interceptor.IRequestConvInterceptor;
import com.ycs.work.wechat.itf.interceptor.IResponseConvInterceptor;

/**
 * @description 微信JS转换器
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public abstract class WxJsConvertor implements WxIConvertor {

    protected String encoding;

    /** requestConvInterceptors,请求拦截器 */
    protected List<IRequestConvInterceptor> requestConvInterceptors = Collections.emptyList();
    /** responseConvInterceptors,返回拦截器 */
    protected List<IResponseConvInterceptor> responseConvInterceptors = Collections.emptyList();

    public List<IRequestConvInterceptor> getRequestConvInterceptors() {
        return Collections.unmodifiableList(this.requestConvInterceptors);
    }

    public void setRequestConvInterceptors(List<IRequestConvInterceptor> requestConvInterceptors) {
        Assert.notEmpty(requestConvInterceptors, "'requestConvInterceptors' must not be empty");
        this.requestConvInterceptors = new ArrayList<IRequestConvInterceptor>(requestConvInterceptors);
    }

    public List<IResponseConvInterceptor> getResponseConvInterceptors() {
        return Collections.unmodifiableList(this.responseConvInterceptors);
    }

    public void setResponseConvInterceptors(List<IResponseConvInterceptor> responseConvInterceptors) {
        Assert.notEmpty(responseConvInterceptors, "'responseConvInterceptors' must not be empty");
        this.responseConvInterceptors = new ArrayList<IResponseConvInterceptor>(responseConvInterceptors);
    }

    private <T extends BaseITFRequestDto> T reqPreHandle(T requestDto) throws HiITFConvertException {
        IRequestConvInterceptor requestConvInterceptor = null;
        for (int i = requestConvInterceptors.size() - 1; i >= 0; i--) {
            requestConvInterceptor = requestConvInterceptors.get(i);
            requestDto = requestConvInterceptor.preHandle(requestDto);
        }
        return requestDto;
    }

    private byte[] reqAfterHandle(byte[] message) throws HiITFConvertException {

        for (IRequestConvInterceptor requestConvInterceptor : requestConvInterceptors) {
            message = requestConvInterceptor.afterHandle(message);
        }
        return message;
    }

    @Override
    public <T extends BaseITFRequestDto> T write(T request)
            throws HiITFConvertException {
        // 数据转换,获取CODE
        request = writeInternal(request);
        return request;
    }
    
    @Override
    public <T extends BaseITFResponseDto> T read(byte[] response,
            Class<T> dtoClazz) throws HiITFConvertException {

        // 返回数据转换前拦截
        response = rspPreHandle(response);

        // 返回数据转换
        T responseDto = readInternal(response, dtoClazz);
        // 返回数据转换后拦截
        responseDto = rspAfterHandle(responseDto);
        return responseDto;
    }
    /**
     * Title: writeInternal<br/>
     * Description: 请求数据转换<br/>
     * @param request
     * @return
     */
    protected abstract <T extends BaseITFRequestDto> T writeInternal(T request)
            throws HiITFConvertException;

    private byte[] rspPreHandle(byte[] response) throws HiITFConvertException {

        for (IResponseConvInterceptor responseConvInterceptor : responseConvInterceptors) {
            response = responseConvInterceptor.preHandle(response);
        }
        return response;
    }

    private <T extends BaseITFResponseDto> T rspAfterHandle(T responseDto) throws HiITFConvertException {

        IResponseConvInterceptor responseConvInterceptor = null;
        for (int i = responseConvInterceptors.size() - 1; i >= 0; i--) {
            responseConvInterceptor = responseConvInterceptors.get(i);
            responseDto = responseConvInterceptor.afterHandle(responseDto);
        }
        return responseDto;
    }

    /**
     * Title: readInternal<br/>
     * Description: 返回数据转换<br/>
     * @param response
     * @return
     */
    protected abstract <T extends BaseITFResponseDto> T readInternal(byte[] response, Class<T> dtoClazz)
            throws HiITFConvertException;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
