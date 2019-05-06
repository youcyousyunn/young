package com.ycs.coobo.coo.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.coobo.coo.domain.po.StohPrdPo;

/**
 * 查询仓库可供产品信息响应DTO
 * @author youcyousyunn
 * @date 2018年5月24日
 */
public class QryStohAllPrdLstResponseDto extends BaseResponseDto {
    //总数
    private Integer total;
    //列表数据
    private List<StohPrdPo> rows;
    //商品tree
    private String prdTreeJson;
    //商品信息
    private String prdJsonData;
    //订单分类
    private String ordCls;
    
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
    public List<StohPrdPo> getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(List<StohPrdPo> rows) {
        this.rows = rows;
    }
	public String getPrdTreeJson() {
		return prdTreeJson;
	}
	public void setPrdTreeJson(String prdTreeJson) {
		this.prdTreeJson = prdTreeJson;
	}
	public String getPrdJsonData() {
		return prdJsonData;
	}
	public void setPrdJsonData(String prdJsonData) {
		this.prdJsonData = prdJsonData;
	}
	public String getOrdCls() {
		return ordCls;
	}
	public void setOrdCls(String ordCls) {
		this.ordCls = ordCls;
	}
	
	/* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QryStohAllPrdLstResponseDto [total=" + total + ", rows=" + rows + "]";
    }
    
}
