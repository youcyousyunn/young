/**
 * 初始化常量
 */
var $shopOrdr_table = $('#shopOrdr_table');
var $localStorage = window.localStorage;
var $prov,$city,$stohNo;

$(function(){
	//日期控制
    var start = {
        format: 'YYYY-MM-DD',
        initAddVal:[0],
        festival:true,
        isinitVal:true,
        maxDate: $.nowDate(0), //最大日期
        choosefun: function(elem,datas){
        }
    };
    
    // 订单日期
    $('#ordDtTxt').jeDate(start);
    
    /**
     * 初始查询门店订单
     */
    initShopOrdrTab($shopOrdr_table);
    
    /**
     * 搜索按钮
     */
    $('#serahBtn').on('click',function(){
        $shopOrdr_table.bootstrapTable('refresh');
    });
    
    /**
     * 订单状态change事件
     */
    $('#ordStsSel').on('change',function(){
    	$shopOrdr_table.bootstrapTable('refresh');
    });
    
    /**
     * 订单分类change事件
     */
    $('#ordClsSel').on('change',function(){
    	$shopOrdr_table.bootstrapTable('refresh');
    });
    
    
    /**
     * 合并门店订单预览
     */
    $('#merge_btn').on('click',function(){
        var $datalst = $shopOrdr_table.bootstrapTable('getAllSelections');
        
        //检查选择的订单
        if(!checkSelOrdr($datalst)){
            return;
        }
        
        //构造订单号列表
        var $shopOrdNoLst=new Array();
        for (var int = 0; int < $datalst.length; int++) {
            $shopOrdNoLst.push($datalst[int].ordNo);
        }
        
        //弹出合并预览窗口
        $.get(YOUNG_DOMAIN + "/resources/html/coo/dialog/mergeordrprview.html", function(html) {
            layer.open({
                id : "mergeOrdrPrview-dialog",
                type : 1,
                title : "订单合并预览",
                content : html,
                area : [ "98%", "98%" ],
                scrollbar : false,
                maxmin: true,
                skin: 'layui-layer-rim',
                shadeClose: false,
                btn : [ "合并", "取消" ],
                success : function() {
                	$stohNo = $datalst[0].stohNo
                    var requestParam = {
                        shopOrdNoLst:$shopOrdNoLst,
                        stohNo:$datalst[0].stohNo
                    };
                    var $mergeDetailtable = $('#mergeDetail_table');
                    initMergeDetailTable($mergeDetailtable, requestParam);
                },
                yes : function(index) {
            		requestObj.ordTyp = '01';
            		requestObj.stohNo = $stohNo;
            		requestObj.stoHoseOrdrDetailPoLst = JSON.parse($localStorage.getItem("MergeData"));
                    layer.load();
                    $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/shopOrdrCombinSubmit.do", requestObj, function(data) {
                        if (data.rspCd == "00000") {
                            layer.closeAll('loading');
                            layer.msg('合并采购单成功');
                            layer.close(index);
                            $shopOrdr_table.bootstrapTable('refresh');
                            
                            layer.msg('是否立即打开采购订单页面下载订单明细？', {
                                time: 0, //不自动关闭
                                btn: ['好的', '不了'],
                                yes: function(index){
                                    layer.close(index);
                                    var $linkUrl = YOUNG_DOMAIN + "/coo/mergepurchordr.html";
                                    parent.buildTab($linkUrl, $linkUrl, "采购订单", $linkUrl, true, true);
                                }
                            });
                        }else{
                            layer.msg('合并采购单失败：'+data.rspCd+','+data.rspInf);
                        }
                    });
                }
            });
        });
    });
});

/**
 * 检查获取对应的订单号进行合并,
 * 非同一种类型的订单不让合并,
 * 非审核完成状态的订单不让合并
 */
var checkSelOrdr = function(datalst){
    var $checkFlag = true;
    if(0 >= datalst.length){
        layer.msg("请选择至少一条订单！！！");
        $checkFlag = false;
        return;
    }
    for (var int = 0; int < datalst.length; int++) {
        //不同的订单分类不允许合并
        if (datalst[0].ordCls != datalst[int].ordCls) {
            layer.msg("存在不同分类的订单，请选择相同分类的订单！！！");
            $checkFlag = false;
            return;
        }
        //不能跨城市操作
        if ((datalst[0].prov != datalst[int].prov) || (datalst[0].city != datalst[int].city)) {
            layer.msg("无法操作跨省份城市订单！！！");
            $checkFlag = false;
            return;
        }
        // 非审核状态的不允许合并
        if('V'!=datalst[int].ordSts){
            layer.msg("存在非审核完成的订单，请选择审核完成的订单！！！");
            $checkFlag = false;
            return;
        }
        $prov = datalst[0].prov;
        $city = datalst[0].city;
    }
    return $checkFlag;
}

/**
 * 初始化查询门店订单
 * @param $shopOrdTab
 */
function initShopOrdrTab($shopOrdTab) {
    // 初始化table
    $shopOrdTab.bootstrapTable({
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
            var searchParams = {
                ordNo:$('#ordNoTxt').val(),
                ordCls:'-1' != $('#ordClsSel').val() ? $('#ordClsSel').val() : "",
                ordSts:'-1' != $('#ordStsSel').val() ? $('#ordStsSel').val() : "",
                ordDt:$('#ordDtTxt').val(),
                ordTyp:'01',
                currentPage:params.pageNumber - 1,
                pageSize:params.pageSize
            };
            return searchParams;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber : 1, // 初始化加载第一页，默认第一页
        pageSize : 25, // 每页的记录行数（*）
        pageList : [25, 50, 100, 150, 200], // 可供选择的每页的行数（*）
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
            formatter : function(value, row, index) {
                var detail_ref = '<a href="javascript:void(0)" onclick="openTab(this);">'+value+'</a>';
                return detail_ref;
            }
        },{
            field : "ordCls",
            title : "订单分类",
            formatter : function(value, row, index) {
                switch (value) {
                case "FRESH":
                    value = "生鲜订单";
                    break;
                case "BFOOD":
                    value = "早餐订单";
                    break;
                case "LFOOD":
                    value = "午餐订单";
                    break;
                case "MATER":
                    value = "普通物料";
                    break;
                case "FMATER":
                    value = "餐饮物料";
                    break;
                case "RMATER":
                    value = "原料订单";
                    break;
                default:
                    value = "普通订单";
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
            title : "订单日期"
        },{
            field : "expDt",
            title : "过期日期"
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
                case "D":
                    value = "配货完成";
                    break;
                case "T":
                    value = "分拣完成";
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
        }],
        silent : true, // 刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { // 没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
            $shopOrdTab.bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}

/**
 * 初始化合并预览窗口
 * @param $table
 * @param requestObj
 */
function initMergeDetailTable($table, requestObj) {
    // 初始化table
    $table.bootstrapTable({
        url : YOUNG_DOMAIN + "/shop/v1/shopOrdrCombin.do", // 请求后台的URL（*）
        dataType : "json",
        method : 'post', // 请求方式（*）
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : false, // 是否显示分页（*）
        sortable : false, // 是否启用排序
        queryParamsType : "custom",
        queryParams : function(params) {
            requestObj.currentPage = params.pageNumber - 1;
            requestObj.pageSize = params.pageSize;
            return requestObj;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber : 1, // 初始化加载第一页，默认第一页
        pageSize : 100, // 每页的记录行数（*）
        pageList : [100, 150, 200, 250], // 可供选择的每页的行数（*）
        search : false, // 是否显示表格搜索
        strictSearch : true, //
        showColumns : false, // 是否显示所有的列
        showRefresh : true, // 是否显示刷新按钮
        minimumCountColumns : 2, // 最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
        uniqueId : "barCode", // 每一行的唯一标识，一般为主键列
        showToggle : false, // 是否显示详细视图和列表视图的切换按钮
        cardView : false, // 是否显示详细视图
        detailView : false, // 是否显示父子表
        responseHandler : handel,
        idField : "barCode",
        iconSize : "outline",
        icons : {
            refresh : "glyphicon-repeat",
            toggle : "glyphicon-list-alt",
            columns : "glyphicon-list"
        },
        columns :
        [{
            field : "barCode",
            title : "条码"
        },{
            field : "prdNm",
            title : "商品名称"
        },{
            field : "suppliNm",
            title : "供应商",
            formatter : function(value, row, index) {
                if('Y'===row.moreSuppli){
                    var selects='<div id="dropdown"><p onclick="initSelected(this)" id="'+row.barCode+'"  data-unid="'+row.barCode+'">'+ row.suppliNm + '</p><ul></ul></div>'
                    return selects;
                }else{ 
                    return row.suppliNm;
                }
            }
        },{
            field : "sourcUnit",
            title : "采购单位"
        },{
            field : "sourcSpec",
            title : "采购规格"
        },{
            field : "sourcPrice",
            title : "采购价格"
        },{
            field : "needCnt",
            title : "需求数量"
        },{
            field : "nowCnt",
            title : "仓库库存数量"
        },{
            field : "upSellCnt",
            title : "门店上周销量"
        },{
            field : "upmSellCnt",
            title : "门店上月销量"
        },{
            field : "ordCnt",
            title : "订单量",
            formatter : function(value, row, index) {
                var sum=row.ordCnt/row.sourcCnt;
                if (!(/(^[0-9]\d*$)/.test(sum))){
                    var sums=sum.toFixed(2);
                }else{
                    var sums=sum;
                }
                var inputbox='<div class="inputbox clearfix">'
                             +'<span data-unid="'+row.barCode+'"  class="input-reduce">-</span>'
                             +'<input class="direct" type="text" data-unid="'+row.barCode+'" value="'+sums+'"/>'
                             +'<span data-unid="'+row.barCode+'" class="input-increase">+</span>'
                             +'<span class="inputboxrg">('+row.sourcUnit+')</span>'
                             +'</div>';
                return inputbox;
            }
        },{
            field : "deliverCnt",
            title : "零售单位数量",
            formatter : function(value, row, index){
                var fmtHtml='<div>'
                        +'<span>'+parseInt(row.ordCnt)+'</span>'
                        +'<span>('+row.posUnit+')</span>'
                        +'</div>'
                return fmtHtml;
            }
        },{
            title : "操作",
            formatter : function(value, row, index){
                var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-barCode" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                    + row.barCode
                    + '><i class="glyphicon glyphicon-trash"></i>删除</button>';
                return delete_button;
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
            $table.bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        },
        onLoadSuccess : function(data){
            initMergeData(data.rows);
        }
    });
    
    //删除操作
    $table.on("click", "button[name='delete-barCode']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        $table.bootstrapTable('removeByUniqueId', $uniqueid);
    });
    
    // 减少
    $table.on("click", ".input-reduce", function() {
        var $this = $(this);
        var $id = $this.data("unid");
        var $data = $table.bootstrapTable('getRowByUniqueId',$id);
        var txtJ;
        var cnt = $data.sourcCnt;
        var txt = $this.siblings('input').val();
        if (txt == 0) {
            layer.msg("真的不能再少了！");
        } else {
            txtJ = txt - 1;
            $this.siblings('input').val(txtJ);
            var sum = cnt * txtJ;
            $data.ordCnt = sum;
            $table.bootstrapTable('updateByUniqueId', {id: $id, row: $data});
            updMergeData($data.barCode,"ordCnt",$data.ordCnt);
        }
    });
    
    //增加
    $table.on("click", ".input-increase", function(){
        var $this = $(this);
        var $id = $this.data("unid");
        var $data = $table.bootstrapTable('getRowByUniqueId', $id);
        var cnt = $data.sourcCnt;
        var txtJ;
        var txt=$this.siblings('input').val();
        txtJ=txt*1 + 1;
        $this.siblings('input').val(txtJ);
        var sum=cnt*txtJ;
        $data.ordCnt = sum;
        $table.bootstrapTable('updateByUniqueId', {id: $id, row: $data});
        updMergeData($data.barCode,"ordCnt",$data.ordCnt);
    });
    
    //直接修改
    $table.on("blur", ".direct", function(){
        var $this = $(this);
        var $id = $this.data("unid");
        var $data = $table.bootstrapTable('getRowByUniqueId', $id);
        var cnt = $data.sourcCnt;
        var $value=$this.val();
        if(isNaN($value)){
            layer.msg("请输入数字！");
            $this.val('0');
            $data.ordCnt = 0;
            return;
        }
        if($value<0){
            layer.msg("最小订单量不能为0！");
            $this.val('0');
            $data.ordCnt = 0;
            return;
        }
        var isApart=$data.isApart;
        if(isApart=='Y'){
            if(!(/^\d{1,10}(\.\d{1,2})?$/.test($value))){
                layer.msg("此商品下单数只支持两位小数点！");
                $this.val('0');
                $data.ordCnt = 0;
                return;
            }
        }else{
            if (!(/(^[0-9]\d*$)/.test($value))){
                layer.msg("此商品下单数不支持小数！");
                $this.val('0');
                $data.ordCnt = 0;
                return;
            }
            var sum=cnt*$value;
            $data.ordCnt = sum.toFixed(2);
            $table.bootstrapTable('updateByUniqueId', {id: $id, row: $data});
            updMergeData($data.barCode,"ordCnt",$data.ordCnt);
        }
    });
}

function initMergeData($datalst){
    //将合并预览的数据缓存起来
    /**
     * 存储对象为:{"barCode":"123456","suppliNo":"123123","ordCnt":"12.22"}
     */
    var $tmpData = new Array();
    for ( var int in $datalst) {
        $tmpData.push({
            barCode:$datalst[int].barCode,
            suppliNo:$datalst[int].suppliNo,
            ordCnt:$datalst[int].ordCnt
        });
    }
    if (null != $tmpData || $tmpData.length > 0) {
        $localStorage.setItem("MergeData", JSON.stringify($tmpData));
    }
}

/**
 * 删除对应barCode数据
 * @param $barCode
 */
function delMergeData($barCode){
    var $tmpData = JSON.parse($localStorage.getItem("MergeData"));
    for (var i in $tmpData) {
        var $object = $tmpData[i];
        if($object.barCode===$barCode){
            $tmpData.splice(i, $object);
            continue;
        }
    }
    if (null != $tmpData || $tmpData.length > 0) {
        $localStorage.setItem("MergeData", JSON.stringify($tmpData));
    }
}

/**
 * 更新指定的缓存对象
 * @param $barCode
 * @param $keyname
 * @param $value
 */
function updMergeData($barCode,$keyname,$value){
    var $tmpData = JSON.parse($localStorage.getItem("MergeData"));
    for (var i in $tmpData) {
        var $object = $tmpData[i];
        if($object.barCode===$barCode){
            for(var key in $object){
                if(key===$keyname){
                    $object[key]=$value;
                }
            }
        }
    }
    
    if (null != $tmpData || $tmpData.length > 0) {
        $localStorage.setItem("MergeData", JSON.stringify($tmpData));
    }
}

function initSelected(select){
    var $this = $(select);
    var $barCode = $this.data("unid");
    var thisId = $this.attr('id');
    var $table = $('#mergeDetail_table');
    var $data = $table.bootstrapTable('getRowByUniqueId',$barCode);
    var ul = $("#"+thisId).siblings('ul');
    if(ul.css("display")=="none"){
        ul.slideDown("fast");
    }else{
        ul.slideUp("fast");
    }
    $.postJson(YOUNG_DOMAIN + "/coo/v1/qrySuppliLstByPrdInfo.do", {barCode:$barCode,prov:$prov,city:$city}, function(data) {
        var $rows = data.rows;
        if (data.rspCd == "00000") {
            ul.empty();
            for(var i in $rows) {
                var $options = "<li><a href='#' rel='"+ $rows[i].suppliNo +"'>" + $rows[i].suppliNm + "</a></li>";
                ul.append($options);
            }
        }else{
            layer.msg('失败：'+data.rspCd+','+data.rspInf);
        }
        $("#dropdown ul ").on('click','li a',function(){
            var txt = $(this).text();
            $(this).parents('ul').prev('p').html(txt);
            var value = $(this).attr("rel");
            $(this).parents('ul').hide();
            var inx=$(this).parents('li').index();
            console.log(inx);
            $data.suppliNm = $rows[inx].suppliNm;
            $table.bootstrapTable('updateByUniqueId', {id: $barCode, row: $data});
            updMergeData($data.barCode,"suppliNo",$rows[inx].suppliNo);
        });
    });
}

function handel(res) {
    return res;
}

function openTab(tabs){
    var tabObj = new Object();
    var ordNo = $(tabs).text();
    tabObj.dataId = "shop/bills/shopordrdetail.html?ordNo="+ordNo;
    tabObj.desc = "门店订单明细";
    parent.buildTab(tabObj.dataId, tabObj.dataId, tabObj.desc, tabObj.dataId, true, true);
}