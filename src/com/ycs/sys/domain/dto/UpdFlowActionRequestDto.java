package com.ycs.sys.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 更改流程动作部门或者岗位请求DTO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class UpdFlowActionRequestDto extends BaseRequestDto {

	//动作名称
    private String actionNo;
    //动作节点
    private String nodeNo;
    //部门号
    private String deparNo;
    //岗位号
    private String postId;
	/**
	 * @return the actionNo
	 */
	public String getActionNo() {
		return actionNo;
	}
	/**
	 * @param actionNo the actionNo to set
	 */
	public void setActionNo(String actionNo) {
		this.actionNo = actionNo;
	}
	/**
	 * @return the nodeNo
	 */
	public String getNodeNo() {
		return nodeNo;
	}
	/**
	 * @param nodeNo the nodeNo to set
	 */
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	/**
	 * @return the deparNo
	 */
	public String getDeparNo() {
		return deparNo;
	}
	/**
	 * @param deparNo the deparNo to set
	 */
	public void setDeparNo(String deparNo) {
		this.deparNo = deparNo;
	}
	/**
	 * @return the postId
	 */
	public String getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
    
	/**
	 * 请求报文接口检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == actionNo || StringUtils.isBlank(actionNo)) {
            return false;
        }
        if (null == nodeNo || StringUtils.isBlank(nodeNo)) {
            return false;
        }
        return true;
    }
    
	
	

}
