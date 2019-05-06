package com.ycs.coobo.coo.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 产品类型PO
 * @author youcyousyunn
 * @date 2018年5月23日
 */
public class ProductTypePo extends BasePo{

    /**
     * 分类编号
     */
    private Integer classId;

    /**
     * 分类名称
     */
    private String classNm;

    /**
     * 分类描述
     */
    private String classDesc;

    /**
     * 分类级别
     */
    private Integer classLvl;

    /**
     * 分类
     */
    private String classType;

    /**
     * 父级分类
     */
    private Integer fClassId;

    /**
     * 分类排序
     */
    private String orderNum;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassNm() {
        return classNm;
    }

    public void setClassNm(String classNm) {
        this.classNm = classNm;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public Integer getClassLvl() {
        return classLvl;
    }

    public void setClassLvl(Integer classLvl) {
        this.classLvl = classLvl;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getfClassId() {
        return fClassId;
    }

    public void setfClassId(Integer fClassId) {
        this.fClassId = fClassId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductTypePo [classId=" + classId + ", classNm=" + classNm + ", classDesc=" + classDesc
                + ", classLvl=" + classLvl + ", classType=" + classType + ", fClassId=" + fClassId + ", orderNum="
                + orderNum + "]";
    }
}
