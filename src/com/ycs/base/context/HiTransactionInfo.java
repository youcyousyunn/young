package com.ycs.base.context;

public class HiTransactionInfo {
	private String appId;
	private String modId;
	private String code;
	private String desc;
	private String txSwitch;
	private String logSwitch;
	private String pwdSwitch;
	private String urlAuth;
	
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
	public String getTxSwitch() {
		return txSwitch;
	}
	public void setTxSwitch(String txSwitch) {
		this.txSwitch = txSwitch;
	}
	public String getLogSwitch() {
		return logSwitch;
	}
	public void setLogSwitch(String logSwitch) {
		this.logSwitch = logSwitch;
	}
	public String getPwdSwitch() {
		return pwdSwitch;
	}
	public void setPwdSwitch(String pwdSwitch) {
		this.pwdSwitch = pwdSwitch;
	}
	public String getUrlAuth() {
		return urlAuth;
	}
	public void setUrlAuth(String urlAuth) {
		this.urlAuth = urlAuth;
	}

	public static HiTransactionInfo getCurInstance() {
		return (HiTransactionInfo) HiContext.getCurrentContext().getProperty("@APP", "TX_IF");
	}

	public static void setCurInstance(HiTransactionInfo transactionInfo) {
		HiContext.getCurrentContext().setProperty("@APP", "TX_IF", transactionInfo);
	}
	
	public boolean canLog() {
		return "Y".equalsIgnoreCase(this.logSwitch);
	}

	public boolean isAccept() {
		return "Y".equalsIgnoreCase(this.txSwitch);
	}
	
	public String toString() {
		return "HiTransactionInfo [appId=" + this.appId + ", modId=" + this.modId + ", code=" + this.code + ", desc="
				+ this.desc + ", txSwitch=" + this.txSwitch + ", logSwitch=" + this.logSwitch + ", pwdSwitch="
				+ this.pwdSwitch + ", urlAuth=" + this.urlAuth + "]";
	}
	
}
