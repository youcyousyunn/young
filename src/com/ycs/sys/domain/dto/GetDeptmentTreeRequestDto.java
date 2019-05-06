package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 部门树形菜单请求DTO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class GetDeptmentTreeRequestDto extends BaseRequestDto {

	/**
     * 是否显示全部按钮
     */
    private boolean showAll = false;

	public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
    
    
    
    

}
