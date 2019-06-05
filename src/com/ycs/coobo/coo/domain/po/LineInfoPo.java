package com.ycs.coobo.coo.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 线路信息PO
 * @author youcyousyunn
 * @date 2018年6月11日
 */
public class LineInfoPo extends BasePo{
    /**
     * 线路号
     */
    private String lineNo;
    /**
     * 线路名称
     */
    private String lineNm;
    /**
     * 服务仓库
     */
    private String stohNo;
    /**
     * 线路状态
     */
    private String lineSts;
    
	/**
	 * @return the lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	/**
	 * @return the lineNm
	 */
	public String getLineNm() {
		return lineNm;
	}
	/**
	 * @param lineNm the lineNm to set
	 */
	public void setLineNm(String lineNm) {
		this.lineNm = lineNm;
	}
	/**
	 * @return the stohNo
	 */
	public String getStohNo() {
		return stohNo;
	}
	/**
	 * @param stohNo the stohNo to set
	 */
	public void setStohNo(String stohNo) {
		this.stohNo = stohNo;
	}
	/**
	 * @return the lineSts
	 */
	public String getLineSts() {
		return lineSts;
	}
	/**
	 * @param lineSts the lineSts to set
	 */
	public void setLineSts(String lineSts) {
		this.lineSts = lineSts;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineInfoPo [lineNo=" + lineNo + ", lineNm=" + lineNm + ", stohNo=" + stohNo + ", lineSts=" + lineSts
				+ "]";
	}
    
    
}
