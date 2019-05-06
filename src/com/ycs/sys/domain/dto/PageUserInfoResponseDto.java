package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 分页用户信息响应DTO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class PageUserInfoResponseDto extends BaseResponseDto {

	/**
     * 总记录数
     */
    private Integer total;

    /**
     * 用户信息集合
     */
    private List<UserInfoPo> data;

    /**
     * 构造函数
     */
    public PageUserInfoResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public PageUserInfoResponseDto(String responseCode, String message) {
        super.rspCd = responseCode;
        super.rspInf = message;
    }

    /**
     * 构造函数
     * @param total 总数
     * @param rows 数据
     * @param responseCode 响应代码
     */
    public PageUserInfoResponseDto(Integer total, List<UserInfoPo> data, String responseCode) {
        this.total = total;
        this.data = data;
        this.rspCd = responseCode;
    }

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<UserInfoPo> getData() {
		return data;
	}

	public void setData(List<UserInfoPo> data) {
		this.data = data;
	}
    
    
    
    
    

}
