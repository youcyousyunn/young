package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 修改权限响应DTO
 * @author youcyousyunn
 * @date 2018年3月9日
 */
public class UpdatePermissionResponseDto extends BaseResponseDto {

	/**
	 * 构造函数
	 */
	public UpdatePermissionResponseDto() {
	}
	
	/**
     * 构造函数
     * @param responseCode 响应代码
     */
    public UpdatePermissionResponseDto(String responseCode) {
        this.rspCd = responseCode;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public UpdatePermissionResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

}
