package com.ycs.base.domain.pojo;

import java.util.Map;
import com.ycs.base.context.HiContext;

public class JmsPojo {
	private String msgId;
	private String serviceName;
	private String methodName;
	private boolean isRedelivered;
	private Map transData;
	private JmsPojo instance;
	private Class clazz;

	public JmsPojo() {
		try {
			HiContext e = HiContext.getCurrentContext();
			if (null != e) {
				this.msgId = e.getRequestId();
			}
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

	}

	public String getService() {
		return this.serviceName;
	}

	public void setService(String service) {
		this.serviceName = service;
	}

	public String getMethod() {
		return this.methodName;
	}

	public void setMethod(String method) {
		this.methodName = method;
	}

	public boolean isRedelivered() {
		return this.isRedelivered;
	}

	public void setRedelivered(boolean isRedelivered) {
		this.isRedelivered = isRedelivered;
	}

	public Map getTransData() {
		return this.transData;
	}

	public void setTransData(Map transData) {
		this.transData = transData;
		this.clazz = transData.getClass();
	}

	public Class getClazz() {
		return this.clazz;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public String toString() {
		return "JmsPojo [msgId=" + this.msgId + ", serviceName=" + this.serviceName + ", methodName=" + this.methodName
				+ ", isRedelivered=" + this.isRedelivered + ", transData=" + this.transData + ", instance="
				+ this.instance + ", clazz=" + this.clazz + "]";
	}
}