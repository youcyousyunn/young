package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询角色是否存在DTO
 * @author youcyousyunn
 * @date 2018年3月11日
 */
public class QueryRoleExistsRequestDto extends BaseRequestDto {

	/**
	 * 角色编号
	 */
	private String roleNo;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }
    
    

}
