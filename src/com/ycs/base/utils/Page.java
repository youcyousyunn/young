package com.ycs.base.utils;

public final class Page {

	/**
     * 总数
     */
    private Integer total;

    /**
     * 起始行
     */
    private Integer row;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 构造函数
     * @param total 总数
     * @param row 起始行
     * @param offset 偏移量
     */
    public Page(Integer total, Integer row, Integer offset) {
        super();
        this.total = total;
        this.row = row;
        this.offset = offset;
    }

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
    
    
	
	
	
}
