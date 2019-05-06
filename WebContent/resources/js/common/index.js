/**
 * 主页JS
 */
$(function() {
    var $indexDiv = $("#wrapper");
    // 初始化用户信息
    initUserInfo();
    
    // 退出登录
    $indexDiv.on("click", ".J_tabExit", function() {
    	window.location.href = "http://www.sso.com:8443/sso/logout.do";
        /*$.postJsonAsync(YOUNG_DOMAIN + "/user/v1/logout.do", {}, function(data) {
            if (data.rspCd == "00000") {
                window.location.href = YOUNG_DOMAIN + "/login.html";
            }
        });*/
    });
    
    // 登陆后显示值
    /*if (window.localStorage) {
        $("strong.font-bold").html(window.localStorage.getItem("userName"));
        $("#index-deptment").html(window.localStorage.getItem("deptment"));
    } else {
        $("strong.font-bold").html(USER_NAME);
        $("#index-deptment").html(DEPTMENT);
    }*/
    
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": true,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "preventDuplicates": true,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
});

// 建立websocket连接
function webSocket(usrNo){
	var ws; //websocket实例
    var lockReconnect = false; //避免重复连接
    var wsUrl = YOUNG_SOCKET+"/ws/ws.do?usrNo="+usrNo;
    
    createWebSocket(wsUrl);
    
    function createWebSocket(url) {
        try {
            ws = new WebSocket(url);
            initEventHandle();
        } catch (e) {
            reconnect(url);
        }     
    }

    function initEventHandle() {
    	ws.onopen = function () {
            console.log("WebSocket:已连接");
            //心跳检测重置
            heartCheck.reset().start();
        };
        ws.onclose = function () {
            console.log("WebSocket:已关闭");
            reconnect(wsUrl);
        };
        ws.onerror = function () {
            console.log("WebSocket:发生错误");
            reconnect(wsUrl);
        };
        ws.onmessage = function (event) {
            console.log("WebSocket:收到一条消息",event.data);
            var audio = document.getElementById("bgMusic");
            //播放(继续播放)
            audio.play();
            toastr.info(event.data);
            //如果获取到消息，心跳检测重置
            //拿到任何消息都说明当前连接是正常的
            heartCheck.reset().start();
        }
    }

    function reconnect(url) {
        if(lockReconnect) return;
        lockReconnect = true;
        // 没连接上会一直重连，设置延迟避免请求过多
        setTimeout(function () {
            createWebSocket(url);
            lockReconnect = false;
        }, 60000);
    }

    
    //心跳检测
    var heartCheck = {
        timeout: 120000,//120秒
        timeoutObj: null,
        serverTimeoutObj: null,
        reset: function(){
            clearTimeout(this.timeoutObj);
            clearTimeout(this.serverTimeoutObj);
            return this;
        },
        start: function(){
            var self = this;
            this.timeoutObj = setTimeout(function(){
                //这里发送一个心跳，后端收到后，返回一个心跳消息，
                //onmessage拿到返回的心跳就说明连接正常
                ws.send("HeartBeat");
                self.serverTimeoutObj = setTimeout(function(){ // 如果超过一定时间还没重置，说明后端主动断开了
                    ws.close(); // 如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
                }, self.timeout)
            }, this.timeout);
        }
    }
}

/**
 * 获取个人信息
 */
function initUserInfo(){
    // 查询当前用户个人信息
    $.postJson(YOUNG_DOMAIN + "/user/v1/userInfo.do", {}, function(data) {
    	if (null != data && data.rspCd == "00000") {
            // 设置登录信息
    		var userInfo = data.userInfo;
    		if(userInfo){
    			window.localStorage.setItem("userName", userInfo.usrNm);
                window.localStorage.setItem("deptment", userInfo.deparNm);
    			$("strong.font-bold").html(window.localStorage.getItem("userName"));
    			$("#index-deptment").html(window.localStorage.getItem("deptment"));
    			
    			webSocket(userInfo.usrNo);
    		}
        }else{
            toastr.error(data.rspInf, "获取用户信息失败！");
        }
    });
}
