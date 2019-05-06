package com.ycs.work.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 工作台消息请求DTO
 * @author youcyousyunn
 * @date 2018年6月25日
 */
public class QryMsgNotifyRequestDto extends BaseRequestDto {
    //消息类型
    private String msgTyp;
    //动作类型
    private String actionTyp;
    //标题
    private String title;
    //标记
    private String isStar;
    //发起
    private String isMyLunch;
    //当前页
    private Integer currentPage;
    //页大小
    private Integer pageSize;
    
    /**
     * @return the msgTyp
     */
    public String getMsgTyp() {
        return msgTyp;
    }
    /**
     * @param msgTyp the msgTyp to set
     */
    public void setMsgTyp(String msgTyp) {
        this.msgTyp = msgTyp;
    }
    public String getActionTyp() {
		return actionTyp;
	}
	public void setActionTyp(String actionTyp) {
		this.actionTyp = actionTyp;
	}
	/**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the isStar
     */
    public String getIsStar() {
        return isStar;
    }
    /**
     * @param isStar the isStar to set
     */
    public void setIsStar(String isStar) {
        this.isStar = isStar;
    }
    /**
     * @return the isMyLunch
     */
    public String getIsMyLunch() {
        return isMyLunch;
    }
    /**
     * @param isMyLunch the isMyLunch to set
     */
    public void setIsMyLunch(String isMyLunch) {
        this.isMyLunch = isMyLunch;
    }
    /**
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }
    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }
    /**
     * @param pageSize the pageSize to set
     */
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
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QryMsgNotifyRequestDto [msgTyp=" + msgTyp + ", title=" + title + ", isStar=" + isStar + ", isMyLunch="
                + isMyLunch + ", currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
    }
}
