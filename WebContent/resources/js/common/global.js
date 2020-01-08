global={
    httpPath:"http://www.young.com:8088",//本机服务器路径
    socketPath:location.protocol == "https" ? "wss://www.young.com:8088" : "ws://www.young.com:8088",//本机socket路径
    //imgDownPath:"https://uat.justsend.cn/imgdownload/",//本机图片下载路径
    youngApp:"/young",//开发环境主webApp目录
    httpYoungPath:"http://www.young.com:8088/young/",
    globalVersion:"1.0.1"
};

/**
 * 构建公共请求头
 * @return {Number}
*/
var requestObj = new Object();
requestObj.appVersion="1.0.1";
requestObj.termTyp="WEB";
requestObj.requestTm=""+new Date().getFullYear()+(new Date().getMonth()+1)+new Date().getDate()+new Date().getHours()+new Date().getMinutes()+new Date().getSeconds();
requestObj.channelId="WEB";
requestObj.channelTyp="WEB"

/**
 * 设置JS中各模块的DOMAIN
 */
var YOUNG_DOMAIN = global.httpPath+global.youngApp;
var YOUNG_SOCKET = global.socketPath+global.youngApp;
