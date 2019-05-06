package com.ycs.work.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 阅读消息请求DTO
 * @author youcyousyunn
 * @date 2018年6月26日
 */
public class IsReadMsgRequestDto extends BaseRequestDto {
	//消息ID
    private String msgId;
    //更改读取状态
    private String isRead;
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the isRead
	 */
	public String getIsRead() {
		return isRead;
	}
	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
    
	/**
	 * 接口报文检查
	 * @return boolean
	 */
    public boolean checkRequestDto() {
        if (null == msgId || StringUtils.isBlank(msgId)) {
            return false;
        }
        if (null == isRead || StringUtils.isBlank(isRead)) {
            return false;
        }
        return true;
    }
    

}
