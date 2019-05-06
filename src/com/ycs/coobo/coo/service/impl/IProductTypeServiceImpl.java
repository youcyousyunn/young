package com.ycs.coobo.coo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.basbo.enums.EnumStatus;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.bo.IProductBo;
import com.ycs.coobo.coo.bo.IProductTypeBo;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeResponseDto;
import com.ycs.coobo.coo.domain.po.ProductTypePo;
import com.ycs.coobo.coo.service.IProductTypeService;
import com.ycs.sys.domain.vo.TreeVO;

@Service
public class IProductTypeServiceImpl implements IProductTypeService {

	/**
     * 产品分类BO
     */
    @Autowired
    private IProductTypeBo productTypeBo;
    
    /**
     * 产品BO
     */
    @Autowired
    private IProductBo productBo;

	@Override
	@Transactional(readOnly=true)
	public QueryProductTypeTreeResponseDto queryProductTypeTree(QueryProductTypeTreeRequestDto request)
			throws HiBusinessReturnException {
		if (null != request) {
			// 查询所有产品分类
            List<ProductTypePo> productTypes = productTypeBo.queryAllProductsType(request.getType());
            if (!CollectionUtils.isEmpty(productTypes)) {
                Map<String, Object> paramMap = new LinkedHashMap<>();
                paramMap.put("level", "#");
                paramMap.put("edit", false);
                paramMap.put("child", true);
                HashMap<String, Integer> countMap = null;
                if (request.isCountProduct()) {
                    // 根据产品分类统计该分类下的产品数量
                    countMap = productBo.countProductTypeNum(request.getProv(), request.getCity(), Arrays.asList(EnumStatus.S.name()));
                    if (null == countMap) {
                        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_PRODUCT_TYPE_FAIL,"获取产品分类失败，统计产品总数出错");
                    }
                }
                int counts = 0;
                List<TreeVO> treeVos = new ArrayList<>();
                TreeVO treeVo = new TreeVO(0, "全部", "#", "fa fa-folder-o", "#", null, false, true, true, counts, paramMap);
                treeVos.add(treeVo);
                
                for (ProductTypePo p : productTypes) {
                    TreeVO tree = null;
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("level", p.getClassLvl());
                    attr.put("edit", true);
                    int count = 0;
                    if (request.isCountProduct()) {
                        // 组装模糊匹配key
                        count = null == countMap.get(String.valueOf(p.getClassId())) ? 0 : countMap.get(String
                                .valueOf(p.getClassId()));
                        counts += count;
                        attr.put("count", count);
                    }
                    if (p.getClassLvl().intValue() == 4) {
                        attr.put("child", false);
                        tree = new TreeVO(p.getClassId(), p.getClassNm(), p.getfClassId(), "fa fa-folder-o",
                                p.getClassLvl(), p.getClassType(), false, false, false, count, attr);
                    } else {
                        attr.put("child", true);
                        tree = new TreeVO(p.getClassId(), p.getClassNm(), p.getfClassId(), "fa fa-folder-o",
                                p.getClassLvl(), p.getClassType(), false, false, false, count, attr);
                    }
                    treeVos.add(tree);
                }
                paramMap.put("count", counts);
                
                Integer levl = 4;//最高层级
                boolean flag = true;
                while(flag){
                    levl-=1;
                    List<TreeVO> treeVoLst = new ArrayList<>();
                    for (TreeVO tree : treeVos) {
                        if(tree.getData().get("level").equals(levl)){
                            treeVoLst.add(tree);
                        }
                    }
                    totTreeCount(treeVos,treeVoLst);
                    if(levl==0)
                        flag = false;
                }
                return new QueryProductTypeTreeResponseDto(HiMsgCdConstants.SUCCESS, treeVos);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_PRODUCT_TYPE_FAIL, "query product type faild");
	}
	
	/**
	 * 统计产品分类树层级总数
	 * @param treeVos
	 * @param treeVoLst
	 * @return boolean
	 */
	private boolean totTreeCount(List<TreeVO> treeVos, List<TreeVO> treeVoLst) {
        for (TreeVO treeVO1 : treeVoLst) {
            int count = 0;
            for (TreeVO treeVO2 : treeVos) {
                if (treeVO1.getId().equals(treeVO2.getParent())) {
                    count += treeVO2.getCount();
                }
            }
            treeVO1.setCount(count);
            treeVO1.getData().put("count", count);
        }
        return true;
    }
    
    
    
    

}
