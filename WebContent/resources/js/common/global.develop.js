global={
    httpPath:"http://localhost:8080",//本机服务器路径
    socketPath:"ws://localhost:8080",//本机socket路径
    //imgDownPath:"https://uat.justsend.cn/imgdownload/",//本机图片下载路径
    youngApp:"/young",//开发环境主webApp目录
    httpYoungPath:"http://localhost:8080/young/",
    /*prdApp:"/prdapp",//开发环境主webApp目录
    sysApp:"/sysapp",//开发环境主webApp目录
    httpMngPath:"http://localhost:8080/mngapp/",
    httpPrdPath:"http://localhost:8080/prdapp/",
    httpSysPath:"http://localhost:8080/sysapp/",
    socketMngPath:"ws://localhost:8080/mngapp/",
    socketPrdPath:"ws://localhost:8080/prdapp/",
    socketSysPath:"ws://localhost:8080/sysapp/",*/
    globalVersion:"1.0.0.1"
};

/**
 * 公共请求头
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
/*var PRD_DOMAIN = global.httpPath+global.prdApp;
var SYS_DOMAIN = global.httpPath+global.sysApp;
var MNG_SOCKET = global.socketPath+global.mngApp;
var PRD_SOCKET = global.socketPath+global.prdApp;
var SYS_SOCKET = global.socketPath+global.sysApp;*/
