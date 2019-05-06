package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.work.domain.po.FlowJrnPo;


/**
 * @description 查询流程流水信息返回DTO
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public class QryFlowJrnLstResponseDto extends BaseResponseDto {
    //总数
    private Integer total;
    //列表数据
    private List<FlowJrnPo> rows;
    
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
    public List<FlowJrnPo> getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(List<FlowJrnPo> rows) {
        this.rows = rows;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QryFlowJrnLstResponseDto [total=" + total + ", rows=" + rows + "]";
    }
}
