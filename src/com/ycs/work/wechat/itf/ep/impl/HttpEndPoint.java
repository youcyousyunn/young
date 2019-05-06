package com.ycs.work.wechat.itf.ep.impl;

import com.ycs.work.wechat.itf.ep.IEndPoint;

public class HttpEndPoint implements IEndPoint {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Object getEndPoint() {
        return url;
    }

    public String toString() {
        return url;
    }
}
