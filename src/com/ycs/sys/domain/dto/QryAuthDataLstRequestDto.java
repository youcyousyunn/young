package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 分页数据权限请求DTO
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public class QryAuthDataLstRequestDto extends BaseRequestDto {
    /**
     * 权限用户号
     */
    private String authUsrNo;
    /**
     * 搜索关键词
     */
    private String search;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 页大小
     */
    private Integer pageSize;
    
    
	public String getAuthUsrNo() {
		return authUsrNo;
	}
	public void setAuthUsrNo(String authUsrNo) {
		this.authUsrNo = authUsrNo;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QryAuthDataLstRequestDto [authUsrNo=").append(authUsrNo).append(", currentPage=")
				.append(currentPage).append(", pageSize=").append(pageSize).append("]");
		return builder.toString();
	}
    
    
    
    
    
    
    
}
