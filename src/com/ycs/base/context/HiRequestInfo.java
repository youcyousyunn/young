package com.ycs.base.context;

public class HiRequestInfo {
	private String nodId;
	private String insId;
	private String modId;
	private String sysCnl;
	private String msgId;
	private long requestTime;
	private long responseTime;
	private String remoteIp;
	private String code;
	private String desc;
	private String urlWithOutContext;
	private String userAgent;
	private byte[] requestData;
	private byte[] responseData;
	private String msgCd;
	private String msgInf;
	private String usrNo;
	private String usrToken;
	private String termType;
	private String termVersion;
	private String termId;
	private String appVersion;
	private String appCnl;
	
	public String getNodId() {
		return nodId;
	}
	public void setNodId(String nodId) {
		this.nodId = nodId;
	}
	public String getInsId() {
		return insId;
	}
	public void setInsId(String insId) {
		this.insId = insId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getSysCnl() {
		return sysCnl;
	}
	public void setSysCnl(String sysCnl) {
		this.sysCnl = sysCnl;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrlWithOutContext() {
		return urlWithOutContext;
	}
	public void setUrlWithOutContext(String urlWithOutContext) {
		this.urlWithOutContext = urlWithOutContext;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public byte[] getRequestData() {
		return requestData;
	}
	public void setRequestData(byte[] requestData) {
		this.requestData = requestData;
	}
	public byte[] getResponseData() {
		return responseData;
	}
	public void setResponseData(byte[] responseData) {
		this.responseData = responseData;
	}
	public String getMsgCd() {
		return msgCd;
	}
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}
	public String getMsgInf() {
		return msgInf;
	}
	public void setMsgInf(String msgInf) {
		this.msgInf = msgInf;
	}
	public String getUsrNo() {
		return usrNo;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	public String getUsrToken() {
		return usrToken;
	}
	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getTermVersion() {
		return termVersion;
	}
	public void setTermVersion(String termVersion) {
		this.termVersion = termVersion;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getAppCnl() {
		return appCnl;
	}
	public void setAppCnl(String appCnl) {
		this.appCnl = appCnl;
	}
	
	public static HiRequestInfo getCurInstance() {
		return (HiRequestInfo) HiContext.getCurrentContext().getProperty("@APP", "REQ_INFO");
	}

	public static void setCurInstance(HiRequestInfo reqInf) {
		HiContext.getCurrentContext().setProperty("@APP", "REQ_INFO", reqInf);
	}
	
}
