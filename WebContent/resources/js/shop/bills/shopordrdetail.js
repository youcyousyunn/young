$("input[name='download']").on("click", function() {
    $ordNo = $('#ordNo').val();
    window.location.href = YOUNG_DOMAIN + "/cmm/v1/CommonExport.do?downType=1&ordNo="+$ordNo;
});

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
            title : "配送",
        }, {
            title : "完成",
        }]
    });
    
    var $ordNo = getUrlParam("ordNo");
    
    //订单信息展示
    showOrdrInfo($ordNo);
    
    //表格数据展示
    pagePost(requestObj);
});

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
        onLoadError : function(data) {
            $('#replenish_table').bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}
function handel(res){
   /* var $ordCnt = 0;
    for (var int = 0; int < res.rows.length; int++) {
        $ordCnt = parseFloat(res.rows[int].ordCnt)+$ordCnt;
    }
    $('#ordrSkuInfo').html($('#ordrSkuInfo').html()+"，商品总数："+$ordCnt);*/
    return res;
}

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
            //消息展示
            disPlayMsg(requestObj);
            $('#ordrSkuInfo').html("订单总数:" +data.ordCnt+ ",总计：SKU："+data.skuCnt+"，总价："+data.ordAmt);
        }else{
            layer.msg('获取门店订单信息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function disPlayMsg(requestObj){
    $.postJson(YOUNG_DOMAIN + "/sys/v1/qryFlowJrnLst.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            //清空列表
            $('#feedList').html('');
            for(var i=0;i<data.rows.length;i++){
            	var eachContent = data.rows[i].txCoent;
            	var index = eachContent.indexOf(',');
            	var content =  eachContent.substring(0, index);
                var innerHtml=''+
                    '<div class="feed-activity-list">'+
                        '<div class="feed-element">'+
                            '<dl class="dl-horizontal"><dt>'+data.rows[i].txUsrNm+'：</dt><dd></dd>'+
                            '<dd>'+content+'， 日期：'+data.rows[i].txDt+' '+data.rows[i].txTm+'</dd></dl>'+
                        '</div>'+
                     '</div>';
                $('#feedList').append(innerHtml);
            }
        }else{
            layer.msg('获取流程消息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}