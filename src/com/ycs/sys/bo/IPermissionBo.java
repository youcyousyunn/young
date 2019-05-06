package com.ycs.sys.bo;

import java.util.List;
import java.util.Set;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.PermissionPo;

/**
 * 权限BO接口
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public interface IPermissionBo {
	
	/**
     * 根据角色查询菜单
     * @param roleNoList 角色编号集合
     * @return 权限集合
     */
    @HiBoMethod
    List<PermissionPo> queryMenu(List<String> roleNoList);

    /**
     * 获取权限
     * @param 用户编码
     * @param 类型
     * @param 状态
     * @return List<PermissionPo> 权限集合
     */
    @HiBoMethod
	List<PermissionPo> queryPermissions(String userNo, String type, String status);

    /**
     * 修改权限
     * @param permissionPo 请求参数
     * @return 修改结果(1成功0失败)
     */
    @HiBoMethod
	int updatePermission(PermissionPo permissionPo);

    /**
	 * 添加权限
	 * @param permissionPo 请求
	 * @return 主键
	 */
    @HiBoMethod
	int addPermission(PermissionPo permissionPo);

    /**
     * 删除权限(根据权限编号) 
     * @param permissionNos 权限编号集合
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int deletePermission(Set<Integer> permissionNos);

    /**
     * 根据角色编号查询权限编号
     * @param roleNos 角色编号集合
     * @return 权限编号
     */
    @HiBoMethod
	Set<Integer> queryPermissionsByRoleNos(Set<String> roleNos);

    /**
     * 根据角色编号删除权限编号
     * @param roleNos 角色编号集合
     * @return 成功数量
     */
    @HiBoMethod
	int deleteRolePermission(Set<String> roleNos);

    /**
     * 添加角色权限
     * @param roleNo 角色编号
     * @param permissionNos 权限集合
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int addRolePermission(String roleNo, Set<Integer> permissionNos);
    
    
    
    
    
    
}
