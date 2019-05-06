package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 查询所有用户响应DTO
 * @author youcyousyunn
 * @date 2018年3月17日
 */
public class QueryAllUserInfoResponseDto extends BaseResponseDto {

	/**
     * 用户集合
     */
    private List<UserInfoPo> userInfos;
    
    /**
     * 构造函数
     */
    public QueryAllUserInfoResponseDto() {
        super();
    }
    
    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryAllUserInfoResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param userInfos 用户集合
     */
    public QueryAllUserInfoResponseDto(String responseCode, List<UserInfoPo> userInfos) {
        super(responseCode);
        this.userInfos = userInfos;
    }

	public List<UserInfoPo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfoPo> userInfos) {
		this.userInfos = userInfos;
	}

	
	
	
	

}
