package com.ycs.base.spring.converter.interceptor;

public interface IResponseConvInterceptor {
	void castPreHandle(Object arg0);
	byte[] castAfterHandle(byte[] arg0);
	
}
