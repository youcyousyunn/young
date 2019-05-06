package com.ycs.msg.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 工作台消息请求DTO
 * @author youcyousyunn
 * @date 2018年6月25日
 */
public class QryNotifyRequestDto extends BaseRequestDto {
    //消息类型
    private String msgTyp;
    //标题
    private String title;
    //当前页
    private Integer currentPage;
    //页大小
    private Integer pageSize;
    
	public String getMsgTyp() {
		return msgTyp;
	}
	public void setMsgTyp(String msgTyp) {
		this.msgTyp = msgTyp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
	 /**
     * 接口报文检查
     * @return boolean
     */
    public boolean checkRequestDto() {
        if (null == currentPage) {
            return false;
        }
        if (null == pageSize) {
            return false;
        }
        return true;
    }
    
}
