package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.RolePo;

/**
 * 分页查询角色信息响应DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class QueryRolesResponseDto extends BaseResponseDto {

	/**
     * 总条数
     */
    private Integer total;

    /**
     * 分页数据
     */
    private List<RolePo> rows;
    
    /**
     * 构造函数
     */
    public QueryRolesResponseDto() {
    }
	
	/**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应消息
     */
    public QueryRolesResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<RolePo> getRows() {
		return rows;
	}

	public void setRows(List<RolePo> rows) {
		this.rows = rows;
	}
    
    
    

}
