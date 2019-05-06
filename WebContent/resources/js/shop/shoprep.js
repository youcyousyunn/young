$(function() {
    requestObj.sort = "city"; // 排序列名
    requestObj.sortName = "desc";// 排位命令（desc，asc）
    showShopInfo();
    pagePost(requestObj);
    //删除操作
    $('#ordrapply_table').on("click", "button[name='delete-barCode']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        $('#ordrapply_table').bootstrapTable('removeByUniqueId', $uniqueid);
    });
    //跳转
    $('#shopordr_table').on("click", "button[name='flow-ordr']", function(e){
        var $uniqueid = $(this).data("uniqueid");
        var tabObj = new Object();
        tabObj.dataId = "shop/bills/pickingform.html?ordNo="+$uniqueid;
        tabObj.desc="门店订单跟踪";
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
    
    //跳转到订单付款界面
    $('#shopordr_table').on("click", "button[name='pay-ordr']", function(e){
        var $uniqueid = $(this).data("uniqueid");
        var tabObj = new Object();
        var distAmt = $('#distAmt').val();
        tabObj.dataId = "resources/html/shop/bills/payform.html?ordNo="+$uniqueid+"&distAmt="+distAmt;
        tabObj.desc="门店订单付款";
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
    //取消门店订单
    $('#shopordr_table').on("click", "button[name='delete-ordr']", function(e){
        requestObj.ordNo = $(this).data("uniqueid");
        $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/cancelShopOrdr.do", requestObj, function(data) {
            if (data.rspCd == "00000") {
                requestObj.ordNo = "";
                layer.msg("取消门店订单成功");
                $('#shopordr_table').bootstrapTable('refresh');
            }else{
                layer.msg(data.rspInf+"取消门店订单失败:"+data.rspCd);
            }
        });
    });
    $('#addBtns').on("click", function(e) {
        var tabObj = new Object();
        tabObj.dataId = "resources/html/shop/shopreptype.html";
        tabObj.desc="创建订单";
        parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
    });
});

/**
 * 查询门店信息
 */
function showShopInfo(){
	$.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/qryShopInfoByUsr.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            $('#shopNm').val(data.shopNm);
            $('#shopNo').val(data.shopNo);
            $('#prov').val(data.prov);
            $('#city').val(data.city);
            $('#shopTyp').val(data.shopType);
            $('#distAmt').val(data.distAmt);
        }
    });
}

/**
 * 初始化订单列表
 */
function pagePost(requestObj) {
    // 初始化table
    $('#shopordr_table').bootstrapTable({
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
        queryParams : function(params){
            requestObj.ordCls='NORMAL';
            requestObj.currentPage = params.pageNumber - 1;
            requestObj.pageSize = params.pageSize;
            requestObj.prov = $('#prov').val();
            requestObj.city = $('#city').val();
            requestObj.shopTyp = $('#shopTyp').val();
            requestObj.shopNo = $('#shopNo').val();
            requestObj.ordTyp = '01';
            return requestObj;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber : 1, // 初始化加载第一页，默认第一页
        pageSize : 10, // 每页的记录行数（*）
        pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
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
        columns: 
        [{
            field : 'index',
            title : '序号',
            formatter : function(value, row, index) {
                return index+1;
            }
        },
        {
            field : 'ordNo',
            title : '门店订单号',
            formatter : function(value, row, index){
            	var ordSts = row.ordSts;
                var detail_ref = '<a href="javascript:void(0)" onclick="openTab(this);">'+value+'</a>'
                			   + '<input type="hidden" value="'+ordSts+'" />';
                return detail_ref;
            }
        },
        {
            field : "ordDt",
            title : "订单创建时间"
        },
        {
            field : "usrNm",
            title : "创建人"
        },
        {
            field : "skuCnt",
            title : "SKU"
        },
        {
            field : "satisSkuCnt",
            title : "数量"
        },
        {
            field : "satisSkuPct",
            title : "SKU完成率"
        },
        {
            field : "satisPrdPct",
            title : "数量完成率"
        },
        {
            field : "ordSts",
            title : "状态",
            formatter : function(value, row, index){
                switch (value) {
                case "W":
                    value = "申请中";
                    break;
                case "V":
                    value = "审核完成";
                    break;
                case "M":
                    value = "付款完成";
                    break;
                case "P":
                    value = "配送中";
                    break;
                case "F":
                    value = "取消关闭";
                    break;
                default:
                    value = "完成关闭";
                    break;
                }
                return value;
            }
        },
        {
            title : "操作",
            width : "15%",
            clickToSelect : false,
            formatter : function(value, row, index) {
                var flow_button = '<button name="flow-ordr" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                        + row.ordNo
                        + '><i class="glyphicon glyphicon-edit"></i>订单跟踪</button>';
                var pay_button = '<button name="pay-ordr" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                    + row.ordNo
                    + '><i class="glyphicon glyphicon-edit"></i>付款</button>';
                var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-ordr" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                        + row.ordNo
                        + '><i class="glyphicon glyp hicon-trash"></i>撤销</button>';
                if("W"==row.ordSts){
                    return flow_button + delete_button;
                } else if("V" == row.ordSts){
                	return pay_button + " " + flow_button;
                }
                return flow_button;
            }
        } ],
        silent : true, // 刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { // 没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
            $('#shopordr_table').bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}
function handel(res) {
    return res;
}

function openTab(tabs){
    var tabObj = new Object();
    var ordNo = $(tabs).text();
    tabObj.dataId = "resources/html/shop/bills/shopordrdetail.html?ordNo="+ordNo;
    tabObj.desc = "门店订单明细";
    parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
}