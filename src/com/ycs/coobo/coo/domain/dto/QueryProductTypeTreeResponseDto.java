/**
 * 
 */
package com.ycs.coobo.coo.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.vo.TreeVO;


/**
 * 查询产品分类树形菜单响应DTO
 * @author youcyousyunn
 * @date 2018年5月23日
 */
public class QueryProductTypeTreeResponseDto extends BaseResponseDto {

    /**
     * 产品分类树形菜单集合
     */
    private List<TreeVO> productTypes;

    /**
     * 构造函数
     */
    public QueryProductTypeTreeResponseDto() {
        super();
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param productTypes 树形菜单
     */
    public QueryProductTypeTreeResponseDto(String responseCode, List<TreeVO> productTypes) {
        super(responseCode);
        this.productTypes = productTypes;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryProductTypeTreeResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    public List<TreeVO> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<TreeVO> productTypes) {
        this.productTypes = productTypes;
    }

}
