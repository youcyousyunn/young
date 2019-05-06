package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.RolePo;

/**
 * 查询角色信息响应DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class QueryRoleResponseDto extends BaseResponseDto {

	/**
     * 角色
     */
    private RolePo role;

    /**
     * 角色权限集合
     */
    private Set<Integer> permissionNos;

    /**
     * 构造函数
     */
    public QueryRoleResponseDto() {
    }

    /**
     * 构造函数
     * 
     * @param responseCode 响应代码
     * @param role 角色
     * @param permissionIds 权限编号
     */
    public QueryRoleResponseDto(String responseCode, RolePo role, Set<Integer> permissionNos) {
        super(responseCode);
        this.role = role;
        this.permissionNos = permissionNos;
    }

    /**
     * 构造函数
     * 
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryRoleResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    public RolePo getRole() {
        return role;
    }

    public void setRole(RolePo role) {
        this.role = role;
    }

	public Set<Integer> getPermissionNos() {
		return permissionNos;
	}

	public void setPermissionNos(Set<Integer> permissionNos) {
		this.permissionNos = permissionNos;
	}

    
    

}
