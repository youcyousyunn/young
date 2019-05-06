package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 添加权限请求DTO
 * @author youcyousyunn
 * @date 2018年3月9日
 */
public class AddPermissionRequestDto extends BaseRequestDto {

	/**
     * 名称
     */
    private String name;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 状态
     */
    private String status;

    /**
     * 类型
     */
    private String type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 地址
     */
    private String url;

    /**
     * 描述
     */
    private String desc;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 权限编号
     */
    private String urlAuth;

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrlAuth() {
		return urlAuth;
	}

	public void setUrlAuth(String urlAuth) {
		this.urlAuth = urlAuth;
	}
    
    

}
