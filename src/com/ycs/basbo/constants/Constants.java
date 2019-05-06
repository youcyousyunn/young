package com.ycs.basbo.constants;

public class Constants {
	public static final String USER="USR";
	
	/**
     * 用户信息session相关
     */
	public static final String SESSION_EMAIL_CODE="session.emailcode";
	public static final String SESSION_RUN_MODE="session.runmode";
	public static final String SESSION_STATUS_ON="on";
	public static final String SESSION_STATUS_OFF="off";
	public static final String USER_SESSION_USRNO="USRNO";
    public static final String USER_SESSION_USERNAME="USERNAME";
    public static final String USER_SESSION_TOKENID="TOKENID";
    public static final String USER_SESSION_REGDT="REGDT";
    public static final String USER_SESSION_INFO="userInfo";
    public static final String USER_SESSION_MOBILE = "USERMOBILE";
    
    /**
     * 文件上传
     */
    public static final String IMG_UPLOAD = "img.upload";
    
    /**
     * 需要排除权限校验的接口权限
     */
    public static enum OUT_AUTH_URL {
        NORMAL("0000"),ALLOW("1111");
        private String enumVal;
        
        private OUT_AUTH_URL(String enumVal) {
            this.enumVal = enumVal;
        }
        
        @Override
        public String toString() {
            return String.valueOf(this.enumVal);
        }
    };
    
    /** 默认当前页 */
    public static final int DEFAULT_CURRENT_PAGE = 1;
    /** 页大小 */ 
    public static final int DEFAULT_PAGE_SIZE = 10;
    /** 默认总条数 */
    public static final int DEFAULT_TOTAL_COUNT = 0;
    /** 默认总页数 */
    public static final int DEFAULT_TOTAL_PAGE = 0;
    /** 默认起始条数 */
    public static final int DEFAULT_ROW = 0;
    
    
    /**
     * 部门类型分类
     */
    public final static String D_NORMAL = "NORMAL";//普通部门
    public final static String D_COMPAN = "COMPAN";//运营公司
    public final static String D_STOH = "STOH";//仓库
    public final static String D_SHOP = "SHOP";//门店
    public final static String D_SUPPLI = "SUPPLI";//供应商
    
    /**
     * 订单类别
     */
    public final static String MDBH = "MDBH";//门店补货单
    public final static String YYPH = "YYPH";//门店运营铺货
    public final static String MDDB = "MDDB";//门店调拨
    public final static String MDDT = "MDDT";//门店提货
    public final static String MDBS = "MDBS";//门店报损
    public final static String MDTH = "MDTH";//门店退货
    public final static String MDPH = "MDPH";//门店配货单
    public final static String MDCG = "MDCG";//门店转仓库采购
    public final static String CKBH = "CKBH";//仓库补货单
    public final static String CKDB = "CKDB";//仓库调拨
    public final static String CKBS = "CKBS";//仓库报损
    public final static String CKPH = "CKPH";//仓库配货单
    public final static String CGRK = "CGRK";//仓库采购入库
    public final static String THRK = "THRK";//仓库退货入库
    public final static String QTCK = "QTCK";//其它出库
    public final static String QTRK = "QTRK";//其它入库
    public final static String WLPS = "WLPS";//物流配送
    
    /**
     * 门店订单有效期
     */
    public final static Integer SHOP_ORD_EXP_DAY = 10;
    
    /**
     * 门店订单分类
     */
    public final static String NORMAL = "NORMAL";//普通订单
    public final static String MATER = "MATER";//物料订单
    public final static String BFOOD = "BFOOD";//早餐饮订单
    public final static String LFOOD = "LFOOD";//午餐饮订单
    public final static String FRESH = "FRESH";//生鲜订单
    
    /** 门店普通补货订单添加 */
    public final static String FLOW_ADDSHOPNORMALORDR_PROCESS = "FLOW_ADDSHOPNORMALORDR_PROCESS";
    /** 门店生鲜补货订单添加 */
    public final static String FLOW_ADDSHOPFRESHORDR_PROCESS = "FLOW_ADDSHOPFRESHORDR_PROCESS";
    /** 门店餐饮补货订单添加 */
    public final static String FLOW_ADDSHOPFOODORDR_PROCESS = "FLOW_ADDSHOPFOODORDR_PROCESS";
    /** 门店物料补货订单添加 */
    public final static String FLOW_ADDSHOPMATERORDR_PROCESS = "FLOW_ADDSHOPMATERORDR_PROCESS";
    
    
    /**
     * 消息类型
     */
    public static final String MSG_TYP_FLOW = "FLOW";//流程
    public static final String MSG_TYP_ANNOUNCE = "ANNOUNCE";//公告
    
    /**
     * 流程ID前缀
     */
    public final static String FLOW_ID = "FLOW";
    
    /**
     * 流程状态
     */
    public static final String FLOW_STS_A1 = "A1";//申请中
    public static final String FLOW_STS_C1 = "C1";//进行中
    public static final String FLOW_STS_S1 = "S1";//已完成
    public static final String FLOW_STS_F1 = "F1";//已作废
    
    /**
     * 流程交易类型
     */
    public static final String FLOW_TX_TYPE_CRE = "CRE";//创建
    public static final String FLOW_TX_TYPE_VER = "VER";//审核
    public static final String FLOW_TX_TYPE_ACP = "ACP";//确认
    
    /**
    * 微信模板
    */
   public static final String ORD_ACCEPT_NOTIFY="TM00530";
   
   /**
    * 系统运行环境
    */
   public static final String SYS_ENV = "sys.runmode";
   public static final String SYS_ENV_DEV = "develop";
   public static final String SYS_ENV_PRD = "product";
   public static final String SYS_ENV_HOST_ADDR = "host.host_addr";
   
   
}
