package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.FlowActionPo;

/**
 * 查询流程工作列表返回DTO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class QryFlowActionLstResponseDto extends BaseResponseDto {
	//总数
    private Integer total;
    //列表数据
    private List<FlowActionPo> rows;
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public List<FlowActionPo> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<FlowActionPo> rows) {
		this.rows = rows;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QryFlowActionLstResponseDto [total=" + total + ", rows=" + rows + "]";
	}

    
    
    
}
