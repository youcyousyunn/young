package com.ycs.sys.domain.enums;

/**
 * 用户状态枚举
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public enum EnumUserStatus {

    S("正常"), F("禁用"), D("删除");

    private EnumUserStatus(String desc) {
        this.desc = desc;
    }

    /**
     * 描述
     */
    private String desc;

    public final String getDesc() {
        return desc;
    }
    
    
    

}
