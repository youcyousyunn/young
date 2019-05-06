toastr.options = {
    "closeButton" : true,
    "debug" : false,
    "progressBar" : true,
    "positionClass" : "toast-center-center",
    "onclick" : null,
    "showDuration" : "100",
    "hideDuration" : "1000",
    "timeOut" : "3000",
    "extendedTimeOut" : "1000",
    "showEasing" : "swing",
    "hideEasing" : "linear",
    "showMethod" : "fadeIn",
    "hideMethod" : "fadeOut"
}

// 订单&配送总金额
var ordAmt = 0;

$(function(){
    //进度条
    $("#ordrStsYstep").loadStep({
        size : "large",
        color : "blue",
        steps : [ {
            title : "生成",
        }, {
            title : "审核",
        }, {
            title : "采购",
        }, {
            title : "完成",
        } ]
    });
    
    var $ordNo = getUrlParam("ordNo", "distAmt");
    
    //订单信息展示
//    showOrdrInfo($ordNo);
    
    var requestObj = {
    		ordNo : $ordNo,
    		isShowMsg : 'Y'
    };
    
    pagePost(requestObj);
    
});

/**
 * 获取门店订单明细
 */
function pagePost(requestObj) {
    // 初始化table
    $('#replenish_table').bootstrapTable({
        url : YOUNG_DOMAIN + "/shop/v1/qryShopOrdrDetailInfo.do", // 请求后台的URL（*）
        dataType : "json",
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : true, // 是否显示分页（*）
        sortable : false, // 是否启用排序
        sortName : "jrnNo",
        sortOrder : "desc",
        queryParamsType : "custom",
        queryParams : function(params) {
            requestObj.currentPage = params.pageNumber - 1;
            requestObj.pageSize = params.pageSize;
            return requestObj;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber : 1, // 初始化加载第一页，默认第一页
        pageSize : 10, // 每页的记录行数（*）
        pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
        search : false, // 是否显示表格搜索
        strictSearch : false,
        showColumns : false, // 是否显示所有的列
        showRefresh : false, // 是否显示刷新按钮
        minimumCountColumns : 2, // 最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
        uniqueId : "jrnNo", // 每一行的唯一标识，一般为主键列
        showToggle : false, // 是否显示详细视图和列表视图的切换按钮
        cardView : false, // 是否显示详细视图
        detailView : false, // 是否显示父子表
        responseHandler : handel,
        idField : "jrnNo",
        iconSize : "outline",
        icons : {
            refresh : "glyphicon-repeat",
            toggle : "glyphicon-list-alt",
            columns : "glyphicon-list"
        },
        columns : [
                [ {
                    title : '订单详情',
                    colspan : 10,
                    clickToSelect : false,
                } ],
                [
                    {
                        title : '<p id="ordNoTxt">订单编号：</p><p id="shopNm">门店：</p><p id="ordDt">订单创建时间：</p><p id="expDt">订单关闭时间：</p>',
                        colspan : 7,
                        clickToSelect : false,
                    },
                    {
                        title : '<img class="replenishform_img"  src="../../../../resources/img/noimgs.jpg" />',
                        colspan : 3,
                        width : "13%",
                        align : 'center'
                    } 
                ], 
                [{
                    field : 'index',
                    title : '序号 ',
                    formatter : function(value, row, index) {
                        return index+1;
                    }
                }, {
                    field : "barCode",
                    title : "条码"
                }, {
                    field : "prdNm",
                    title : "品名"
                }, {
                    field : "spec",
                    title : "零售规格",
                }, {
                    field : "unit",
                    title : "零售单位"
                }, {
                    field : "price",
                    title : "批发单价"
                }, {
                    field : "ordCnt",
                    title : "申请订单量"
                }, {
                    field : "revwCnt",
                    title : "审核订单量"
                }, {
                    field : "totpPice",
                    title : "总价",
                    formatter : function(value, row, index) {
                        var sum=row.price*row.revwCnt;
                        ordAmt += sum;
                        $("#ordAmt").val(ordAmt); //订单总金额
                        var distAmt = $("#distAmt").val(); //该门店的订单配送费
                        if (distAmt){
                        	var totAmt = parseFloat(ordAmt)+parseFloat(distAmt);
                        	$("#ordAndDistAmt").val(totAmt);
                        	$('#ordrSkuInfo').html("订单总金额(元)：" + totAmt.toFixed(2) + " (含配送费(元)："+distAmt+")"); //订单含配送费总金额
                        } else{
                        	$("#ordAndDistAmt").val(ordAmt);
                        	$('#ordrSkuInfo').html("订单总金额：" + ordAmt.toFixed(2) + " (含配送费："+distAmt+")"); //订单含配送费总金额
                        }
                        return sum.toFixed(2);
                    }
                }
            ] 
        ],
        silent : true, //刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { //没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadSuccess : function(data){
        	var $ordNo = requestObj.ordNo;
        	showOrdrInfo($ordNo);
        },
        onLoadError : function(data) {
            $('#replenish_table').bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}
function handel(res){
    return res;
}

/**
 * 获取门店订单信息
 */
function showOrdrInfo($ordNo){
    requestObj.ordNo = $ordNo;
    requestObj.isShowMsg = 'Y';
    $.postJson(YOUNG_DOMAIN + "/shop/v1/qryShopOrdrInfo.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            $('#ordNoTxt').html('订单编号：'+data.ordNo);
            $('#ordNo').val(data.ordNo);
            $('#shopNm').html('门店名称：'+data.shopNm);
            $('#ordDt').html('订单创建时间：'+data.ordDt);
            $('#expDt').html('订单关闭时间：'+data.expDt);
            var $shopNo = data.shopNo;
            $('#shopNo').val($shopNo);
            if ($shopNo){
            	//账户余额展示
//            	showAccountAmt($shopNo);
            }
            //状态步骤条
            switch (data.ordSts) {
                case 'V':
                    $("#ordrStsYstep").setStep(2);
                    break;
                case 'P':
                    $("#ordrStsYstep").setStep(3);
                    break;
                case 'S':
                    $("#ordrStsYstep").setStep(4);
                    break;
                default:
                    $("#ordrStsYstep").setStep(1);
                    break;
            }
        }else{
            layer.msg('获取门店订单信息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function showAccountAmt($shopNo){
	$.postJson(YOUNG_DOMAIN + "/shop/v1/qryShopAccountInfo.do",{shopNo : $shopNo}, function(data) {
        if (data.rspCd == "00000") {
        	var surplusAmt = parseFloat(data.surplusAmt); //当前账户余额
        	$('#payTip').html('当前账户余额(元): ￥'+surplusAmt);
        	var ordAndDistAmt = parseFloat($("#ordAndDistAmt").val());
        	if(surplusAmt < ordAndDistAmt){
        		$('#payBtn').attr('disabled', 'disabled')
        		var diffAmt = ordAndDistAmt - surplusAmt;
        		$('#difAmtTip').html('您还差'+diffAmt.toFixed(2)+'元'+'，请联系财务进行充值');
        	}
        }
    });
}

$('#payBtn').on('click', function(){
	var ordNo = $('#ordNo').val();
	var ordNm = $('#shopNm').text();
	var totAmt = $('#ordAndDistAmt').val();
	totAmt = parseFloat(totAmt).toFixed(2);
	var ordDesc = '阿里支付';
	var requestObj = {
			ordNo:ordNo,
			ordNm:ordNm,
			totAmt:totAmt,
			ordDesc:ordDesc
	};
	//setTimeout(function () {window.close();}, 2000);
	$.postJson(YOUNG_DOMAIN + "/alipay/pay.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
        	$('#payTip').html("");
        	$('#difAmtTip').html("");
        	$('#ordrSkuInfo').html("");
        	$('#payBtn').attr('disabled', 'disabled')
//        	openAlipay(data.rspInf);
        	document.write(data.rspInf);
//        	toastr.success("付款成功");
        } else{
        	toastr.error("付款失败");
        }
    });
});

//跳转到阿里支付界面
function openAlipay($content){
	var tabObj = new Object();
    tabObj.dataId = "resources/html/shop/bills/alipay.html?content="+$content;
    tabObj.desc = "阿里支付";
    parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
};

//获取url中的参数
function getUrlParam(name, distAmt) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var reg_ = new RegExp("(^|&)" + distAmt + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var temp = window.location.search;
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    var r_ = window.location.search.substr(1).match(reg_);  //匹配目标参数
    var distAmt = r_ != null ? unescape(r_[2]) : null;
    $("#distAmt").val(distAmt);
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

