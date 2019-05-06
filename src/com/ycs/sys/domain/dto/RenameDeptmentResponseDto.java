package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 更改部门名称相应DTO
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public class RenameDeptmentResponseDto extends BaseResponseDto {

	/**
     * 构造函数
     */
    public RenameDeptmentResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     */
    public RenameDeptmentResponseDto(String responseCode) {
        super(responseCode);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public RenameDeptmentResponseDto(String responseCode, String message) {
        super(responseCode, message);
    }
	
	
	
	

}
