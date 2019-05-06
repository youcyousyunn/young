package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 删除权限请求DTO
 * @author youcyousyunn
 * @date 2018年3月9日
 */
public class DeletePermissionRequestDto extends BaseRequestDto {

	/**
     * 权限编号集合
     */
    private Set<Integer> permissionNos;

    public Set<Integer> getPermissionNos() {
        return permissionNos;
    }

    public void setPermissionNos(Set<Integer> permissionNos) {
        this.permissionNos = permissionNos;
    }

}
