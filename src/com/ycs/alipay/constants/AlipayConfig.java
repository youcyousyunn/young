package com.ycs.alipay.constants;

public class AlipayConfig {
		// 商户appid
		public static String app_id = "2016091500516448";
		// 商户私钥(您的PKCS8格式RSA2私钥)
	    public static String merchant_private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhuXgy9tNS3dZ+WDFu1+Eq+V+8b01ChIL2bgt+WzC7VqWjSQXFizh9ig/bnillq80nqT3GopK8XXRyAjXWbZMTpVu8ITEknfSO4NKoxg4iiK87vhp4g/SLWuNMbQ2IceZnw6tlPBOxGKLUOQryY28dtwY6a4jtwXUgoSutDiX+Z9zVvLP9ueyLLu6B5xCtzSTmmfePKoawwus73Okj0CD/LCmWytnDceZ48ivDpwigg2c8FVIL2VH6xb8eYv9ZlaH7ZB7hBfTuClhky1hVswPwPKRZMPDNxy/Ph85IpVWB6WpnQanXfd+cexSO3uSFHbCpEn/5EOPyu6sUH/dQH2tQQIDAQAB";
		// 支付宝公钥
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyw0t7esAA0X/42HaeFjVp3O+2lUclM9H+CDjhJVNsSKIJX07SHYuBKrABQwGKLqMQXUfh7Vpi+TfmzIxhO8WftIt2t5W4RKFIIsIZsiNFDxd65DuNn959jmkM6rjN59R9NeKxg7HuC7z9pCCmdp3H+Qd5R8EkoDJunBPSRaFZ99N6i1NhwSSbJWDc4tXwpR7zWTwggbfKqDEZYzEIpZfJ0Ak1qjEzkyFaK5NE2ouAnEhEc3zjxNXF4gdj9dsNWTEo6u26NetCpRlUcH1G8LpKMTBHob7eMwML55CpmG+1GZ4rD8Ph2mhHvieyRzB4zA1bJ10dsySwglzqRdURr64/QIDAQAB";
		// 服务器异步通知页面路径
		public static String notify_url = "https://github.com/youcyousyunn/basic";
		// 页面跳转同步通知页面路径
		public static String return_url = "https://github.com/youcyousyunn/others";
		// 签名方式
		public static String sign_type = "RSA2";
		// 字符编码格式
		public static String charset = "utf-8";
		// 真实支付宝网关
//		public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
		// 沙箱环境支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; 
		
	
	
}
