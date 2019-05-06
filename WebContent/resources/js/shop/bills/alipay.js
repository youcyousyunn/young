$(function(){
	var $alipayContent = getUrlParam(content);
	document.forms[0].submit();
//	document.write($alipayContent);
//	$("#alipayContent").innerHTML(alipayContent);
    
});

//获取url中的参数
function getUrlParam(content) {
    var reg = new RegExp("(^|&)" + content + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

