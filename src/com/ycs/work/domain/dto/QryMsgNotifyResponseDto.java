package com.ycs.work.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.msg.domain.po.NotifyPo;


/**
 * 工作台消息请求响应DTO
 * @author youcyousyunn
 * @date 2018年6月25日
 */
public class QryMsgNotifyResponseDto extends BaseResponseDto {
    //总数
    private Integer total;
    //列表数据
    private List<NotifyPo> rows;
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
    public List<NotifyPo> getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(List<NotifyPo> rows) {
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
        return "QryMsgNotifyResponseDto [total=" + total + ", rows=" + rows + "]";
    }
}
