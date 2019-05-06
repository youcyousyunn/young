package com.ycs.msg.utils;

public class MailInfo {
	private String subject; //主题
	private String text; //正文
	private String to; //接收者
	private String from; //发送者
	private String code; //验证码
	
	public MailInfo(){
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MailInfo [subject=" + subject + ", text=" + text + ", to=" + to + ", from=" + from + ", code=" + code
				+ "]";
	}
	
}
