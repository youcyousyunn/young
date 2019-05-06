package com.ycs.base.domain.dto;

public class BaseRequestDto extends BaseDto {
	protected String appVersion;
	protected String osVersion;
	protected String termTyp;
	protected String termId;
	protected String requestTm;
	protected String deviceId;
	protected String tokenId;
	protected String channelId;
	protected String channelTyp;
	protected String usrNo;
	
	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getTermTyp() {
		return this.termTyp;
	}

	public void setTermTyp(String termTyp) {
		this.termTyp = termTyp;
	}

	public String getTermId() {
		return this.termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getRequestTm() {
		return this.requestTm;
	}

	public void setRequestTm(String requestTm) {
		this.requestTm = requestTm;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelTyp() {
		return this.channelTyp;
	}

	public void setChannelTyp(String channelTyp) {
		this.channelTyp = channelTyp;
	}

	public String getUsrNo() {
		return this.usrNo;
	}

	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	
	

}
