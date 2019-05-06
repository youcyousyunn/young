package com.ycs.coobo.shop.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询门店订单明细请求DTO
 * @author youcyousyunn
 * @date 2018年6月13日
 */
public class QryShopOrdrDetailInfoRequestDto extends BaseRequestDto {
	//门店订单号
    private String ordNo;
    //排序列
    private String sort;
    //排序方式(DESC、ASC)
    private String sortName;
    //当前页
    private Integer currentPage;
    //页大小
    private Integer pageSize;
    
	/**
	 * @return the ordNo
	 */
	public String getOrdNo() {
		return ordNo;
	}
	/**
	 * @param ordNo the ordNo to set
	 */
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}
	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
	/**
	 * 接口请求报文检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == ordNo || StringUtils.isBlank(ordNo)) {
            return false;
        }
        if (null == pageSize) {
            return false;
        }
        if (null == currentPage) {
            return false;
        }
        return true;
    }
    

}
