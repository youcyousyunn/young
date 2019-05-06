package com.ycs.sys.bo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.RolePo;

/**
 * 角色BO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public interface IRoleBo {

	/**
     * 分页查角色
     * @param roleNo 角色编号
     * @param status 状态
     * @param name 名称
     * @param createDate 创建时间
     * @param sort 排序列
     * @param sortName 排序方式
     * @param row 起始行
     * @param offset 偏移量(页大小)
     * @return 角色集合
     */
    @HiBoMethod
    List<RolePo> pageQuery(String roleNo, String status, String name, Date createDate, String search, String sort, String sortName,
            Integer row, Integer offset);

    /**
     * 获取角色数量
     * 
     * @param roleNo 角色编号
     * @param status 状态
     * @param name 名称
     * @param createDate 创建时间
     * @param sort 排序列
     * @param sortName 排序方式
     * @return 数量
     */
    @HiBoMethod
    int getCount(String roleNo, String status, String name, Date createDate, String search, String sort, String sortName);

    /**
     * 添加用户角色
     * @param userNo 用户编号
     * @param roleNos 角色集合
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
    int addUserRoles(String usrNo, Set<String> roleNos);

    /**
     * 根据用户编号获取用户角色编号
     * 
     * @param userNo 用户编号
     * @return 角色编号集合
     */
    @HiBoMethod
    List<String> queryUserRoles(String userNo);

    /**
     * 添加角色
     * @param role 角色
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
    int addRole(RolePo role);

    /**
     * 根据角色编号查询角色是否存在
     * @param roleNo 角色编号
     * @return 数量
     */
    @HiBoMethod
    int queryRoleExists(String roleNo);

    /**
     * 根据角色查询是否有用户关联
     * @param roleNos 角色编号集合
     * @return 已经被关联的角色编号集合
     */
    @HiBoMethod
    Set<String> queryUserRole(Set<String> roleNos);

    /**
     * 批量删除角色
     * @param roleNos 角色编号集合
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
    int deleteRoles(Set<String> roleNos);

    /**
     * 修改角色信息
     * @param role 角色
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
    int updateRole(RolePo role);

    /**
     * 根据用户编号删除用户角色
     * @param userNo 用户编号
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
    int deleteUserRole(String usrNo);

    /**
     * 根据角色编号查询角色
     * @param roleNo 角色编号
     * @return 角色
     */
    @HiBoMethod
    RolePo queryRole(String roleNo);

    /**
     * 根据用户编号查询角色
     * 
     * @param userNo 用户编号
     * @return 角色集合
     */
    @HiBoMethod
    Set<RolePo> queryRolesByUsrNo(String usrNo);

    /**
     * 查询所有角色
     * 
     * @return 角色集合
     */
    @HiBoMethod
    Set<RolePo> queryRoles();
	
}
