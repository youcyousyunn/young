$(function(){
    requestObj.pageSize="10";
    requestObj.currentPage=0;
    initMsglst(requestObj);
    initAnnounceLst(requestObj);
    //点击加载更多
    $('#addmore_btn').on('click', function(){
        requestObj.currentPage=requestObj.currentPage+1;
        initMsglst(requestObj);
    });
    
    //点击刷新
    $('#reloadBtn').on('click', function(){
        $('#msgLst').empty();
        requestObj.pageSize="10";
        requestObj.currentPage=0;
        initMsglst(requestObj);
    });
    
    //发送公告
    $('#noticeBtn').on('click', function(){
    	$.get(YOUNG_DOMAIN + "/resources/html/msg/publish-notice.html", function(data) {
            var l = layer.open({
                id : "edit-role-dialog",
                type : 1,
                title : "发布公告",
                content : data,
                area : ["40%", "auto"],
                maxmin: true,
                closeBtn : 1,
                scrollbar : false,
                fix : true,
                move : false,
                shift : 5,
                btn : [ "确定", "取消" ],
                success : function() {
                	initPublishDialog();
                },
                yes : function(index) {
                    /**
                     * 表单验证规则
                     */
                	var $publishNoticeForm = $("#publish-notice-form");
                    $publishNoticeForm.validate({
                        rules : {
                        	title : "required",
                            content : "required"
                        },
                        messages : {
                        	title : "请输入公告标题",
                        	content : "请输入公告内容"
                        }
                    });
                    if (!$publishNoticeForm.valid()) {
                        return;
                    }
                    var $publishNoticeInfo = $publishNoticeForm.serializeJSON();
                    $.postJson(YOUNG_DOMAIN + "/work/v1/notify.do", $publishNoticeInfo, function(data) {
                    	if (data.rspCd == "00000") {
                            toastr.success("发布成功");
                            $('#announceLst').empty();
                            initAnnounceLst(requestObj); // 刷新公告列表
                            layer.close(index);
                        } else {
                            toastr.error(data.rspInf, "发布失败");
                        }
                    });
                }
            });
        });
    });
});

//点击加载更多(公告)
$('#loadmore_btn').on('click', function(){
    requestObj.currentPage=requestObj.currentPage+1;
    initAnnounceLst(requestObj);
});

function initPublishDialog(){
	var $publishNoticeForm = $("#publish-notice-form");
	$publishNoticeForm.find("#usrNm").val(window.localStorage.getItem("userName"));
}


/**
 * 初始化消息列表
 */
function initMsglst(requestObj){
    //加载首页默认未读消息数据 
	requestObj.msgTyp = "REMIND";
    $.postJson(YOUNG_DOMAIN + "/work/v1/qryMsgNotify.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            for (var i = 0; i < data.rows.length; i++) {
                var timeH5='datetime="'+data.rows[i].creDt+'T'+data.rows[i].creTm+'+0800"';
                
                var linkUrl = null===data.rows[i].linkUrl?'#':data.rows[i].linkUrl;
                var extJson = JSON.parse(data.rows[i].extJson);
                var classSrc = data.rows[i].isRead==="N"?"form-control list-group-item unread":"form-control list-group-item";
                //构建消息内容
                var eachContent = data.rows[i].msgContent;
            	var index = eachContent.lastIndexOf(',');
            	var content =  eachContent.substring(0, index);
                var htmldiv=
                    '<div class="input-group" style="margin: 2px 2px;">'+
                        '<a href="javascript:void(0);" onClick="openTab(this)" data-msgid="'+data.rows[i].msgId+'" data-dispno="'+extJson.dispNo+'" data-trackno="'+extJson.trackNo+'" data-desc="'+data.rows[i].msgTitle+'" data-floword="'+extJson.flowOrd+'" data-flowjrn="'+extJson.flowJrn+'" data-ordno="'+extJson.ordNo+'" data-actiontyp="'+data.rows[i].actionTyp+'" data-linkurl="'+linkUrl+'" class="'+classSrc+'" style="height: 100px;">'+
                            '<h3 class="list-group-item-heading">'+data.rows[i].msgTitle+'</h3>'+
                            '<p class="list-group-item-text">消息内容：'+data.rows[i].lunchUsrNm+content+'</p>'+
                            '<small class="text-muted">日期 '+data.rows[i].creDt+'</small>'+
                            '<time name="ctime" class="pull-right" '+timeH5+'></time>'+
                        '</a> '+
                        '<span class="input-group-btn"> '+
                            '<button id="heartBtn" onClick="heartMsg(this)" data-msgid="'+data.rows[i].msgId+'" type="button" class="btn" style="height: 100px;"><i class="fa fa-heart"></i></button>'+
                        '</span>'+
                    '</div>';
                $('#msgLst').append(htmldiv);
            }
            $('time[name="ctime"]').timeago();
        } else {
            layer.msg("获取消息失败：" + data.rspInf + "," + data.rspCd);
        }
    });
}

/**
 * 初始化公告消息列表
 */
function initAnnounceLst(requestObj){
	requestObj.msgTyp = "ANNOUNCE";
    $.postJson(YOUNG_DOMAIN + "/work/v1/qryNotify.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            for (var i = 0; i < data.rows.length; i++) {
                var timeH5='datetime="'+data.rows[i].creDt+'T'+data.rows[i].creTm+'+0800"';
                
                var linkUrl = null===data.rows[i].linkUrl?'#':data.rows[i].linkUrl;
                var extJson = JSON.parse(data.rows[i].extJson);
                var classSrc = data.rows[i].isRead==="N"?"form-control list-group-item unread":"form-control list-group-item";
                //构建消息内容
                var eachContent = data.rows[i].msgContent;
            	var index = eachContent.lastIndexOf(',');
            	var content =  eachContent.substring(0, index);
                var htmldiv=
                    '<div class="input-group" style="margin: 2px 2px;">'+
                            '<h3 class="list-group-item-heading">'+data.rows[i].msgTitle+'</h3>'+
                            '<p class="list-group-item-text">公告内容：'+data.rows[i].msgContent+'</p>'+
                            '<small class="text-muted">日期 '+data.rows[i].creDt+'</small>'+
                            '<time name="ctime" class="pull-right" '+timeH5+'></time>'+
                        '</a> '
                    '</div>';
                $('#announceLst').append(htmldiv);
            }
            $('time[name="ctime"]').timeago();
        } else {
            layer.msg("获取公告消息失败：" + data.rspInf + "," + data.rspCd);
        }
    });
}

function openWarn($msgId, $linkUrl){
    $.get($linkUrl, function(html) {
        layer.open({
            id : "warn-dialog",
            type : 1,
            title : "告警消息详情",
            content : html,
            area : [ "60%", "80%" ],
            scrollbar : false,
            skin: 'layui-layer-rim',
            shadeClose: false,
            btn : [ "确定", "取消" ],
            success : function(){
                $.postJson(YOUNG_DOMAIN + "/work/v1/qryMsgInfo.do", {msgId:$msgId}, function (data) {
                    $('#msgTitle').html(data.msgTitle);
                    $('#msgContent').html(data.msgContent);
                    var msgDetail = JSON.parse(data.extJson);
                    for (var int = 0; int < msgDetail.msgData.length; int++) {
                        var detailHtml = '<tr><td>'+msgDetail.msgData[int].msgDataId+'</td><td>'+msgDetail.msgData[int].msgDataTxt+'</td><td>'+msgDetail.msgData[int].msgDataUm+'</td></tr>';
                        $('#detail').append(detailHtml);
                    }
                });
            },
            yes : function(index) {
                layer.close(index);
            }
        });
    });
}


function openRemind($msgId, $linkUrl){
    $.get($linkUrl, function(html) {
        layer.open({
            id : "remind-dialog",
            type : 1,
            title : "提示消息详情",
            content : html,
            area : [ "60%", "80%" ],
            scrollbar : false,
            skin: 'layui-layer-rim',
            shadeClose: false,
            btn : [ "确定", "取消" ],
            success : function(){
                $.postJson(YOUNG_DOMAIN + "/work/v1/qryMsgInfo.do", {msgId:$msgId}, function (data) {
                    $('#msgTitle').html(data.msgTitle);
                    $('#msgContent').html(data.msgContent);
                    var msgDetail = JSON.parse(data.extJson);
                    for (var int = 0; int < msgDetail.msgData.length; int++) {
                        var detailHtml = '<tr><td>'+msgDetail.msgData[int].msgDataId+'</td><td>'+msgDetail.msgData[int].msgDataTxt+'</td><td>'+msgDetail.msgData[int].msgDataUm+'</td></tr>';
                        $('#detail').append(detailHtml);
                    }
                });
            },
            yes : function(index) {
                layer.close(index);
            }
        });
    });
}

function openOrdrMsg($msgId, $linkUrl){
    $.get($linkUrl, function(html) {
        layer.open({
            id : "OrdrMsg-dialog",
            type : 1,
            title : "提示消息详情",
            content : html,
            area : [ "60%", "80%" ],
            scrollbar : false,
            skin: 'layui-layer-rim',
            shadeClose: false,
            btn : [ "确定", "取消" ],
            success : function() {
                $.postJson(YOUNG_DOMAIN + "/work/v1/qryMsgInfo.do", {
                    msgId : $msgId
                }, function(data) {
                    $('#msgTitle').html(data.msgTitle);
                    $('#msgContent').html(data.msgContent);
                    var msgDetail = JSON.parse(data.extJson);
                    for (var int = 0; int < msgDetail.msgData.length; int++) {
                        var detailHtml = '<tr><td>'
                                + msgDetail.msgData[int].msgDataId
                                + '</td><td>' + msgDetail.msgData[int].barCode
                                + '</td><td>' + msgDetail.msgData[int].prdNm
                                + '</td><td>' + msgDetail.msgData[int].whlesSpec
                                + '</td><td>' + msgDetail.msgData[int].unit
                                + '</td><td>' + msgDetail.msgData[int].ordCnt
                                + '</td><td>' + msgDetail.msgData[int].dbCnt
                                + '</td><td>' + msgDetail.msgData[int].revwCnt
                                + '</td><td>' + msgDetail.msgData[int].msgDataUm
                                + '</td></tr>';
                        $('#detail').append(detailHtml);
                    }
                });
            },
            yes : function(index) {
                layer.close(index);
            }
        });
    });
}

/**
 * 打开消息
 */
function openTab(object){
    var $msgId = $(object).data("msgid");
    //标记消息为已读
    $.postJson(YOUNG_DOMAIN + "/work/v1/isReadMsg.do", {msgId:$msgId, isRead:"Y"}, function(data) {
        console.log(data);
        $(object).removeClass("unread");
    });
    
    var $linkUrl = $(object).data("linkurl");
    var $msgId = $(object).data("msgid");
    var $ordNo = $(object).data("ordno");
    var $flowJrn = $(object).data("flowjrn");
    var $flowOrd = $(object).data("floword");
    var $actionTyp = $(object).data("actiontyp");
    var $desc = $(object).data("desc");
    var $trackNo = $(object).data("trackno");
    var $dispNo = $(object).data("dispno");
    
    var tabObj = new Object();
    switch($actionTyp){
    	case "COMPAN":
    		tabObj.desc = "运营公司";
            tabObj.dataId = $linkUrl+'?cotldNo='+$ordNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
    		break;
    	case "STOHOSE":
    		tabObj.desc = "仓库";
            tabObj.dataId = $linkUrl+'?stohNo='+$ordNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
        	break;
    	case "SHOP":
    		tabObj.desc = "门店";
            tabObj.dataId = $linkUrl+'?shopNo='+$ordNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
        	break;
    	case "PIRCE":
    		tabObj.desc = "价格";
            tabObj.dataId = $linkUrl+'?batNo='+$ordNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
        	break;
    	case "RECVSHOP":
    		tabObj.desc = "门店订单收货";
            tabObj.dataId = $linkUrl+'?ordNo='+$ordNo+'&trackNo='+$trackNo+'&dispNo='+$dispNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
        	break;
    	case "SHOPEXCP":
    		tabObj.desc = "门店异常订单";
            tabObj.dataId = $linkUrl+'?ordNo='+$ordNo+'&trackNo='+$trackNo+'&dispNo='+$dispNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
        	break;
    	default:
    		tabObj.desc = $desc;
        	tabObj.dataId = $linkUrl+'?ordNo='+$ordNo+'&flowJrn='+$flowJrn+'&flowOrd='+$flowOrd;
    		break;
    }
    parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
}

/**
 * 标记红心
 */
function heartMsg(object){
	 var requestObj = {
		 msgId : $(object).data("msgid"),
		 usrNo : $(object).data("usrno")
	};
	$.postJson(YOUNG_DOMAIN + "/work/v1/starMsgNotify.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
        	
        
        }
	});
}
