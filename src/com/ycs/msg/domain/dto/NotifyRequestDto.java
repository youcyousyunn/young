package com.ycs.msg.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 发布公告DTO
 * @author youcyousyunn
 * @date 2019年3月20日
 */
public class NotifyRequestDto extends BaseRequestDto implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -7947704936414065603L;

	/**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;
	
    /**
     * 构造函数
     */
	public NotifyRequestDto() {
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 接口报文请求检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == title || StringUtils.isBlank(title)) {
            return false;
        }
        if (null == content || StringUtils.isBlank(content)) {
            return false;
        }
        return true;
    }

}
