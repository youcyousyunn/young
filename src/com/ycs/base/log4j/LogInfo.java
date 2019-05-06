package com.ycs.base.log4j;

public class LogInfo {
	private StringBuilder buf;
	private IFileName name;

	LogInfo(IFileName name, StringBuilder buf) {
		this.buf = buf;
		this.name = name;
	}

	public StringBuilder getBuf() {
		return buf;
	}
	public void setBuf(StringBuilder buf) {
		this.buf = buf;
	}
	public IFileName getName() {
		return name;
	}
	public void setName(IFileName name) {
		this.name = name;
	}

}
