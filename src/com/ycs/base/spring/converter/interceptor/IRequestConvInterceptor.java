package com.ycs.base.spring.converter.interceptor;

import java.io.IOException;

public interface IRequestConvInterceptor {
	byte[] preHandler(byte[] arg0) throws IOException;
	Object afterHandler(Object arg0) throws IOException;
	
}
