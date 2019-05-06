package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询流程动作请求DTO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class QryFlowActionLstRequestDto extends BaseRequestDto {

	//动作名称
    private String actionNm;
    //排序列
    private String sort;
    //排序方式(DESC、ASC)
    private String sortName;
    //当前页
    private Integer currentPage;
    //页大小
    private Integer pageSize;
	/**
	 * @return the actionNm
	 */
	public String getActionNm() {
		return actionNm;
	}
	/**
	 * @param actionNm the actionNm to set
	 */
	public void setActionNo(String actionNm) {
		this.actionNm = actionNm;
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
	 * 接口报文检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == currentPage) {
            return false;
        }
        if (null == pageSize) {
            return false;
        }
        return true;
    }
    

}
