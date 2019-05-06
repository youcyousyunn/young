package com.ycs.base.spring.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ycs.base.log4j.logger.HiITFLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.converter.interceptor.IRequestConvInterceptor;
import com.ycs.base.spring.converter.interceptor.IResponseConvInterceptor;

public class HiMappingJacksonHttpMessageConverter extends AbstractHttpMessageConverter<Object>
		implements GenericHttpMessageConverter<Object> {
	
	private List<IRequestConvInterceptor> requestConvInterceptors = Collections.emptyList();
	private List<IResponseConvInterceptor> responseConvInterceptors = Collections.emptyList();
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private static final boolean jackson23Available = ClassUtils.hasMethod(ObjectMapper.class, "canDeserialize",
			new Class[]{JavaType.class, AtomicReference.class});
	private ObjectMapper objectMapper = new ObjectMapper();
	private String jsonPrefix;
	private Boolean prettyPrint;

	public List<IRequestConvInterceptor> getRequestConvInterceptors() {
		return Collections.unmodifiableList(this.requestConvInterceptors);
	}

	public void setRequestConvInterceptors(List<IRequestConvInterceptor> requestConvInterceptors) {
		Assert.notEmpty(requestConvInterceptors, "\'requestConvInterceptors\' must not be empty");
		this.requestConvInterceptors = new ArrayList(requestConvInterceptors);
	}

	public List<IResponseConvInterceptor> getResponseConvInterceptors() {
		return Collections.unmodifiableList(this.responseConvInterceptors);
	}

	public void setResponseConvInterceptors(List<IResponseConvInterceptor> responseConvInterceptors) {
		Assert.notEmpty(responseConvInterceptors, "\'responseConvInterceptors\' must not be empty");
		this.responseConvInterceptors = new ArrayList(responseConvInterceptors);
	}

	public HiMappingJacksonHttpMessageConverter() {
		super(new MediaType[]{new MediaType("application", "json", DEFAULT_CHARSET),
				new MediaType("application", "*+json", DEFAULT_CHARSET)});
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "ObjectMapper must not be null");
		this.objectMapper = objectMapper;
		this.configurePrettyPrint();
	}

	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	public void setJsonPrefix(String jsonPrefix) {
		this.jsonPrefix = jsonPrefix;
	}

	public void setPrefixJson(boolean prefixJson) {
		this.jsonPrefix = prefixJson ? "{} && " : null;
	}

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = Boolean.valueOf(prettyPrint);
		this.configurePrettyPrint();
	}

	private void configurePrettyPrint() {
		if (this.prettyPrint != null) {
			this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint.booleanValue());
		}
	}

	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return this.canRead(clazz, (Class) null, mediaType);
	}

	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		JavaType javaType = this.getJavaType(type, contextClass);
		return jackson23Available && this.logger.isWarnEnabled()
				? false
				: this.objectMapper.canDeserialize(javaType) && this.canRead(mediaType);
	}

	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return jackson23Available && this.logger.isWarnEnabled()
				? false
				: this.objectMapper.canSerialize(clazz) && this.canWrite(mediaType);
	}

	protected boolean supports(Class<?> clazz) {
		throw new UnsupportedOperationException();
	}

	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		JavaType javaType = this.getJavaType(clazz, (Class) null);
		return this.readJavaType(javaType, inputMessage);
	}

	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		JavaType javaType = this.getJavaType(type, contextClass);
		byte[] message = this.readMessageAndClose(inputMessage.getBody());
		byte[] in = this.preHandle(message);
		Object o = this.readJavaType(javaType, in);
		o = this.afterHandle(o);
		return o;
	}

	private byte[] preHandle(byte[] message) throws IOException {
		IRequestConvInterceptor requestConvInterceptor;
		for (Iterator arg1 = this.requestConvInterceptors.iterator(); arg1
				.hasNext(); message = requestConvInterceptor.preHandler(message)) {
			requestConvInterceptor = (IRequestConvInterceptor) arg1.next();
		}

		return message;
	}

	private Object afterHandle(Object object) throws IOException {
		IRequestConvInterceptor requestConvInterceptor = null;
		for (int i = this.requestConvInterceptors.size() - 1; i >= 0; --i) {
			requestConvInterceptor = (IRequestConvInterceptor) this.requestConvInterceptors.get(i);
			object = requestConvInterceptor.afterHandler(object);
		}
		return object;
	}

	private byte[] readMessageAndClose(InputStream in) throws IOException {
		byte[] buf = new byte[1024];
		int onceread = 0;
		int offset = 0;
		byte[] tmpBuf;
		Object tmpBuf1;
		try {
			for (; onceread >= 0; onceread = in.read(buf, offset, buf.length - offset)) {
				offset += onceread;
				if (offset >= buf.length) {
					tmpBuf = new byte[buf.length + 1024];
					System.arraycopy(buf, 0, tmpBuf, 0, buf.length);
					buf = tmpBuf;
					tmpBuf1 = null;
				}
			}
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException arg11) {
					;
				}
				in = null;
			}
			tmpBuf = new byte[offset];
			System.arraycopy(buf, 0, tmpBuf, 0, offset);
			buf = tmpBuf;
			tmpBuf1 = null;
		}
		return buf;
	}

	private Object readJavaType(JavaType javaType, byte[] in) {
		try {
			Object e = this.objectMapper.readValue(new String(in, SystemPropertyConfigure.getProperty("sys.encoding")),
					javaType);
			return e;
		} catch (IOException arg3) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + arg3.getMessage(), arg3);
		}
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
		try {
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		} catch (IOException arg3) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + arg3.getMessage(), arg3);
		}
	}

	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		this.beforeCast(object);
		JsonEncoding encoding = this.getJsonEncoding(outputMessage.getHeaders().getContentType());
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			JsonGenerator jsonGenerator = this.objectMapper.getFactory().createJsonGenerator(out, encoding);
			if (this.objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
				jsonGenerator.useDefaultPrettyPrinter();
			}
			try {
				if (this.jsonPrefix != null) {
					jsonGenerator.writeRaw(this.jsonPrefix);
				}
				this.objectMapper.writeValue(jsonGenerator, object);
			} catch (JsonProcessingException arg9) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + arg9.getMessage(), arg9);
			}
			byte[] data = this.afterCast(out.toByteArray());
			HiITFLogger.info("send data[" + new String(data) + "]");
			outputMessage.getBody().write(data);
			outputMessage.getBody().flush();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	private void beforeCast(Object object) {
		Iterator arg1 = this.responseConvInterceptors.iterator();
		while (arg1.hasNext()) {
			IResponseConvInterceptor responseConvInterceptor = (IResponseConvInterceptor) arg1.next();
			responseConvInterceptor.castPreHandle(object);
		}
	}

	private byte[] afterCast(byte[] jsonValue) throws IOException {
		IResponseConvInterceptor responseConvInterceptor = null;
		for (int i = this.responseConvInterceptors.size() - 1; i >= 0; --i) {
			responseConvInterceptor = (IResponseConvInterceptor) this.responseConvInterceptors.get(i);
			jsonValue = responseConvInterceptor.castAfterHandle(jsonValue);
		}
		return jsonValue;
	}

	protected JavaType getJavaType(Type type, Class<?> contextClass) {
		return this.objectMapper.getTypeFactory().constructType(type, contextClass);
	}

	protected JsonEncoding getJsonEncoding(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			JsonEncoding[] arg2 = JsonEncoding.values();
			int arg3 = arg2.length;

			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				JsonEncoding encoding = arg2[arg4];
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

}
