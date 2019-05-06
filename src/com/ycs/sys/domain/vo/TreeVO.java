package com.ycs.sys.domain.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * JSTree的树形菜单VO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class TreeVO {

    /**
     * 编号
     */
    private Object id;

    /**
     * 名称
     */
    private String text;

    /**
     * 父级
     */
    private Object parent;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型(自定义用来前端限制)
     */
    private Object type;
    /**
     * 分类类型
     */
    private String classType;
    
    /**
     * 分类包含商品数
     */
    private Integer count;

    /**
     * state属性,opened,disabled,selected
     */
    private Map<Object, Boolean> state;

    /**
     * 自定义属性
     */
    private Map<String, Object> data;

    /**
     * 构造函数
     */
    public TreeVO() {
        super();
    }

    /**
     * 构造函数
     * @param id 编号
     * @param text 名称
     * @param parent 父级编号
     * @param icon 图标
     */
    public TreeVO(Object id, String text, Object parent, String icon, String classType) {
        super();
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.icon = icon;
        this.classType = classType;
    }

    /**
     * 构造函数
     * @param id 编号
     * @param text 名称
     * @param parent 父级编号
     * @param icon 图标
     * @param type 类型
     */
    public TreeVO(Object id, String text, Object parent, String icon, Object type) {
        super();
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.icon = icon;
        this.type = type;
    }

    /**
     * 构造函数
     * @param id 编号
     * @param text 名称
     * @param parent 父级编号
     * @param icon 图标
     * @param type 类型
     * @param state state
     */
    public TreeVO(Object id, String text, Object parent, String icon, Object type, Map<Object, Boolean> state) {
        super();
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.icon = icon;
        this.type = type;
        this.state = state;
    }

    /**
     * 构造函数
     * @param id 编号
     * @param text 名称
     * @param parent 父级编号
     * @param icon 图标
     * @param type 类型
     * @param isDisabled 是否禁用
     * @param isOpened 是否打开
     * @param isSelected 是否选中
     */
    public TreeVO(Object id, String text, Object parent, String icon, Object type, boolean isDisabled,
            boolean isOpened, boolean isSelected) {
        super();
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.icon = icon;
        this.type = type;
        Map<Object, Boolean> state = new HashMap<>();
        state.put("disabled", isDisabled);
        state.put("opened", isOpened);
        state.put("selected", isSelected);
        this.state = state;
    }

    /**
     * 构造函数
     * @param id 编号
     * @param text 名称
     * @param parent 父级编号
     * @param icon 图标
     * @param type 类型
     * @param isDisabled 是否禁用
     * @param isOpened 是否打开
     * @param isSelected 是否选中
     * @param attr 自定义属性
     */
    public TreeVO(Object id, String text, Object parent, String icon, Object type, String classType, boolean isDisabled,
            boolean isOpened, boolean isSelected, Integer count, Map<String, Object> attr) {
        super();
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.icon = icon;
        this.type = type;
        this.classType = classType;
        Map<Object, Boolean> state = new HashMap<>();
        state.put("disabled", isDisabled);
        state.put("opened", isOpened);
        state.put("selected", isSelected);
        this.state = state;
        this.count = count;
        this.data = attr;
    }
    

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Map<Object, Boolean> getState() {
        return state;
    }

    public void setState(Map<Object, Boolean> state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
    

    
    
}
