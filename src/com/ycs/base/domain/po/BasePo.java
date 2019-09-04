package com.ycs.base.domain.po;

/**
 * 公共基类PO
 * @author youcyousyunn
 * @date 2018年3月12日
 */
public abstract class BasePo {
	
	/**
     * 创建日期
     */
	/*@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date creDt;*/
	private String creDt;
	private String creTm;
    private String updDt;
    private String updTm;
    
	/**
	 * @return the creDt
	 */
	public String getCreDt() {
		return creDt;
	}
	/**
	 * @param creDt the creDt to set
	 */
	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}
	/**
	 * @return the creTm
	 */
	public String getCreTm() {
		return creTm;
	}
	/**
	 * @param creTm the creTm to set
	 */
	public void setCreTm(String creTm) {
		this.creTm = creTm;
	}
	/**
	 * @return the updDt
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * @param updDt the updDt to set
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	/**
	 * @return the updTm
	 */
	public String getUpdTm() {
		return updTm;
	}
	/**
	 * @param updTm the updTm to set
	 */
	public void setUpdTm(String updTm) {
		this.updTm = updTm;
	}
    
}
