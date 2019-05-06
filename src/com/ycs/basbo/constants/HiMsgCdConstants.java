package com.ycs.basbo.constants;

public class HiMsgCdConstants {

	/*** 成功 */
    public static final String SUCCESS = "00000";
    /** SYS_FAIL,系统错误 */
    public static final String SYS_FAIL = "99999";
    /*** 部分成功 */
    public static final String PARTIAL_SUCCESS = "00001";
    /*** 接口请求报文异常 ****/
    public static final String TX_REQUESTBODY_FAIL = "T0004";
    /**  接口请求参数异常 */
    public static final String TX_REQUESTPRAM_FAIL = "T0005";
    
    /*** 用户名已存在 */
    public static final String USERNAME_EXISTED_INVALID = "M0000";
    /*** 用户名或密码错误 */
    public static final String USERNAME_OR_PASSWORD_INVALID = "M0001";
    /*** 获取菜单失败 */
    public static final String GET_MENUS_ERROR = "M0002";
    /*** 查询菜单返回empty */
    public static final String GET_MENUS_RETURN_EMPTY = "M0003";
    /*** 查询角色返回empty */
    public static final String QUERY_ROLES_EMPTY = "M0004";
    /*** 查询角色失败 */
    public static final String QUERY_ROLES_FAIL = "M0005";
    /*** 查询用户失败 */
    public static final String QUERY_USERINFO_FAIL = "M0006";
    /*** 获取权限集合返回empty */
    public static final String GET_PERMISSION_RETURN_EMPTY = "M0007";
    /*** 添加权限失败 */
    public static final String ADD_PERMISSION_FAIL = "M0008";
    /*** 修改权限失败 */
    public static final String UPDATE_PERMISSION_FAIL = "M0009";
    /*** 添加用户失败 */
    public static final String ADD_USER_FAIL = "M0010";
    /*** 添加用户角色失败 */
    public static final String ADD_USER_ROLE_FAIL = "M0011";
    /*** 删除权限失败 */
    public static final String DELETE_PERMISSION_FAIL = "M0012";
    /*** 添加角色失败 */
    public static final String ADD_ROLE_FAIL = "M0013";
    /*** 添加角色权限失败 */
    public static final String ADD_ROLE_PERMISSION_FAIL = "M0014";
    /*** 查询角色是否存在失败 */
    public static final String QUERY_ROLE_ISEXISTS_FAIL = "M0015";
    /*** 所有角色已经被用户 关联 */
    public static final String ALL_ROLE_RELATED_USER = "M0016";
    /*** 删除角色失败 */
    public static final String DELETE_ROLE_FAIL = "M0017";
    /*** 删除角色权限失败 */
    public static final String DELETE_ROLE_PERMISSION_FAIL = "M0018";
    /*** 更新角色失败 */
    public static final String UPDATE_ROLE_FAIL = "M0019";
    /*** 更新角色权限失败 */
    public static final String UPDATE_ROLE_PERMISSION_FAIL = "M0020";
    /*** 修改用户信息失败 */
    public static final String UPDATE_USER_INFO_FAIL = "M0021";
    /*** 修改用户密码失败 */
    public static final String UPDATE_USER_PASSWORD_FAIL = "M0022";
    /*** 删除用户失败 */
    public static final String DELETE_USER_INFO_FAIL = "M0023";
    /*** 删除用户角色失败 */
    public static final String DELETE_USER_ROLE_FAIL = "M0024";
    /*** 查询角色信息失败 */
    public static final String QUERY_ROLE_FAIL = "M0025";
    /*** 未查询到角色 */
    public static final String ROLE_NOT_FOUND = "M0026";
    /*** 用户信息未找到 */
    public static final String USER_INFO_NOT_FOUND = "M0027";
    /*** 未查询到部门信息 */
    public static final String DEPTMENT_NOT_FOUND = "M0028";
    /*** 查询部门信息失败 */
    public static final String QUERY_DEPTMENT_FAIL = "M0029";
    /*** 查询工号是否存在失败 */
    public static final String QUERY_JOBNO_EXISTS_FAIL = "M0030";
    /*** 查询手机号是否存在失败 */
    public static final String QUERY_MOBILE_EXISTS_FAIL = "M0031";
    /*** 新增部门失败 */
    public static final String ADD_DEPTMENT_FAIL = "M0032";
    /*** 修改部门失败 */
    public static final String UPDATE_DEPTMENT_FAIL = "M0033";
    /*** 修改部门名称失败 */
    public static final String RENAME_DEPTMENT_FAIL = "M0035";
    /*** 修改头像失败 */
    public static final String UPDATE_USER_HEAD_FAIL = "M0036";
    /*** 查询部门岗位信息失败 */
    public static final String QUERY_DEPTPOST_FAIL = "M0050";
    /*** 注册用户发送验证码失败 */
    public static final String SEND_IDENTIFY_CODE_FAIL = "M0051";
    /*** 查询用户数据权限失败 */
    public static final String QUERY_DATARULE_FAIL = "M0061";
    /*** 增加流程失败 */
    public static final String ADD_FLOWINFO_FAIL = "M0064";
    /*** 增加公告失败 */
    public static final String ADD_ANNOUNCE_FAIL = "M0065";
    
    
    /*** 添加运营公司错误 */
    public static final String ADD_COMPAN_FAIL = "C0001";
    /*** 添加门店错误 */
    public static final String ADD_STORE_FAIL = "C0002";
    /*** 添加仓库错误 */
    public static final String ADD_STOHOSE_FAIL = "C0003";
    /*** 查询运营公司信息失败 */
    public static final String GET_COMPANINFO_FAIL = "C0004";
    /*** 变更运营公司错误 */
    public static final String UPD_COMPAN_FAIL = "C0005";
    /*** 变更门店错误 */
    public static final String UPD_STORE_FAIL = "C0006";
    /*** 变更仓库错误 */
    public static final String UPD_STOHOSE_FAIL = "C0007";
    /*** 查询仓库信息失败 */
    public static final String GET_STOHOSEINFO_FAIL = "C0012";
    /*** 查询产品分类失败 */
    public static final String QUERY_PRODUCT_TYPE_FAIL = "C0013";
    /*** 没有审核权限 */
    public static final String UN_OPER_AUTH = "C0029";
    /*** 查询门店不存在商品批发价格失败 */
    public static final String QUERY_SHOP_PRICE_NOT_EXISTS_FAIL = "C0044";
    /*** 增加门店订单失败 */
    public static final String ADD_SHOPORDR_FAIL = "C0046";
    /*** 增加门店订单明细失败 */
    public static final String ADD_SHOPORDR_DETAIL_FAIL = "C0047";
    /*** 没有申请门店订单权限 */
    public static final String NOTHAVE_SHOPORDR_AUTH_FAIL = "C0050";
    /*** 非可处理订单状态 */
    public static final String NOTUPD_ORD_STS_FAIL = "C0068";
    /*** 查询供应商商品定价失败 */
    public static final String QUERY_SUPPLI_PRICE_FAIL = "C0082";
    /*** 增加仓库订单明细失败 */
    public static final String ADD_STOHORDR_DETAIL_FAIL = "C0097";
    /*** 增加仓库订单失败 */
    public static final String ADD_STOHORDR_FAIL = "C0098";
    
    
    /*** 更新流程动作节点信息失败 */
    public static final String UPD_ACTION_INFO_FAIL = "C0201";
    /*** 非同一供货仓库无法合并订单 */
    public static final String NOT_RQSTOH_FAIL = "C0254";
    /*** 更新消息是否已读失败 */
    public static final String UPD_MSG_USER_NOTIFY_FAIL = "C0294";
    
    
    /** ITF_CONNECT_FAIL,连接失败 */
    public static final String ITF_CONNECT_FAIL = "F0000";
    /** ITF_SEND_FAIL,发送请求失败 */
    public static final String ITF_SEND_FAIL = "F0001";
    /** ITF_CLIENT_NOT_EXIST,客户端不存在 */
    public static final String ITF_CLIENT_NOT_EXIST = "F1000";

    
}
