package com.ycs.alipay.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ycs.alipay.constants.AlipayConfig;
import com.ycs.alipay.domain.dto.AlipayRequestDto;
import com.ycs.alipay.domain.dto.AlipayResponseDto;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.controller.IBaseController;

/**
 * 支付宝支付Controller
 * @author youcyousyunn
 * @date 2018年5月9日
 */
@Controller
@RequestMapping(value="/alipay")
public class AlipayInfoController extends IBaseController {

	/**
	 * 支付宝订单支付
	 * @param
	 * @return Model
	 */
	@RequestMapping(value="/pay")
	@ResponseBody
	public AlipayResponseDto alipay(@RequestBody AlipayRequestDto request, HttpServletResponse response) throws Exception{
		AlipayResponseDto responseDto = new AlipayResponseDto();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ request.getOrdNo() +"\"," 
				+ "\"total_amount\":\""+ request.getTotAmt() +"\"," 
				+ "\"subject\":\""+ request.getOrdNm() +"\"," 
				+ "\"body\":\""+ request.getOrdDesc() +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//支付请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			responseDto.setRspInf(result);
		} catch (AlipayApiException e) {
			responseDto.setRspCd(e.getErrCode());
			return responseDto;
		}
		responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
		return responseDto;
	}
	
	/**
	 * 支付宝支付成功后同步通知付款方接口
	 * @param
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 */
	@RequestMapping(value="return")
	public String alipayReturn(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException{
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		String result = "";
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			result = "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
		}else {
			result = "验签失败";
		}
		
		return result;
	}
	
	
	/**
	 * 支付宝支付成功后异步通知接口
	 * @param
	 * @return String
	 */
	@RequestMapping(value="/notify")
	public String notify(HttpServletRequest request, HttpServletResponse response){
		 Map<String, String> resultMap = new LinkedHashMap<>();
		 // 获取支付宝回调的request中值
		 Map<String, String[]> requestMap = request.getParameterMap();
		 // 订单号
		 String outTradeNo = request.getParameter("out_trade_no");
		 // 交易状态
		 String tradeStatus = request.getParameter("trade_status");
		 Iterator<String> iterator = requestMap.keySet().iterator();
		 while (iterator.hasNext()){
			 String key = iterator.next();
			 String[] values = requestMap.get(key);
			 String eachValue = "";
			 for (int i=0; i<values.length; i++){
				 if (i == values.length-1){
					 eachValue += values[i];
				 } else{
					 eachValue += values[i] + ",";
				 }
			 }
			 
			 resultMap.put(key, eachValue);
		 }
		 
		 // 签名验证(对支付宝返回的数据验证,确保是支付宝返回的)
		 boolean signVerified = false;
		 
		 try {
			 signVerified = AlipaySignature.rsaCheckV1(resultMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
		 } catch (AlipayApiException e) {
			 e.printStackTrace();
		 }
		
		 // 对验证结果进行处理
		 if (signVerified){
			if ("TRADE_SUCCESS".equals(tradeStatus)){
				////TODO 业务处理，更新个人业务订单支付状态&&
			}
		 } else{
			 ////TODO 返回前端错误消息
		 }
		
		 return null;
	}
	
	
}
