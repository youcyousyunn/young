$(function() {
    //表格数据
    pagePost(requestObj);
    //数据权限城市列表
//    selectCity(requestObj);
    
    /**
     * 审核弹窗
     */
    $('#reviwOrdr').on('click', function(){
        var checkItems = $('input[name="btSelectItem"]:checked');
        var order = $('#order_table').bootstrapTable('getSelections')[0];
        
        if(checkItems.length!=1){
            layer.msg('请选择一条数据进行操作');
        }else{
            
            if("W"!=order.ordSts){
                layer.msg('非正常状态无法操作');
                return false;
            }
            
            //审核表格
            var $ordNo = order.ordNo;
            requestObj.ordNo = $ordNo;
            $('#orderrev_table').bootstrapTable('refresh',{url:YOUNG_DOMAIN + '/shop/v1/qryShopOrdrDetailInfo.do'});
            $('#ordrInfo').html('订单号：   '+order.ordNo+' 门店：    '+order.shopNm+' 发起人：'+order.usrNm);
            
            //根据流程号进行查询流程状态
            disPlayShowFlow($ordNo);
            
            //展示订单信息
            showOrdrInfo($ordNo);
            
            layer.open({
                title: '订单审核',
                type: 1,
                area: ['98%', '90%'],
                maxmin: true,
                skin: 'layui-layer-rim',
                shadeClose: false, //点击遮罩关闭
                content: $('#orderRevDiv').show(),
                btn: ['同意','关闭'],
                yes: function(index){
                    requestObj.reviewType = "PASS";
                    $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/reviewShopOrdr.do", requestObj, function(data) {
                        if (data.rspCd == "00000") {
                            layer.msg('审核成功');
                            
                            //再次刷新页面
                            disPlayShowFlow($ordNo);
                            $('#order_table').bootstrapTable('refresh');
                            layer.close(index);
                        }else{
                            layer.msg('审核失败：'+data.rspCd+','+data.rspInf);
                        }
                   });
                }
            });
        }
    });
    
    /**
     * 配货
     */
    $('#distOrdr').on('click', function(){
        var orders = $('#order_table').bootstrapTable('getAllSelections');
        
        if(0==orders.length){
            layer.msg('请选择一条数据进行操作');
            return false;
        }else if(6<=orders.length){
            layer.msg('请选择五条以下数据进行操作');
            return false;
        }
        
        var ordNoAry = new Array();
        
        for (var int = 0; int < orders.length; int++) {
            ordNoAry[int]=orders[int].ordNo;
        }
        
        var tabObj = new Object();
        tabObj.dataId="shop/shoponlyorder.html?ordNo="+ordNoAry;
        
        if(1<orders.length){
            tabObj.dataId="shop/shopbatchorder.html?ordNo=["+ordNoAry+"]";
        }
        
        tabObj.desc = "门店订单配货";
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
    
    //表格收货按钮
    $('#order_table').on("click", "button[name='recv-ordr']", function(e) {
        var tabObj = new Object();
        tabObj.desc = "门店订单收货";
        tabObj.dataId="shop/shoponlyorder.html?ordNo="+$(this).data('uniqueid');
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
    
    //表格订单跟踪
    $('#order_table').on("click", "button[name='track-ordr']", function(e) {
        var tabObj = new Object();
        tabObj.desc = "门店订单跟踪";
        tabObj.dataId="shop/shoponlyorder.html?ordNo="+$(this).data('uniqueid');
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
});


//直接修改
function orders($input,$value){
    var $this = $($input);
    var $id = $this.data("unid");
    var $data = $('#orderrev_table').bootstrapTable('getRowByUniqueId', $id);
    var maxnumber= $data.surplusCnt;
    var ordcur = $data.cnt;
    var sum=ordcur*$value;
    if(isNaN(sum)||sum===''){
        layer.msg('请输入数字');
        $('#orderrev_table').bootstrapTable('load','$data.revwCnt');
        return;
    }
    if(sum < 0){
        layer.msg('您输入的值不能小于0！');
       $('#orderrev_table').bootstrapTable('load','$data.revwCnt');
        return;
    }
//  if( eval(sum)>=0 ){
//      $data.revwCnt = sum;
//      requestObj.updTyp='RV';
//      requestObj.jrnNo=$id;
//      requestObj.updCnt=sum;
//      //此处调用添加方法
//      $.postJson(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
//          if (data.rspCd == "00000") {
//              layer.msg('修改成功');
//          }else{
//              layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
//          }
//     });
//  }
    
    var isApart=$data.isApart;
    if(isApart=='Y'){
		if(!(/^\d{1,10}(\.\d{1,2})?$/.test($value))){
			layer.msg("此商品下单数只支持两位小数点！");
			$('#orderrev_table').bootstrapTable('load','$data.revwCnt');
        	return;
		}
    	$data.revwCnt = sum.toFixed(2);
        requestObj.updTyp='RV';
        requestObj.jrnNo=$id;
        requestObj.updCnt=sum;
        //此处调用添加方法
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
            	$('#orderrev_table').bootstrapTable('load','$data.revwCnt');
                layer.msg('修改成功');
            }else{
                layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
            }
       	});
    }else{
    	if (!(/(^[0-9]\d*$)/.test($value))){
    		layer.msg("此商品下单数不支持小数！");
    		$('#orderrev_table').bootstrapTable('load','$data.revwCnt');
        	return;
    	}
    	$data.revwCnt = sum.toFixed(2);
        requestObj.updTyp='RV';
        requestObj.jrnNo=$id;
        requestObj.updCnt=sum;
        //此处调用添加方法
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/updShopOrdrDetailCnt.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                layer.msg('修改成功');
                $('#orderrev_table').bootstrapTable('load','$data.revwCnt');
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
    var $data = $('#orderrev_table').bootstrapTable('getRowByUniqueId', $id);
    var txtJ;
    var maxnumber= $data.surplusCnt;//获取剩余配送数
    var ordcur = $data.cnt;
    var txt=$this.siblings('input').val();
    if(txt==0){
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
                $('#orderrev_table').bootstrapTable('load','$data.revwCnt');
            }else{
                layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
            }
       });
    } 
}
//增加
function increase($sapn){
    var $this = $($sapn);
    var $id = $this.data("unid");
    var $data = $('#orderrev_table').bootstrapTable('getRowByUniqueId', $id);
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
            $('#orderrev_table').bootstrapTable('load','$data.revwCnt');
        }else{
            layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function pagePost(requestObj) {
    // 初始化table
    $('#order_table').bootstrapTable({
        url : YOUNG_DOMAIN + "/shop/v1/qryShopOrdrLst.do", // 请求后台的URL（*）
        dataType : "json",
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : true, // 是否显示分页（*）
        sortable : true, // 是否启用排序
        sortName : "ordNo",
        sortOrder : "desc",
        queryParamsType : "custom",
        queryParams : function(params) {
            requestObj.ordNo=$('#ordNoTxt').val();
            requestObj.city=$('#cityNmSel').val();
            requestObj.shopNo=$('#shopNmSel').val();
            requestObj.lineNo=$('#lineNmSel').val();
            requestObj.ordSts=$('#ordrSts').val();
            requestObj.ordCls=$('#ordClsSel').val();
            requestObj.ordTyp = '01';
            requestObj.currentPage = params.pageNumber - 1;
            requestObj.pageSize = params.pageSize;
            return requestObj;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber : 1, // 初始化加载第一页，默认第一页
        pageSize : 10, // 每页的记录行数（*）
        pageList : [10, 25, 50, 100], // 可供选择的每页的行数（*）
        search : false, // 是否显示表格搜索
        strictSearch : true,
        showColumns : true, // 是否显示所有的列
        showRefresh : true, // 是否显示刷新按钮
        minimumCountColumns : 2, // 最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
        uniqueId : "ordNo", // 每一行的唯一标识，一般为主键列
        showToggle : true, // 是否显示详细视图和列表视图的切换按钮
        cardView : false, // 是否显示详细视图
        detailView : false, // 是否显示父子表
        responseHandler : handel,
        idField : "ordNo",
        iconSize : "outline",
        icons : {
            refresh : "glyphicon-repeat",
            toggle : "glyphicon-list-alt",
            columns : "glyphicon-list"
        },
        columns : 
        [{
            field : "index",
            checkbox : true
        },{
            field : "ordNo",
            title : "订单号",
            formatter : function(value, row, index){
                var detail_ref = '<a href="javascript:void(0)" onclick="openTab(this);">'+value+'</a>';
                return detail_ref;
            }
        },{
            field : "ordCls",
            title : "订单分类",
            formatter : function(value, row, index) {
                switch (value) {
                case "FRESH":
                    value = "生鲜单";
                    break;
                case "BFOOD":
                    value = "早餐单";
                    break;
                case "LFOOD":
                    value = "午餐单";
                    break;
                case "MATER":
                    value = "物料单";
                    break;
                case "RMATER":
                    value = "原料单";
                    break;
                default:
                    value = "普通单";
                    break;
                }
                return value;
            }
        },{
            field : "city",
            title : "城市"
        },{
            field : "shopNm",
            title : "店名"
        }/*,{
            field : "brandSts",
            title : "所属线路"
        }*/,{
            field : "stohNm",
            title : "负责仓库"
        },{
            field : "skuCnt",
            title : "SKU"
        },{
            field : "satisSkuCnt",
            title : "满足数量"
        },{
            field : "satisSkuPct",
            title : "SKU完成率"
        },{
            field : "satisPrdPct",
            title : "数量完成率"
        },{
            field : "ordDt",
            title : "提交时间"
        },{
            field : "effDay",
            title : "处理时长"
        },{
            field : "ordSts",
            title : "状态",
            formatter : function(value, row, index) {
                switch (value) {
                case "W":
                    value = "申请中";
                    break;
                case "V":
                    value = "审核完成";
                    break;
                case "T":
                    value = "分拣中";
                    break
                case "P":
                    value = "配送中";
                    break;
                default:
                    value = "完成关闭";
                    break;
                }
                return value;
            }
        },{
            title : "操作",
            clickToSelect : false,
            formatter : function(value, row, index) {
                var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-ordr" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                        + row.ordNo
                        + '>取消</button>';
                var recv_button = '&nbsp;&nbsp;&nbsp;<button name="recv-ordr" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                    + row.ordNo
                    + '>收货</button>';
                var track_button = '&nbsp;&nbsp;&nbsp;<button name="track-ordr" type="button" class="btn btn-info btn-xs" data-uniqueid='
                    + row.ordNo
                    + '>订单跟踪</button>';
                if("W"==row.ordSts){
                    return delete_button;
                }
                if("P"==row.ordSts){
                    return recv_button;
                }
                if("V"==row.ordSts){
                    return track_button;
                }
            }
        }],
        silent : true, // 刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { // 没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
            $('#order_table').bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
    
    $('#orderrev_table').bootstrapTable({
        dataType : "json",
        method : 'post', // 请求方式（*）
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : true, // 是否显示分页（*）
        sortable : true, // 是否启用排序
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
        pageSize : 100, // 每页的记录行数（*）
        pageList : [25, 50, 100, 200], // 可供选择的每页的行数（*）
        search : false, // 是否显示表格搜索
        strictSearch : true,
        showColumns : false, // 是否显示所有的列
        showRefresh : false, // 是否显示刷新按钮
        minimumCountColumns : 2, //最少允许的列数
        clickToSelect : true, //是否启用点击选中行
        uniqueId : "jrnNo", //每一行的唯一标识，一般为主键列
        showToggle : false, //是否显示详细视图和列表视图的切换按钮
        cardView : false, //是否显示详细视图
        detailView : false, //是否显示父子表
        responseHandler : handel,
        idField : "jrnNo",
        iconSize : "outline",
        icons : {
            refresh : "glyphicon-repeat",
            toggle : "glyphicon-list-alt",
            columns : "glyphicon-list"
        },
        columns : [ {
            field : 'index',
            title : '序号',
            formatter : function(value, row, index) {
                return index+1;
            }
        }, {
            field : 'barCode',
            title : '条码',
        }, {
            field : "prdNm",
            title : "品名"
        }, {
            field : "spec",
            title : "发货规格"
        }, {
            field : "whsleUnit",
            title : "发货单位",
        }, {
            field : "price",
            title : "批发单价",
        }, {
            field : "sellNum",
            title : "门店库存",
        }/*, {
            field : "sellNum",
            title : "上一周期销售量",
        }, {
            field : "cotldSts",
            title : "均值",
        }, {
            field : "cotldSts",
            title : "趋势",
        }, {
            field : "nowNum",
            title : "当前库存量",
        }*/, {
            field : "deliverCnt",
            title : "配送在途量",
        }, {
            field : "ordCnt",
            title : "申请订单量",
        }, {
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
        },{
            field : "deliverCnt",
            title : "零售单位数量",
            formatter : function(value, row, index){
            	var revwCnt=row.revwCnt;
            	var sum='<div>'
	                    +'<span>'+parseInt(revwCnt)+'</span>'
	                    +'<span>('+row.unit+')</span>'
	                    +'</div>'
                return sum;
            }
        }],
        silent : true, // 刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { // 没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
            $('#orderrev_table').bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}
function handel(res) {
    return res;
}

function disPlayShowFlow($ordNo){
    $.postJson(YOUNG_DOMAIN + "/sys/v1/getFlowOrdInfo.do",{ordNo:$ordNo}, function(data) {
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
                break;
            default:
                data.flowSts='未知状态';
                break;
            }
            $('#flowSts').html(data.flowSts);
            $('#flowOrd').val(data.flowOrd);
            
            requestObj.flowOrd=data.flowOrd;
            disPlayMsg(requestObj);
        }else{
            layer.msg('获取流程失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function showOrdrInfo($ordNo){
    requestObj.ordNo=$ordNo;
    $.postJson(YOUNG_DOMAIN + "/shop/v1/qryShopOrdrInfo.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
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
            case 'T':
                data.ordSts='分拣中';
                break;
            default:
                data.ordSts='初始化';
                break;
            }
            $('#ordSts').html(data.ordSts);
            $('#ordrSkuInfo').html("总计：SKU："+data.skuCnt+"，总价："+data.ordAmt+"");
        }else{
            layer.msg('获取订单信息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function disPlayMsg(requestObj){
    $.postJson(YOUNG_DOMAIN + "/sys/v1/qryFlowJrnLst.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            //清空列表
            $('#feedList').html('');
            for(var i=0;i<data.rows.length;i++){
                var innerHtml=''+
                    '<div class="feed-activity-list">'+
                        '<div class="feed-element">'+
                            '<dl class="dl-horizontal"><dt>'+data.rows[i].txUsrNm+'：</dt><dd></dd>'+
                            '<dd>'+data.rows[i].txCoent+' 日期：'+data.rows[i].txDt+' '+data.rows[i].txTm+'</dd></dl>'+
                        '</div>'+
                     '</div>';
                $('#feedList').append(innerHtml);
            }
            requestObj.flowJrn=data.rows[data.rows.length-1].jrnNo;
        }else{
            layer.msg('获取流程消息失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

//查询城市
function selectCity(requestObj){
    $.postJson(YOUNG_DOMAIN + "/dataauth/v1/qryAuthData.do", {authKey:"CITY"}, function(data) {
        if (data.rspCd == "00000") {
            $('#cityNmSel').empty();
            var $option = '<option value ="">所有</option>';
            for(var i in data.authData) {
                $options += "<option value='" + data.authData[i].authDataKey + "'>" + data.authData[i].authDataVal + "</option>";
            }
            $('#cityNmSel').html($option);
        } else {
            layer.msg("获取城市数据权限失败");
        }
    });
}
//查询门店
function selectShop(obj){
    $.postJson(YOUNG_DOMAIN + "/dataauth/v1/qryAuthData.do", {authKey:"SHOP"}, function(data) {
        if (data.rspCd == "00000") {
            $('#shopNmSel').empty();
            var $option = '<option value ="">所有</option>';
            for(var i in data.authData) {
                $options += "<option value='" + data.authData[i].authDataKey + "'>" + data.authData[i].authDataVal + "</option>";
            }
            $('#shopNmSel').html($option);
        } else {
            layer.msg("获取门店数据权限失败");
        }
    });
}
//查询线路
function selectLine(obj){
    requestObj.shopNo=$(obj).val();
    $.postJson(YOUNG_DOMAIN + "/line/v1/qryLineInfosByShop.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            $('#lineNmSel').empty();
            var firtHtml = '<option value ="">所有</option>';
            $('#lineNmSel').append(firtHtml);
            for(var line=0;line<data.lineinfos.length;line++){
                var innerHtml = '<option value ="'+data.lineinfos[line].lineNo+'">'+data.lineinfos[line].lineName+'</option>';
                $('#lineNmSel').append(innerHtml);
            }
        }else{
            layer.msg('获取线路失败：'+data.rspCd+','+data.rspInf);
        }
    });
}
//按照条件查询
function serahBtn(){
    requestObj.ordNo=$('#ordNoTxt').val();
    requestObj.city=$('#cityNmSel').val();
    requestObj.shopNo=$('#shopNmSel').val();
    requestObj.lineNo=$('#lineNmSel').val();
    requestObj.ordSts=$('#ordrSts').val();
    $('#order_table').bootstrapTable("refresh",{url : YOUNG_DOMAIN + "/shop/v1/qryShopOrdrLst.do"});
}

function openTab(tabs){
    var tabObj = new Object();
    var ordNo = $(tabs).text();
    tabObj.dataId = "shop/bills/shopordrdetail.html?ordNo="+ordNo;
    tabObj.desc = "门店订单明细";
    parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
}