package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 检查手机号是否存在响应DTO
 * @author youcyousyunn
 * @date 2018年3月15日
 */
public class QueryMobileExistsResponseDto extends BaseResponseDto {

	/**
	 * 手机号是否存在
	 */
	private boolean isExists = false;

	
	/**
     * 构造函数
     */
    public QueryMobileExistsResponseDto() {
        super();
    }
    
    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryMobileExistsResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param isExists 是否存在
     */
    public QueryMobileExistsResponseDto(String responseCode, boolean isExists) {
        super(responseCode);
        this.isExists = isExists;
    }

	public boolean isExists() {
		return isExists;
	}

	public void setExists(boolean isExists) {
		this.isExists = isExists;
	}

    
	
	
	
	
	
	

}
