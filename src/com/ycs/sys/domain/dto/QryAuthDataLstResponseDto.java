package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.DataAuthPo;

/**
 * 查询数据权限页面数据响应DTO
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public class QryAuthDataLstResponseDto extends BaseResponseDto {

	/**
     * 总记录数
     */
    private Integer total;
    
    /**
     * 用户信息集合
     */
    private List<DataAuthPo> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<DataAuthPo> getRows() {
		return rows;
	}

	public void setRows(List<DataAuthPo> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "QryAuthDataLstResponseDto [total=" + total + ", rows=" + rows + "]";
	}
    
    
    
    

}
