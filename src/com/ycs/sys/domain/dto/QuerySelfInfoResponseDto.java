package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.RolePo;
import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 查询用户信息响应DTO
 * @author youcyousyunn
 * @date 2018年3月23日
 */
public class QuerySelfInfoResponseDto extends BaseResponseDto {

	/**
     * 用户信息
     */
    private UserInfoPo userInfo;

    /**
     * 角色信息
     */
    private Set<RolePo> roles;

    /**
     * 构造函数
     */
    public QuerySelfInfoResponseDto() {
    }
    
    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QuerySelfInfoResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param userInfo 用户信息
     * @param roles 角色信息
     */
    public QuerySelfInfoResponseDto(String responseCode, UserInfoPo userInfo, Set<RolePo> roles) {
        super(responseCode);
        this.userInfo = userInfo;
        this.roles = roles;
    }

	public UserInfoPo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPo userInfo) {
		this.userInfo = userInfo;
	}

	public Set<RolePo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePo> roles) {
		this.roles = roles;
	}

    
    
    
    
    
    

}
