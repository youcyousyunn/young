package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.vo.PermissionVO;

/**
 * 添加权限响应DTO
 * @author youcyousyunn
 * @date 2018年3月9日
 */
public class AddPermissionResponseDto extends BaseResponseDto {

	/**
	 * 权限
	 */
	private PermissionVO permission;
	
	/**
	 * 构造函数
	 */
	public AddPermissionResponseDto() {
	}
	
	/**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public AddPermissionResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param permission 权限信息
     */
    public AddPermissionResponseDto(String responseCode, PermissionVO permission) {
        this.rspCd = responseCode;
        this.permission = permission;
    }

    public PermissionVO getPermission() {
        return permission;
    }

    public void setPermission(PermissionVO permission) {
        this.permission = permission;
    }
    

}
