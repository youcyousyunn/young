/*** 打印*/
var LODOP;
function myPreview(){
    LODOP=getLodop();  
    var strBodyStyle="<style>#replenishform_dy{display:block;}table,td,th { border: 1 solid #000000;border-collapse:collapse; }table {width: 100%;}</style>";
    var strFormHtml=strBodyStyle+"<body>"+document.getElementById("print_div").innerHTML+"</body>"
    LODOP.SET_PRINT_PAGESIZE(0,2400,900,"A4");    
    LODOP.ADD_PRINT_HTM(0,0,"100%","100%",strFormHtml);
    LODOP.PREVIEW();
}; 
    
$(function(){
    var $flowOrd = getUrlParam("flowOrd");
    var $ordNo = getUrlParam("ordNo");
    var reviewType = getUrlParam("reviewType");
    requestObj.flowJrn = getUrlParam("flowJrn");
    //根据流程号进行查询流程状态
    disPlayShowFlow($flowOrd);
    
    //订单信息数据展示
    showOrdrInfo($ordNo);
    
    //消息展示
    disPlayMsg($flowOrd);
    
    //表格展示
    pagePost(requestObj);
    
    //审核通过
    $('#accptBtn').on('click', function() {
        requestObj.reviewType = 'PASS';
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/reviewShopOrdr.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('审核成功');
                //再次刷新页面
                disPlayShowFlow($flowOrd);
                
                $('#replenish_table').bootstrapTable('refresh');
                layer.close(index);
            }else{
                layer.msg('审核失败：'+data.rspCd+','+data.rspInf);
            }
       });
    });
    
    //审核拒绝
    $('#refuseBtn').on('click', function(){
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/cancelShopOrdr.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('取消关闭成功');
              //再次刷新页面
                disPlayShowFlow($flowOrd);
                
                $('#replenish_table').bootstrapTable('refresh');
                layer.close(index);
            }else{
                layer.msg(data.rspInf+"取消门店订单失败:"+data.rspCd);
            }
        });
    });
});

//直接修改
function orders($input,$value){
    var $this = $($input);
    var $id = $this.data("unid");
    var $data = $('#replenish_table').bootstrapTable('getRowByUniqueId', $id);
    var maxnumber= $data.surplusCnt;
    var ordcur = $data.cnt;
    var sum=ordcur*$value;
    if(isNaN($value)){
        layer.msg('请输入数字');
        $('#replenish_table').bootstrapTable('load','$data.revwCnt');
    	return;
    }
    if(sum <= 0 ){
    	layer.msg('您输入的值不能小于等于0！');
    	$('#replenish_table').bootstrapTable('load','$data.revwCnt');
    	return;
    }
    var isApart=$data.isApart;
    if(isApart=='Y'){
		if(!(/^\d{1,10}(\.\d{1,2})?$/.test($value))){
			layer.msg("此商品下单数只支持两位小数点！");
			$('#replenish_table').bootstrapTable('load','$data.revwCnt');
    		return;
		}
    	$data.revwCnt = sum;
        requestObj.updTyp='RV';
        requestObj.jrnNo=$id;
        requestObj.updCnt=sum;
        //此处调用添加方法
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('修改成功');
            }else{
                layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
            }
       	});
    }else{
    	if (!(/(^[0-9]\d*$)/.test($value))){
    		layer.msg("此商品下单数不支持小数！");
    		$('#replenish_table').bootstrapTable('load','$data.revwCnt');
    		return;
    	}
    	$data.revwCnt = sum;
        requestObj.updTyp='RV';
        requestObj.jrnNo=$id;
        requestObj.updCnt=sum;
        //此处调用添加方法
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('修改成功');
            }else{
                layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
            }
       });
    }
}

//减少
function reduce($sapn){
    var $this = $($sapn);
    var $id = $this.data("unid");
    var $data = $('#replenish_table').bootstrapTable('getRowByUniqueId', $id);
    var txtJ;
    var maxnumber= $data.surplusCnt;//获取剩余配送数
    var ordcur = $data.cnt;
    var txt=$this.siblings('input').val();
    if(txt==1){
        layer.msg('真的不能再少了！');
    }else{
        txtJ=txt-1;
        var sum=ordcur*txtJ;
        $this.siblings('input').val(txtJ);
        $data.revwCnt = sum;
        requestObj.updTyp='RV';
        requestObj.jrnNo=$id;
        requestObj.updCnt=sum;
        //此处调用添加方法
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('修改成功');
            }else{
                layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
            }
       });
    } 
    $('#replenish_table').bootstrapTable('load','$data.revwCnt');
}

//增加
function increase($sapn){
    var $this = $($sapn);
    var $id = $this.data("unid");
    var $data = $('#replenish_table').bootstrapTable('getRowByUniqueId', $id);
    var ordcur = $data.cnt;
    var maxnumber= $data.surplusCnt;//获取剩余配送数
    var txtJ;
    var txt=$this.siblings('input').val();
    txtJ=txt*1 + 1;
    var sum=ordcur*txtJ;
    $this.siblings('input').val(txtJ);
    $data.revwCnt = sum;
    requestObj.updTyp='RV';
    requestObj.jrnNo=$id;
    requestObj.updCnt=sum;
    //此处调用添加方法
    $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            layer.msg('修改成功');
        }else{
            layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
        }
    });
    $('#replenish_table').bootstrapTable('load','$data.revwCnt');
}

function disPlayShowFlow($flowOrd){
    //根据流程号进行查询流程状态
    requestObj.flowOrd = $flowOrd;
    $.postJson(YOUNG_DOMAIN + "/sys/v1/getFlowOrdInfo.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            switch (data.flowSts) {
            case 'A1':
                data.flowSts='申请中';
                break;
            case 'C1':
                data.flowSts='进行中';
                break;
            case 'S1':
                data.flowSts='已完成';
                $('#accptBtn').attr("disabled","disabled");
                $('#refuseBtn').attr("disabled","disabled");
                break;
            default:
                $('#accptBtn').attr("disabled","disabled");
                $('#refuseBtn').attr("disabled","disabled");
                data.flowSts='未知状态';
                break;
            }
            $('#flowSts').html(data.flowSts);
            $('#flowOrd').val(data.flowOrd);
        }else{
            layer.msg('获取流程失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

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
                    field : "whsleUnit",
                    title : "零售单位"
                }, {
                    field : "price",
                    title : "零售单价"
                }, {
                    field : "ordCnt",
                    title : "申请订单量"
                },{
                    field : "replTyp",
                    title : "补货建议",
                    formatter : function(value, row, index) {
                        if("MAX"===value){
                            return "过大";
                        }else if("MIN"===value){
                            return "过小";
                        }else{
                            return "正常";
                        }
                    }
                }
                , {
                    field : "revwCnt",
                    title : "审核订单量",
                    formatter : function(value, row, index) {
                        var sum=row.revwCnt/row.cnt; 
		            	if (!(/(^[0-9]\d*$)/.test(sum))){
		        			var sums=sum.toFixed(2);
		        		}else{
		        			var sums=sum;
		        		}
                        var inputbox='<div class="inputbox clearfix">'
                                     +'<span onclick="reduce(this)" data-unid="'+row.jrnNo+'"  class="input-reduce">-</span>'
                                     +'<input type="text" data-unid="'+row.jrnNo+'" value="'+sums+'" onblur="orders(this,this.value)"/>'
                                     +'<span onclick="increase(this)" data-unid="'+row.jrnNo+'" class="input-increase">+</span>'
                                     +'<span class="inputboxrg">('+row.whsleUnit+')</span>'
                                     +'</div>'
                        return inputbox;
                    }
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
            
            switch (data.ordSts) {
            case 'S':
                data.ordSts='完成关闭';
                break;
            case 'V':
                data.ordSts='审核完成';
                break;
            case 'P':
                data.ordSts='配送中';
                break;
            case 'F':
                data.ordSts='取消关闭';
                break;
            default:
                data.ordSts='初始化';
                break;
            }
            $('#ordSts').html(data.ordSts);
            $('#ordrSkuInfo').html("总计：SKU："+data.skuCnt+"，总价："+parseFloat(data.ordAmt).toFixed(2));
        }else{
            layer.msg('获取门店订单信息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function disPlayMsg($flowOrd){
    requestObj.flowOrd=$flowOrd;
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