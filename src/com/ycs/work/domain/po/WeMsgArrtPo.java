package com.ycs.work.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * @description 微信模板消息属性对象
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public class WeMsgArrtPo extends BasePo {
    //属性名称
    private String arrtNm;
    //属性颜色
    private String arrtColor;
    
    /**
     * @return the arrtNm
     */
    public String getArrtNm() {
        return arrtNm;
    }
    /**
     * @param arrtNm the arrtNm to set
     */
    public void setArrtNm(String arrtNm) {
        this.arrtNm = arrtNm;
    }
    /**
     * @return the arrtColor
     */
    public String getArrtColor() {
        return arrtColor;
    }
    /**
     * @param arrtColor the arrtColor to set
     */
    public void setArrtColor(String arrtColor) {
        this.arrtColor = arrtColor;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WeMsgArrtPo [arrtNm=" + arrtNm + ", arrtColor=" + arrtColor + "]";
    }
}
