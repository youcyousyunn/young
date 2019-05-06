package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 修改权限请求DTO
 * @author youcyousyunn
 * @date 2018年3月9日
 */
public class UpdatePermissionRequestDto extends BaseRequestDto {

	/**
     * 权限编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

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
     * 构造函数
     */
	public UpdatePermissionRequestDto() {
	}
	
	/**
     * 构造函数
     * @param name 名称
     * @param status 状态
     * @param type 类型
     * @param icon 图标
     * @param url 地址
     * @param desc 描述
     */
    public UpdatePermissionRequestDto(String name, String status, String type, String icon, String url, String desc) {
        this.name = name;
        this.status = status;
        this.type = type;
        this.icon = icon;
        this.url = url;
        this.desc = desc;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
    
    

}
