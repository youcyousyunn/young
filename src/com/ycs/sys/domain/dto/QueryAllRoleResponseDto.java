package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.RolePo;

/**
 * 查询所有角色响应DTO
 * @author youcyousyunn
 * @date 2018年3月14日
 */
public class QueryAllRoleResponseDto extends BaseResponseDto {

	/**
     * 角色集合
     */
    private Set<RolePo> roles;

    /**
     * 构造函数
     */
    public QueryAllRoleResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param roles 角色集合
     */
    public QueryAllRoleResponseDto(String responseCode, Set<RolePo> roles) {
        super(responseCode);
        this.roles = roles;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryAllRoleResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

	public Set<RolePo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePo> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	
	

}
