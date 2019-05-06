package com.ycs.msg.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.msg.domain.po.MsgNotifyPo;

/**
 * 发布公告响应DTO
 * @author youcyousyunn
 * @date 2019年3月20日
 */
public class QryNotifyResponseDto extends BaseResponseDto {
	//总数
    private Integer total;
    //列表数据
    private List<MsgNotifyPo> rows;
    
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<MsgNotifyPo> getRows() {
		return rows;
	}
	public void setRows(List<MsgNotifyPo> rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "QryNotifyResponseDto [total=" + total + ", rows=" + rows + "]";
	}
    
}
