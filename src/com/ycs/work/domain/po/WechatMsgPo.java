package com.ycs.work.domain.po;

import java.util.List;
import com.ycs.base.domain.po.BasePo;

/**
 * @description 微信模板通知定义对象
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public class WechatMsgPo extends BasePo {
    //模板ID
    private String tempId;
    //模板名称
    private String tempNm;
    //测试模板ID
    private String uatId;
    //生产模板ID
    private String prdId;
    //链接地址
    private String tempUrl;
    //标题颜色
    private String topColor;
    //属性值列表
    private List<WeMsgArrtPo> data;
    
    /**
     * @return the tempId
     */
    public String getTempId() {
        return tempId;
    }
    /**
     * @param tempId the tempId to set
     */
    public void setTempId(String tempId) {
        this.tempId = tempId;
    }
    /**
     * @return the tempNm
     */
    public String getTempNm() {
        return tempNm;
    }
    /**
     * @param tempNm the tempNm to set
     */
    public void setTempNm(String tempNm) {
        this.tempNm = tempNm;
    }
    /**
     * @return the uatId
     */
    public String getUatId() {
        return uatId;
    }
    /**
     * @param uatId the uatId to set
     */
    public void setUatId(String uatId) {
        this.uatId = uatId;
    }
    /**
     * @return the prdId
     */
    public String getPrdId() {
        return prdId;
    }
    /**
     * @param prdId the prdId to set
     */
    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }
    /**
     * @return the tempUrl
     */
    public String getTempUrl() {
        return tempUrl;
    }
    /**
     * @param tempUrl the tempUrl to set
     */
    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }
    /**
     * @return the topColor
     */
    public String getTopColor() {
        return topColor;
    }
    /**
     * @param topColor the topColor to set
     */
    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }
    /**
     * @return the data
     */
    public List<WeMsgArrtPo> getData() {
        return data;
    }
    /**
     * @param data the data to set
     */
    public void setData(List<WeMsgArrtPo> data) {
        this.data = data;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WechatMsgPo [tempId=" + tempId + ", tempNm=" + tempNm + ", uatId=" + uatId + ", prdId=" + prdId
                + ", tempUrl=" + tempUrl + ", topColor=" + topColor + ", data=" + data + "]";
    }
}
