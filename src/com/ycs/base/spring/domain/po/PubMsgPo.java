package com.ycs.base.spring.domain.po;

import java.sql.Timestamp;

import com.ycs.base.domain.po.BasePo;

public class PubMsgPo extends BasePo {
	private String appId;
	private String modId;
	private String msgCd;
	private String msgType;
	private String bmsgInf;
	private String msgInf;
	private Timestamp tmSmp;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getMsgCd() {
		return msgCd;
	}
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getBmsgInf() {
		return bmsgInf;
	}
	public void setBmsgInf(String bmsgInf) {
		this.bmsgInf = bmsgInf;
	}
	public String getMsgInf() {
		return msgInf;
	}
	public void setMsgInf(String msgInf) {
		this.msgInf = msgInf;
	}
	public Timestamp getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(Timestamp tmSmp) {
		this.tmSmp = tmSmp;
	}
	
}
