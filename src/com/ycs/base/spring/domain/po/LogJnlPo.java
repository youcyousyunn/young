package com.ycs.base.spring.domain.po;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class LogJnlPo {
	private String nodId;
	private String regId;
	private String msgId;
	private String code;
	private String codeDesc;
	private Date reqDt;
	private Time reqTm;
	private Date rspDt;
	private Time rspTm;
	private int totTm;
	private String userAgent;
	private String sip;
	private String reqDat;
	private String usrToken;
	private String usrNo;
	private String rspDat;
	private String msgCd;
	private String msgInf;
	private String trmTyp;
	private String trmId;
	private String trmVer;
	private String appVer;
	private String appCnl;
	private Timestamp tmSmp;

	public String getNodId() {
		return this.nodId;
	}

	public void setNodId(String nodId) {
		this.nodId = nodId;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public Date getReqDt() {
		return this.reqDt;
	}

	public void setReqDt(Date reqDt) {
		this.reqDt = reqDt;
	}

	public Time getReqTm() {
		return this.reqTm;
	}

	public void setReqTm(Time reqTm) {
		this.reqTm = reqTm;
	}

	public Date getRspDt() {
		return this.rspDt;
	}

	public void setRspDt(Date rspDt) {
		this.rspDt = rspDt;
	}

	public Time getRspTm() {
		return this.rspTm;
	}

	public void setRspTm(Time rspTm) {
		this.rspTm = rspTm;
	}

	public int getTotTm() {
		return this.totTm;
	}

	public void setTotTm(int totTm) {
		this.totTm = totTm;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSip() {
		return this.sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getReqDat() {
		return this.reqDat;
	}

	public void setReqDat(String reqDat) {
		this.reqDat = reqDat;
	}

	public String getUsrToken() {
		return this.usrToken;
	}

	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}

	public String getUsrNo() {
		return this.usrNo;
	}

	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}

	public String getRspDat() {
		return this.rspDat;
	}

	public void setRspDat(String rspDat) {
		this.rspDat = rspDat;
	}

	public String getMsgCd() {
		return this.msgCd;
	}

	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}

	public String getMsgInf() {
		return this.msgInf;
	}

	public void setMsgInf(String msgInf) {
		this.msgInf = msgInf;
	}

	public String getTrmTyp() {
		return this.trmTyp;
	}

	public void setTrmTyp(String trmTyp) {
		this.trmTyp = trmTyp;
	}

	public String getTrmId() {
		return this.trmId;
	}

	public void setTrmId(String trmId) {
		this.trmId = trmId;
	}

	public String getAppVer() {
		return this.appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getAppCnl() {
		return this.appCnl;
	}

	public void setAppCnl(String appCnl) {
		this.appCnl = appCnl;
	}

	public String getTrmVer() {
		return this.trmVer;
	}

	public void setTrmVer(String trmVer) {
		this.trmVer = trmVer;
	}

	public Timestamp getTmSmp() {
		return this.tmSmp;
	}

	public void setTmSmp(Timestamp tmSmp) {
		this.tmSmp = tmSmp;
	}
}