package com.ycs.coobo.coo.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.bo.IProductBo;
import com.ycs.coobo.coo.domain.po.ProductPo;
import com.ycs.coobo.coo.service.ExportCommonExcelService;
import com.ycs.coobo.shop.bo.ShopOrdrApplyBo;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

@Service
public class ExportCommonExcelServiceImpl implements ExportCommonExcelService {

	@Autowired
    private IProductBo productBo;
	@Autowired
	private ShopOrdrApplyBo shopOrdrApplyBo;

	@Override
	public HttpServletResponse CommonExport(String downType, Map<String, String[]> parameterMap,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws HiBusinessAbortException, HiBusinessReturnException, IOException {
		@SuppressWarnings("deprecation")
		String tplPath = httpRequest.getRealPath("/");
        String fileName = "download";
        boolean creFlag = false;
        
        Context context = new Context();
        switch (downType) {
        case "1":
            fileName = "门店订单补货单";
            tplPath = tplPath + "/resources/exporttpl/shopordrtpl.xls";
            ShopOrdrPo shopOrdrPo = shopOrdrApplyBo.qryShopOrdInfo(httpRequest.getParameter("ordNo"));
            if (null != shopOrdrPo) {
                List<ShopOrdrDetailPo> shopOrdrDetailPoLst = shopOrdrApplyBo.qryShopOrdrDetailsByOrdNo(httpRequest.getParameter("ordNo"));
                // 此处的值与模板文件jstl注解一一对应
                context.putVar("ordNo", shopOrdrPo.getOrdNo());
                context.putVar("shopNm", shopOrdrPo.getShopNm());
                context.putVar("ordTyp", shopOrdrPo.getOrdTyp());
                context.putVar("ordDt", shopOrdrPo.getOrdDt());
                context.putVar("ordTm", shopOrdrPo.getOrdTm());
                context.putVar("detailLst", shopOrdrDetailPoLst);
                fileName = shopOrdrPo.getShopNm()+fileName;
                creFlag = true;
            }
            	break;
       		case "13":
       			fileName = "商品总库一览";
                tplPath = tplPath + "/resources/exporttpl/productstpl.xls";
                LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
                String city = new String(httpRequest.getParameter("city").getBytes("iso-8859-1"), "utf-8");
                String search = new String(httpRequest.getParameter("search").getBytes("iso-8859-1"), "utf-8");
                paramMap.put("city", StringUtils.isNotBlank(city) ? city : null);
                paramMap.put("search", StringUtils.isNotBlank(search) ? "%" + search.trim() + "%" : null);
                List<ProductPo> products = productBo.qryAllProductLst(paramMap);
                if (products.size() > 0) {
                    context.putVar("detailLst", products);
                    creFlag = true;
                }
        	break;
        	default:
        		break;
        }
        
        //是否创建文档
        if(creFlag){
            //创建Excel对象
            try(InputStream is = new FileInputStream(tplPath)) {
                try (OutputStream os = httpResponse.getOutputStream()) {
                    httpResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
                    httpResponse.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName+".xls").getBytes(), "iso-8859-1"));
                    JxlsHelper.getInstance().processTemplate(is, os, context);
                    is.close();
                    os.close();
                }catch(IOException e){
                	e.printStackTrace();
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return httpResponse;
	}
	
	

}
