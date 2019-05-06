package com.ycs.coobo.shop.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.basbo.utils.JrnGenerator;
import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.utils.AmonutCalcuUtil;
import com.ycs.base.utils.DateUtil;
import com.ycs.base.utils.PageUtil;
import com.ycs.coobo.coo.bo.ILineBo;
import com.ycs.coobo.coo.bo.IProductBo;
import com.ycs.coobo.coo.bo.ProductPriceBo;
import com.ycs.coobo.coo.domain.po.LineInfoPo;
import com.ycs.coobo.coo.domain.po.ProductPo;
import com.ycs.coobo.coo.domain.po.WhslePricePo;
import com.ycs.coobo.shop.bo.ShopMngBo;
import com.ycs.coobo.shop.bo.ShopOrdrApplyBo;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrDetailInfoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrDetailInfoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrInfoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrInfoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrLstRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrLstResponseDto;
import com.ycs.coobo.shop.domain.dto.ReviewShopOrdrRequestDto;
import com.ycs.coobo.shop.domain.dto.ReviewShopOrdrResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrApplyRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrApplyResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinSubmitRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinSubmitResponseDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrCombinPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;
import com.ycs.coobo.shop.domain.po.ShopPo;
import com.ycs.coobo.shop.service.ShopOrdrService;
import com.ycs.coobo.stoh.bo.StoHoseMngBo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrDetailPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrPo;
import com.ycs.coobo.stoh.domain.po.StoHosePo;
import com.ycs.coobo.suppli.domain.po.SuppliPricePo;
import com.ycs.sys.bo.DataAuthBo;
import com.ycs.sys.bo.FlowBo;
import com.ycs.sys.domain.po.DataAuthPo;
import com.ycs.work.domain.po.FlowInfoPo;
import com.ycs.work.domain.po.FlowOrdPo;
import com.ycs.work.domain.po.WorkFlowCmmParamPo;
import com.ycs.work.service.WorkFlowCmmService;

@Service
public class ShopOrdrServiceImpl implements ShopOrdrService {
	@Autowired
    private FlowBo flowBo;
	@Autowired
	private DataAuthBo dataAuthBo;
	@Autowired
	private ShopMngBo shopMngBo;
	@Autowired
	private StoHoseMngBo stoHoseMngBo;
	@Autowired
    private ILineBo iLineBo;
	@Autowired
    private ProductPriceBo productPriceBo;
	@Autowired
    private IProductBo iProductBo;
	@Autowired
	private ShopOrdrApplyBo shopOrdrApplyBo;
	@Autowired
	private WorkFlowCmmService workFlowCmmService;

	@Override
	public QryShopOrdrLstResponseDto qryShopOrdrLst(QryShopOrdrLstRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        
        // 查询数据权限
        DataAuthPo dataAuthPo = dataAuthBo.qryDataAuthByUsrNo(request.getUsrNo());
        if (null == dataAuthPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DATARULE_FAIL, "查询用户数据权限失败");
        }
        
        paramMap.put("prov", null != dataAuthPo.getProv() ? dataAuthPo.getProv() : null);
        paramMap.put("city", null != dataAuthPo.getCity() ? dataAuthPo.getCity() : null);
        paramMap.put("ordNo", null != request.getOrdNo() ? request.getOrdNo() : null);
        paramMap.put("shopNo", null != request.getShopNo() ? request.getShopNo() : null);
        paramMap.put("shopNm", null != request.getShopNm() ? "%" + request.getShopNm() + "%" : null);
        paramMap.put("lineNo", null != request.getLineNo() ? request.getLineNo() : null);
        paramMap.put("ordSts", null != request.getOrdSts() ? request.getOrdSts() : null);
        paramMap.put("ordTyp", !"".equals(request.getOrdTyp()) ? request.getOrdTyp() : null);
        paramMap.put("ordCls", !"".equals(request.getOrdCls()) ? request.getOrdCls() : null);
        paramMap.put("ordDt", null != request.getOrdDt() ? request.getOrdDt() : null);
        paramMap.put("sendDt", null != request.getSendDt() ? request.getSendDt() : null);
        paramMap.put("stohNo", null != request.getStohNo() ? request.getStohNo() : null);
        
        // 删除条件Key对应为空的参数
        Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            Object key = entry.getKey();
            if (null == paramMap.get(key) || "".equals(paramMap.get(key))) {
                iter.remove();
            }
        }

        // 首先查询共有多少记录
        int totalCount = shopOrdrApplyBo.qryShopOrdrCount(paramMap);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());

        // 分页查询
        List<ShopOrdrPo> shopOrdrPoLst = shopOrdrApplyBo.qryShopOrdrLst(paramMap, PageUtil.getStartRow(),
                PageUtil.getOffset());
        // 组装响应信息
        QryShopOrdrLstResponseDto response = new QryShopOrdrLstResponseDto();
        response.setRows(shopOrdrPoLst);
        response.setTotal(totalCount);
        return response;
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessAbortException.class, HiBusinessReturnException.class })
	public ShopOrdrApplyResponseDto shopOrdrApply(ShopOrdrApplyRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		// 根据下单人内部用户号获取门店信息
		ShopPo shopPo = shopMngBo.qryShopInfoByUsrNo(request.getUsrNo());
		if (null == shopPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.NOTHAVE_SHOPORDR_AUTH_FAIL, "只能由门店人员申请订单！");
        }
		
		// 订单头部标识判断
        String ordNo = null;
        switch (request.getOrdrTyp()) {
        case "02":
            ordNo = Constants.MDDB + JrnGenerator.genJrnNo();
            break;
        case "03":
            ordNo = Constants.MDBS + JrnGenerator.genJrnNo();
            break;
        case "04":
            ordNo = Constants.MDBS + JrnGenerator.genJrnNo();
            break;
        case "05":
            ordNo = Constants.QTCK + JrnGenerator.genJrnNo();
            break;
        case "06":
            ordNo = Constants.QTRK + JrnGenerator.genJrnNo();
            break;
        case "07":
            ordNo = Constants.YYPH + JrnGenerator.genJrnNo();
            break;
        default:
            ordNo = Constants.MDBH + JrnGenerator.genJrnNo();
            break;
        }
        
        // 创建主订单
        ShopOrdrPo shopOrdrPo = new ShopOrdrPo();
        shopOrdrPo.setOrdNo(ordNo);
        shopOrdrPo.setOrdCnl(request.getChannelTyp());
        shopOrdrPo.setOrdTyp(request.getOrdrTyp());
        shopOrdrPo.setOrdCls(request.getOrdCls());
        shopOrdrPo.setOrdSts("W");
        shopOrdrPo.setShopNo(shopPo.getShopNo());
        shopOrdrPo.setShopNm(shopPo.getShopNm());
        shopOrdrPo.setStohNo(shopPo.getStohNo());
        shopOrdrPo.setStohNm(shopPo.getStohNm());
        // 根据仓库号，门店号获取门店订单线路信息
        LineInfoPo lineInfoPo = iLineBo.qryLineInfoByShop(shopPo.getStohNo(), shopPo.getShopNo());
        if (null != lineInfoPo) {
            shopOrdrPo.setLineNo(lineInfoPo.getLineNo());
            shopOrdrPo.setLineNm(lineInfoPo.getLineNm());
        }
        shopOrdrPo.setProv(shopPo.getProv());
        shopOrdrPo.setCity(shopPo.getCity());
        shopOrdrPo.setDistrict(shopPo.getDistrict());
        String ordDt = DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd);
        String ordTm = DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss);
        String expDt = DateUtil.calculateDate(ordDt, DateUtil.DAY, Constants.SHOP_ORD_EXP_DAY, DateUtil.dataFormatyyyy_MM_dd);
        shopOrdrPo.setOrdDt(ordDt);
        shopOrdrPo.setSendDt(StringUtils.isNotBlank(request.getOrdDt())?request.getOrdDt():ordDt);
        shopOrdrPo.setOrdTm(ordTm);
        shopOrdrPo.setEffDay(""+Constants.SHOP_ORD_EXP_DAY);
        shopOrdrPo.setExpDt(expDt);
        shopOrdrPo.setExpTm(ordTm);
        // 主订单总金额
        BigDecimal ordrTotAmt = new BigDecimal(0);
        
        // 创建订单明细
        List<ShopOrdrDetailPo> shopOrdrDetailLst = new ArrayList<ShopOrdrDetailPo>();
        List<ShopOrdrDetailPo> requestShopOrdrDetailLst = request.getShopOrdrPrdLst();
        // 查询订单商品批发价格
        List<String> barCodeLst = new ArrayList<>();
        for (int i = 0; i < requestShopOrdrDetailLst.size(); i++) {
            barCodeLst.add(requestShopOrdrDetailLst.get(i).getBarCode());
        }
        // 查询商品批发价格
        List<WhslePricePo> whslePriceLst = productPriceBo.qryWhslePriceByCodeLst(barCodeLst, shopPo.getProv(), shopPo.getCity(), shopPo.getShopType());
        if (null == whslePriceLst || barCodeLst.size() != whslePriceLst.size()) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_SHOP_PRICE_NOT_EXISTS_FAIL, "查询门店不存在商品批发价格失败！");
        }
        // 查询商品信息
        List<ProductPo> productlst = iProductBo.qryProductsByCodeLst(barCodeLst);
        for (int i=0; i<requestShopOrdrDetailLst.size(); i++){
        	ShopOrdrDetailPo shopOrdrDetailPo = requestShopOrdrDetailLst.get(i);
        	shopOrdrDetailPo.setJrnNo(JrnGenerator.genJrnNo());
            shopOrdrDetailPo.setOrdNo(ordNo);
            // 获取批发规格的单价
            for (int j=0; j<whslePriceLst.size(); j++){
            	if (shopOrdrDetailPo.getBarCode().equals(whslePriceLst.get(j).getBarCode())){
            		shopOrdrDetailPo.setPrice(whslePriceLst.get(j).getWhslePrice());
            	}
            }
            // 获取批发规格的重量
            for (int j = 0; j < productlst.size(); j++) {
                if (shopOrdrDetailPo.getBarCode().trim().equals(productlst.get(j).getBarCode().trim())) {
                    shopOrdrDetailPo.setSpec(productlst.get(j).getPosSpec());//这个单位需要特别注意(因为不管入库还是展示给门店或者仓库拣货都是最小单位)
                    shopOrdrDetailPo.setUnit(productlst.get(j).getPosUnit());
                    shopOrdrDetailPo.setWght(productlst.get(j).getPosWght().toString());
                    shopOrdrDetailPo.setCnt(productlst.get(j).getWhsleCnt().toString());//基数则需要取对应的数值
                    shopOrdrDetailPo.setTax(productlst.get(j).getTaxes().toString());
                }
            }
            // 默认订单审核数为下单数
            shopOrdrDetailPo.setRevwCnt(shopOrdrDetailPo.getOrdCnt());
            
            // 每种商品总价计算=(商品最小单位数ordCnt)*规格单价
            BigDecimal totPice = new BigDecimal(shopOrdrDetailPo.getPrice());
            totPice = totPice.multiply(new BigDecimal(shopOrdrDetailPo.getOrdCnt()));
            ordrTotAmt = ordrTotAmt.add(totPice);
            shopOrdrDetailPo.setTotPice(String.valueOf(totPice));
            // 总重=(商品最小单位数ordCnt)*规格重量
            shopOrdrDetailPo.setTotWght(AmonutCalcuUtil.mul(shopOrdrDetailPo.getWght(), shopOrdrDetailPo.getOrdCnt()));
            shopOrdrDetailPo.setSurplusCnt(shopOrdrDetailPo.getOrdCnt());
            shopOrdrDetailPo.setCreDt(ordDt);
            shopOrdrDetailPo.setCreTm(ordTm);
            shopOrdrDetailLst.add(shopOrdrDetailPo);
        }
        shopOrdrPo.setSkuCnt(String.valueOf(shopOrdrDetailLst.size()));
        shopOrdrPo.setOrdAmt(String.valueOf(ordrTotAmt));
        shopOrdrPo.setTaxAmt(String.valueOf(ordrTotAmt)); //税金额暂且等于订单总金额
        shopOrdrPo.setUsrNo(request.getUsrNo());
        
        // 订单添加
        if (shopOrdrApplyBo.addShopOrdr(shopOrdrPo)) {
        	// 订单明细添加
            if (shopOrdrApplyBo.addShopOrdrDetail(shopOrdrDetailLst)) {
            } else{
                throw new HiBusinessReturnException(HiMsgCdConstants.ADD_SHOPORDR_DETAIL_FAIL, "增加门店订单明细失败!");
            }
        } else {
            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_SHOPORDR_FAIL, "增加门店订单失败!");
        }
        shopOrdrMatch(shopOrdrPo);
        ShopOrdrApplyResponseDto response = new ShopOrdrApplyResponseDto();
        return response;
	}

	@Override
    @Transactional(rollbackFor = { HiBusinessAbortException.class, HiBusinessReturnException.class })
    public boolean shopOrdrMatch(ShopOrdrPo shopOrdrPo) throws HiBusinessAbortException,
            HiBusinessReturnException {
        //执行流程定义
        WorkFlowCmmParamPo workFlowCmmParamPo = new WorkFlowCmmParamPo();
        switch (shopOrdrPo.getOrdCls()) {
        case Constants.FRESH:
            workFlowCmmParamPo.setFlowNo(Constants.FLOW_ADDSHOPFRESHORDR_PROCESS);
            workFlowCmmParamPo.setActionNo("FLOW_ADDSHOPFRESHORDR_ACTION");
            break;
        case Constants.BFOOD:
            workFlowCmmParamPo.setFlowNo(Constants.FLOW_ADDSHOPFOODORDR_PROCESS);
            workFlowCmmParamPo.setActionNo("FLOW_ADDSHOFOODORDR_ACTION");
            break;
        case Constants.LFOOD:
            workFlowCmmParamPo.setFlowNo(Constants.FLOW_ADDSHOPFOODORDR_PROCESS);
            workFlowCmmParamPo.setActionNo("FLOW_ADDSHOFOODORDR_ACTION");
            break;
        case Constants.MATER:
            workFlowCmmParamPo.setFlowNo(Constants.FLOW_ADDSHOPMATERORDR_PROCESS);
            workFlowCmmParamPo.setActionNo("FLOW_ADDSHOMATERORDR_ACTION");
            break;
        default:
            workFlowCmmParamPo.setFlowNo(Constants.FLOW_ADDSHOPNORMALORDR_PROCESS);
            workFlowCmmParamPo.setActionNo("FLOW_ADDSHOPNORMALORDR_ACTION");
            break;
        }
        workFlowCmmParamPo.setNodeNo("Activiti1");
        workFlowCmmParamPo.setTargetTyp("SHOPORDR");
        workFlowCmmParamPo.setLunchUsrNo(shopOrdrPo.getUsrNo());
        workFlowCmmParamPo.setRecvUsrNo(shopOrdrPo.getUsrNo());
        workFlowCmmParamPo.setTargetId(shopOrdrPo.getOrdNo());
        workFlowCmmParamPo.setTitle("新增门店订单");
        workFlowCmmParamPo.setContent("创建订单, 订单号："+shopOrdrPo.getOrdNo()+", 门店名称："+shopOrdrPo.getShopNm());
        workFlowCmmParamPo.setType(Constants.MSG_TYP_FLOW);
        workFlowCmmParamPo.setLinkUrl(SystemPropertyConfigure.getParams("host.host_addr")+"resources/html/shop/bills/replenishform.html");
        workFlowCmmService.workFlowStart(workFlowCmmParamPo);
        return true;
    }
	
	@Override
	public QryShopOrdrInfoResponseDto qryShopOrdrInfo(QryShopOrdrInfoRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		QryShopOrdrInfoResponseDto response = new QryShopOrdrInfoResponseDto();
		ShopOrdrPo shopOrdrPo = shopOrdrApplyBo.qryShopOrdInfoByOrdNo(request.getOrdNo());
		try {
            response = BaseDto.entity2Trans(shopOrdrPo, QryShopOrdrInfoResponseDto.class);
        } catch (HiException e) {
            e.printStackTrace();
        }
		return response;
	}
	
	@Override
	@Transactional(readOnly=true)
	public QryShopOrdrDetailInfoResponseDto qryShopOrdrDetailInfo(QryShopOrdrDetailInfoRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		// 订单号
		String ordNo = request.getOrdNo();
        // 首先查询共有多少记录
        int totalCount = shopOrdrApplyBo.qryShopOrdrDetailCountByOrdNo(ordNo);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());

        // 分页查询
        List<ShopOrdrDetailPo> shopOrdrDetailPoLst = shopOrdrApplyBo.qryShopOrdrDetailLstByOrdNo(ordNo,
                PageUtil.getStartRow(), PageUtil.getOffset());
        // 组装响应信息
        QryShopOrdrDetailInfoResponseDto response = new QryShopOrdrDetailInfoResponseDto();
        response.setRows(shopOrdrDetailPoLst);
        response.setTotal(totalCount);
        return response;
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessAbortException.class, HiBusinessReturnException.class })
	public ReviewShopOrdrResponseDto reviewShopOrdr(ReviewShopOrdrRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		FlowOrdPo flowOrdPo = flowBo.queryFlowOrd(request.getFlowOrd());
        FlowInfoPo flowInfoPo = flowBo.queryFlowInfo(flowOrdPo.getFlowNo());

        // 审核权限判断
        if (!flowOrdPo.getRecevUsrNo().equals(request.getUsrNo())) {
            throw new HiBusinessReturnException(HiMsgCdConstants.UN_OPER_AUTH, "不具备此次审核权限");
        }
        
        // 订单状态判断
        ShopOrdrPo shopOrdrPo = shopOrdrApplyBo.qryShopOrdInfo(flowOrdPo.getOrdNo());
        if(!"W".equals(shopOrdrPo.getOrdSts())){
            throw new HiBusinessReturnException(HiMsgCdConstants.NOTUPD_ORD_STS_FAIL, "非可处理订单状态");
        }
        
        // 组装流程
        WorkFlowCmmParamPo workFlowCmmParamPo = new WorkFlowCmmParamPo();
        workFlowCmmParamPo.setTitle("门店订单审核");
        workFlowCmmParamPo.setContent("审核通过, 订单号："+shopOrdrPo.getOrdNo()+", 门店名称："+shopOrdrPo.getShopNm());
        workFlowCmmParamPo.setInitiator(shopOrdrPo.getShopNm());

        // 添加审核通过情况下
        String shopOrdrSts = null;
        if ("ADD".equals(flowInfoPo.getFlowTyp())) {
            shopOrdrSts = "V";
        }
        
        // 只有为最后一个节点才更改为对应状态
        if (flowInfoPo.getEndNode().equals(flowOrdPo.getDownStep())) {
            // 页面审核且通过情况下
            if("PASS".equals(request.getReviewType())){
                shopOrdrApplyBo.updShopOrdrSts(shopOrdrSts, flowOrdPo.getOrdNo());
            }
        }
        
        workFlowCmmParamPo.setFlowOrdNo(flowOrdPo.getFlowOrd());
        workFlowCmmParamPo.setActionNo(flowOrdPo.getFlowAction());
        workFlowCmmParamPo.setNodeNo(flowOrdPo.getDownStep());
        workFlowCmmParamPo.setTargetId(flowOrdPo.getOrdNo());
        workFlowCmmParamPo.setTargetTyp("SHOPORDR");
        workFlowCmmParamPo.setFlowJrn(request.getFlowJrn());
        workFlowCmmParamPo.setLunchUsrNo(request.getUsrNo());
        workFlowCmmParamPo.setLinkUrl(SystemPropertyConfigure.getParams(Constants.SYS_ENV_HOST_ADDR)+"shop/bills/replenishform.html");
        if(flowInfoPo.getEndNode().equals(flowOrdPo.getDownStep())){
            workFlowCmmService.workFlowEnd(workFlowCmmParamPo);
        }else{
            workFlowCmmService.workFlowOnIng(workFlowCmmParamPo);
        }
        ReviewShopOrdrResponseDto response = new ReviewShopOrdrResponseDto();
        return response;
	}

	@Override
	public ShopOrdrCombinResponseDto shopOrdrCombin(ShopOrdrCombinRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		/**
         * 1、循环所有门店订单号
         * 2、如果存在不是同一个仓库的订单则报错
         * 3、统计所有订单号并根据商品国际码分组，累计需求量
         * 4、返回给前端展示
         */
        List<ShopOrdrPo> shopOrdrPoLst = shopOrdrApplyBo.qryShopOrdInfoLstByOrdrNoLst(request.getShopOrdNoLst());
        String stohNo = null; // 仓库号
        for (int i = 0; i < shopOrdrPoLst.size(); i++) {
        	if (0 == i) {
        		stohNo = shopOrdrPoLst.get(i).getStohNo();
        	} else {
        		if(!stohNo.equals(shopOrdrPoLst.get(i).getStohNo())){
        			throw new HiBusinessReturnException(HiMsgCdConstants.NOT_RQSTOH_FAIL, "非同一供货仓库无法合并订单");
        		}
        	}
        }
        
        // 查询仓库信息
        StoHosePo stoHosePo = stoHoseMngBo.qryStoHoseInfoByStohNo(stohNo);
        if (null == stoHosePo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.GET_STOHOSEINFO_FAIL, "查询仓库信息失败");
        }
        
        LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("ordNoLst", request.getShopOrdNoLst());
        paramMap.put("stohNo", stohNo);
        paramMap.put("prov", stoHosePo.getProv());
        paramMap.put("city", stoHosePo.getCity());
        
        // 删除对应的空Key为后面的DaoImpl参数赋值提供处理
        Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            Object key = entry.getKey();
            if (null == paramMap.get(key) || "".equals(paramMap.get(key))) {
                iter.remove();
            }
        }
        
        //查询订单合并信息
        List<ShopOrdrCombinPo> shopOrdrCombinPoLst = shopOrdrApplyBo.shopOrdrCombin(paramMap);
        
        ShopOrdrCombinResponseDto response = new ShopOrdrCombinResponseDto();
        response.setTotal(shopOrdrCombinPoLst.size());
        response.setRows(shopOrdrCombinPoLst);
        return response;
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessAbortException.class, HiBusinessReturnException.class })
	public ShopOrdrCombinSubmitResponseDto shopOrdrCombinSubmit(ShopOrdrCombinSubmitRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
        // 生成仓库订单头部标识
        String ordNo = Constants.MDCG+ JrnGenerator.genJrnNo();
        String ordDt = DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd);
        String ordTm = DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss);
        String expDt = DateUtil.calculateDate(ordDt, DateUtil.DAY, Constants.SHOP_ORD_EXP_DAY, DateUtil.dataFormatyyyy_MM_dd);
        
        // 查询仓库信息
        StoHosePo stoHosePo = stoHoseMngBo.qryStoHoseInfoByStohNo(request.getStohNo());
        if(null == stoHosePo){
            throw new HiBusinessReturnException(HiMsgCdConstants.GET_STOHOSEINFO_FAIL, "查询仓库信息失败 ");
        }
        
        //创建订单对象
        StoHoseOrdrPo stoHoseOrdrPo = new StoHoseOrdrPo();
        stoHoseOrdrPo.setOrdNo(ordNo);
        stoHoseOrdrPo.setOrdCnl(request.getChannelTyp());
        stoHoseOrdrPo.setOrdTyp(request.getOrdTyp());
        stoHoseOrdrPo.setOrdCls("NORMAL");
        stoHoseOrdrPo.setStohNo(stoHosePo.getStohNo());
        stoHoseOrdrPo.setStohNm(stoHosePo.getStohNm());
        stoHoseOrdrPo.setProv(stoHosePo.getProv());
        stoHoseOrdrPo.setCity(stoHosePo.getCity());
        stoHoseOrdrPo.setDistrict(stoHosePo.getDistrict());
        stoHoseOrdrPo.setIsLdist("N");//默认不需要直配门店
        stoHoseOrdrPo.setOrdSts("V");//采购自己采购的默认就是审核成功
        stoHoseOrdrPo.setOrdDt(ordDt);
        stoHoseOrdrPo.setOrdTm(ordTm);
        stoHoseOrdrPo.setEffDay(""+Constants.SHOP_ORD_EXP_DAY);
        stoHoseOrdrPo.setExpDt(expDt);
        stoHoseOrdrPo.setExpTm(ordTm);
        
        BigDecimal ordAmt = new BigDecimal(0);
        
        //查询订单商品定价
        List<String> barCodeLst = new ArrayList<String>();
        List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst = request.getStoHoseOrdrDetailPoLst();
        for(int i=0; i<stoHoseOrdrDetailPoLst.size(); i++){
            barCodeLst.add(stoHoseOrdrDetailPoLst.get(i).getBarCode());
        }
        
        // 查询所有商品采购价
        List<SuppliPricePo> suppliPriceLst = productPriceBo.qrySuppliPriceByCodeLst(barCodeLst);
        HiBizLogger.info("查询省份城市商品供应商价格,应该需要获取的商品价格列表数：" + barCodeLst.size() + ",实际获取到的列表数：" + suppliPriceLst.size());
        if (null == suppliPriceLst || 0 == suppliPriceLst.size()) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_SUPPLI_PRICE_FAIL, "查询供应商商品定价失败");
        }
        
        // 查询所有商品信息
        List<ProductPo> productlst = iProductBo.qryProductsByCodeLst(barCodeLst);
        
        // 构造仓库订单明细对象列表
        List<StoHoseOrdrDetailPo> newStoHoseOrdrDetailPoLst = new ArrayList<StoHoseOrdrDetailPo>();
        for(int i=0; i<stoHoseOrdrDetailPoLst.size(); i++){
        	StoHoseOrdrDetailPo stoHoseOrdrDetailPo = stoHoseOrdrDetailPoLst.get(i);
            // 为0的跳过
            if(new BigDecimal(stoHoseOrdrDetailPo.getOrdCnt()).compareTo(new BigDecimal(0))<=0){
                continue;
            }
            
            stoHoseOrdrDetailPo.setJrnNo(JrnGenerator.genJrnNo());
            stoHoseOrdrDetailPo.setOrdNo(ordNo);
            
            for (int j = 0; j < suppliPriceLst.size(); j++) {
                //必须国际码和供应商号一一相等才能赋予价格,因为下单的时候供应商是有多个的
                if (stoHoseOrdrDetailPo.getBarCode().equals(suppliPriceLst.get(j).getBarCode())) {
                    stoHoseOrdrDetailPo.setPrice(suppliPriceLst.get(j).getSuppliPrice());
                    stoHoseOrdrDetailPo.setTax(suppliPriceLst.get(j).getSuppliTax());
                    stoHoseOrdrDetailPo.setSuppliNo(suppliPriceLst.get(j).getSuppliNo());//如果此处没值会造成合并采购出错
                    stoHoseOrdrDetailPo.setSuppliNm(suppliPriceLst.get(j).getSuppliNm());//如果此处没值会造成合并采购出错
                    break;
                }
            }
            
            //获取采购规格的重量(注意：存入数据库要用最小售卖单位，页面展示用对应单位)
            for (int j = 0; j < productlst.size(); j++) {
                if(stoHoseOrdrDetailPo.getBarCode().equals(productlst.get(j).getBarCode())){
                    stoHoseOrdrDetailPo.setPrdNm(productlst.get(j).getPrdNm());
                    stoHoseOrdrDetailPo.setWght(productlst.get(j).getPosWght().toString());
                    stoHoseOrdrDetailPo.setSpec(productlst.get(j).getPosSpec());
                    stoHoseOrdrDetailPo.setUnit(productlst.get(j).getPosUnit());
                    stoHoseOrdrDetailPo.setCnt(productlst.get(j).getSourcCnt().toString());
                }
            }
            //总价计算，总价计算的时候需要转换操作不能已ordCnt去计算，ordCnt存的是最小售卖单位，如：瓶
            BigDecimal totPice = new BigDecimal(stoHoseOrdrDetailPo.getPrice());
            //总价=(下单最小售卖单位数)*规格单价
            totPice = totPice.multiply(new BigDecimal(stoHoseOrdrDetailPo.getOrdCnt()));
            ordAmt = ordAmt.add(totPice);
            
            stoHoseOrdrDetailPo.setTotPrice(String.valueOf(totPice));
            stoHoseOrdrDetailPo.setIsLdist("N");//是否可直配
            //总重=(下单最小售卖单位数)*规格重量
            stoHoseOrdrDetailPo.setTotWght(new BigDecimal(stoHoseOrdrDetailPo.getWght()).multiply(new BigDecimal(stoHoseOrdrDetailPo.getOrdCnt())).toString());
            stoHoseOrdrDetailPo.setRevwCnt(stoHoseOrdrDetailPo.getOrdCnt());
            stoHoseOrdrDetailPo.setSurplusCnt(stoHoseOrdrDetailPo.getOrdCnt());
            stoHoseOrdrDetailPo.setCreDt(ordDt);
            stoHoseOrdrDetailPo.setCreTm(ordTm);
            if(new BigDecimal(stoHoseOrdrDetailPo.getOrdCnt()).compareTo(new BigDecimal(0))>0){
            	newStoHoseOrdrDetailPoLst.add(stoHoseOrdrDetailPo);
            }
        }
        
        stoHoseOrdrPo.setSkuCnt(String.valueOf(newStoHoseOrdrDetailPoLst.size()));
        stoHoseOrdrPo.setOrdAmt(String.valueOf(ordAmt));
        stoHoseOrdrPo.setTaxAmt("0.00");
        stoHoseOrdrPo.setUsrNo(request.getUsrNo());
        // 新增仓库订单前提订单明细大于0
        if(0 < newStoHoseOrdrDetailPoLst.size()){
            //订单添加
            if(stoHoseMngBo.addStoHoseOrdr(stoHoseOrdrPo)){
                //订单明细添加
                if(!stoHoseMngBo.addStoHoseOrdrDetail(newStoHoseOrdrDetailPoLst)){
                    throw new HiBusinessReturnException(HiMsgCdConstants.ADD_STOHORDR_DETAIL_FAIL, "增加仓库订单明细失败");
                }
            }else{
                throw new HiBusinessReturnException(HiMsgCdConstants.ADD_STOHORDR_FAIL, "增加仓库订单失败");
            }
        }
        ShopOrdrCombinSubmitResponseDto response = new ShopOrdrCombinSubmitResponseDto();
        return response;
	}

	
	
	
	

}
