package com.ycs.basbo.enums;

/**
 * @description 目标类型枚
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public enum TargetTypEnum {
    COMPAN("运营公司","COMPAN"), STOHOSE("仓库","STOHOSE"), SHOP("门店","SHOP"), PIRCE("价格","PIRCE"), RECVSHOP("门店订单收货","RECVSHOP"), SHOPORDR("门店普通订单","SHOPORDR"), SHOPFOODORDR(
            "门店餐饮订单","SHOPFOODORDR"), SHOPFRESHORDR("门店生鲜订单","SHOPFRESHORDR"), SHOPEXCP("门店异常订单","SHOPEXCP"), STOHORDR("仓库普通订单","STOHORDR");
    
    // 成员变量  
    private String name;
    private String index;
    
    // 构造方法  
    private TargetTypEnum(String name, String index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(String index) {  
        for (TargetTypEnum c : TargetTypEnum.values()) {  
            if (c.getIndex().equals(index)) {
                return c.name;
            }
        }
        return "订单";  
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the index
     */
    public String getIndex() {
        return index;
    }
    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }
}
