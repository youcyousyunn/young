var $table = $("#ordrapply_table");
var $table1 = $("#orderPost_table");
var $tree = $("#product-type-tree");
var $toolbar = $("#product-type-toolbar");
var $localStorage = window.localStorage;
var $today=new Date();
var $week = "0";
$(function() {
    requestObj.sort = "city"; // 排序列名
    requestObj.sortName = "desc";// 排位命令（desc，asc）
    showShopInfo();
    pagePost(requestObj);
    $('.type-trees').css({
	    "height":$("body").height()-60,
	    "overflow":"auto",
	});
    //提交
    $('.addBtn').on('click', function() {
    	var products = getTmpData();
    	if (null == products) {
    		layer.msg("您还未选中任何产品，请先选择产品后下单");
    		return;
    	}
        layer.open({
            title : '订单预览',
            type : 1,
            zIndex : 2000,
            area : [ '98%', '90%' ],
            maxmin: true,
            skin : 'layui-layer-rim',
            shadeClose : false, // 点击遮罩关闭
            content : $('#ordrAddDiv').show(),
            btn : [ '确定下单', '关闭' ],
            success: function() {
            	$table1.bootstrapTable('destroy');
            	orderPost(products);
            },
            yes : function(index) {
            	var week = $today.getDay()+"";
            	if ("0" != $week) {
            		if (week != $week) {
                		layer.msg("注意！预包装商品只有周"+$week+"才能下单！");
                		return;
                	}
            	}
            	
                // 此处调用添加方法
                requestObj.ordrTyp='01';
                requestObj.ordCls='NORMAL';//普通订单
                requestObj.shopOrdrPrdLst=$table1.bootstrapTable('getData');
                //此处调用添加方法
                $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/shopOrdrApply.do", requestObj, function(data) {
                    if (data.rspCd == "00000") {
                        layer.msg('添加成功');
                        removeTreeNum();
                        parent.closeTab();
                        //刷新表格
//                      window.location.reload();//刷新当前页面
//                      layer.close(index);
                    }else{
                        layer.msg('添加失败：'+data.rspCd+','+data.rspInf);
                    }
               });
            },btn2: function(index, layero){
//            	window.location.reload();//刷新当前页面
            },cancel: function(index) {
//          	window.location.reload();//刷新当前页面
            }
        });
    });
    
    
    $('#rep_data').on('click',function(){
         $('#rep_data').val('');
    })
    //下单预览自动补全窗口
    $('#rep_data').autocomplete({
        hint : false,
        autoselect:true,
        openOnFocus:true
    },[{
        source : function(request, response) {
            requestObj.prov = $('#prov').val();
            requestObj.city = $('#city').val();
            requestObj.shopTyp = $('#shopTyp').val();
            requestObj.shopNo = $('#shopNo').val();
            requestObj.serchTxt = request;
            requestObj.classId = '';
            requestObj.classTyp = 'preck';
            $.postJson(YOUNG_DOMAIN + "/coo/v1/qryStohAllPrdLst.do",requestObj, function(data) {
                if (data.rspCd == "00000") {
                    var prdArry = new Array();
                    var prdArrys = data.rows;
                    // 处理前端展示内容
                    for (var i = 0; i < prdArrys.length; i++) {
                        prdArry.push({
                            value : prdArrys[i].barCode +' '+ prdArrys[i].prdNm,
                            barCode : prdArrys[i].barCode,
                            classId:prdArrys[i].classId,
                            prdNm : prdArrys[i].prdNm,
                            brandNm:prdArrys[i].brandNm,
                            prdSts:prdArrys[i].prdSts,
                            nowStockNum:prdArrys[i].nowStockNum,
                            lastWeekSellNum:prdArrys[i].lastWeekSellNum,
                            nowSellNum:prdArrys[i].nowSellNum,
                            whsleSpec : prdArrys[i].whsleSpec,
                            whsleUnit : prdArrys[i].whsleUnit,
                            shopUnit:prdArrys[i].shopUnit,
                            whslePrice : prdArrys[i].whslePrice,
                            whsleCnt : prdArrys[i].whsleCnt,
                            tax : prdArrys[i].tax,
                            ordCnt : prdArrys[i].ordCnt,
                            updDt : prdArrys[i].updDt,
                            isApart:prdArrys[i].isApart
                        });                        
                    }
                    response(prdArry);
                }else{
                	layer.msg('获取失败：'+data.rspCd+','+data.rspInf);
                }
            });
        }
    }]).on('autocomplete:selected',function(event, suggestion, dataset) {
        var row = new Object();
        row.barCode=suggestion.barCode;
        row.classId=suggestion.classId;
        row.prdNm=suggestion.prdNm;
        row.brandNm=suggestion.brandNm;
        row.prdSts=suggestion.prdSts;
        row.nowStockNum=suggestion.nowStockNum;
        row.lastWeekSellNum=suggestion.lastWeekSellNum;
        row.nowSellNum=suggestion.nowSellNum;
        row.whsleSpec=suggestion.whsleSpec;
        row.whsleUnit=suggestion.whsleUnit;
        row.shopUnit=suggestion.shopUnit;
        row.whslePrice=suggestion.whslePrice;
        row.shopPrice=suggestion.shopPrice;
        row.whsleCnt=suggestion.whsleCnt;
        row.tax=suggestion.tax;
        row.ordCnt=suggestion.whsleCnt;
        row.isApart=suggestion.isApart;
        addDataForShop(row);
        $('#rep_data').val('');
    });
    
    // 下单页添加
    $('#rep_data1').on('click',function(){
         $('#rep_data1').val('');
    });
    
    // 自动补全窗口
    $('#rep_data1').autocomplete({
        hint : false,
        autoselect:true,
        openOnFocus:true
    },[ {
        source : function(request, response) {
            requestObj.prov = $('#prov').val();
            requestObj.city = $('#city').val();
            requestObj.shopTyp = $('#shopTyp').val();
            requestObj.shopNo = $('#shopNo').val();
            requestObj.serchTxt = request;
            requestObj.classId = '';
            requestObj.classTyp = 'preck';
            $.postJson(YOUNG_DOMAIN + "/coo/v1/qryStohAllPrdLst.do",requestObj, function(data) {
                if (data.rspCd == "00000") {
                    var prdArry = new Array();
                    var prdArrys = data.rows;
                    // 处理前端展示内容
                    for (var i = 0; i < prdArrys.length; i++) {
                        prdArry.push({
                            value : prdArrys[i].barCode +' '+ prdArrys[i].prdNm,
                            barCode : prdArrys[i].barCode,
                            classId:prdArrys[i].classId,
                            prdNm : prdArrys[i].prdNm,
                            brandNm:prdArrys[i].brandNm,
                            prdSts:prdArrys[i].prdSts,
                            nowStockNum:prdArrys[i].nowStockNum,
                            lastWeekSellNum:prdArrys[i].lastWeekSellNum,
                            nowSellNum:prdArrys[i].nowSellNum,
                            whsleSpec : prdArrys[i].whsleSpec,
                            whsleUnit : prdArrys[i].whsleUnit,
                            shopUnit:prdArrys[i].shopUnit,
                            whslePrice : prdArrys[i].whslePrice,
                            shopPrice : prdArrys[i].shopPrice,
                            whsleCnt : prdArrys[i].whsleCnt,
                            tax : prdArrys[i].tax,
                            ordCnt : prdArrys[i].ordCnt,
                            updDt : prdArrys[i].updDt,
                            isApart:prdArrys[i].isApart
                        });                        
                    }
                    response(prdArry);
                }else{
                	layer.msg('获取失败：'+data.rspCd+','+data.rspInf);
                }
            });
        }
    } ]).on('autocomplete:selected',function(event, suggestion, dataset) {
        var row = new Object();
        row.barCode=suggestion.barCode;
        row.classId=suggestion.classId;
        row.prdNm=suggestion.prdNm;
        row.brandNm=suggestion.brandNm;
        row.prdSts=suggestion.prdSts;
        row.nowStockNum=suggestion.nowStockNum;
        row.lastWeekSellNum=suggestion.lastWeekSellNum;
        row.nowSellNum=suggestion.nowSellNum;
        row.whsleSpec=suggestion.whsleSpec;
        row.whsleUnit=suggestion.whsleUnit;
        row.shopUnit=suggestion.shopUnit;
        row.whslePrice=suggestion.whslePrice;
        row.shopPrice=suggestion.shopPrice;
        row.whsleCnt=suggestion.whsleCnt;
        row.tax=suggestion.tax;
        row.ordCnt=suggestion.ordCnt;
        row.isApart=suggestion.isApart;
        addDataForShop1(row);
        $('#rep_data1').val('');
    });
    
    
    //删除操作
    $table1.on("click", "button[name='delete-barCode']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        var $data = $table1.bootstrapTable('getRowByUniqueId', $uniqueid);
        var classid=$data.classId;
        divNum(classid);
        deleteTmpData($data);
        $table1.bootstrapTable('removeByUniqueId', $uniqueid);
    });
    
 	//初始化树形菜单  选中事件
    $tree.on("select_node.jstree", function(e, nodes) {
        var $node = nodes.node;
        var $id = $node.id;
      	var type=$node.type;
      	//单击打开
      	$tree.jstree("toggle_node", "#"+$id);
        $("#product-type").val($id);
        var products, storeage = window.localStorage, products = storeage.getItem("shopbarCodeLst");
		// 获取产品集合
		products = JSON.parse(products);
        $table.bootstrapTable('refresh');
    });
    
    // 更变事件
    $tree.on("open_node.jstree", function(e, $nodes) {
         var $treeNum = JSON.parse(window.localStorage.getItem("shoptreeNum"));
         var $node = $nodes.node;
         var $childens = $node.children_d;
         var $ref = $tree.jstree(true);
         for(var i in $childens) {
             var $id = $childens[i];
             var $span=$('.'+$id);
             var $num = $treeNum[$id];
             $span.text($num);
         }
    });
});

//状态变更
function xgdata($data){
	if($data.ordCnt>0){
		divNum($data.classId);
   }
	deleteTmpData($data);
	$data.ordCnt = 0;
}
//自动补全添加
function addDataForShop1(row){
    var rowObj = $table.bootstrapTable('getRowByUniqueId', row.barCode);
    if(null==rowObj){
    	if(row.prdSts=="F"||row.prdSts=="W"){
    		layer.msg('当前商品状态异常不能下单!', {
			  time: 1500,
			})
    		return;
    	}
        $table.bootstrapTable('prepend', addtabData(row));
    }
}
//下单预览自动补全添加
function addDataForShop(row){
    var rowObj = $table1.bootstrapTable('getRowByUniqueId', row.barCode);
    if(null==rowObj){
    	if(row.prdSts=="F"||row.prdSts=="W"){
    		layer.msg('当前商品状态异常不能下单!', {
			  time: 1500,
			})
    		return;
    	}
        $table1.bootstrapTable('prepend', addtabData(row));
        var $data = $table1.bootstrapTable('getRowByUniqueId', row.barCode);
		saveTmpData($data);/*缓存*/
		addNum($data.classId);/*数字加一*/
		$table.bootstrapTable('refresh');
    }
}
function addtabData(row) {
    var rows = [];
    rows.push({
        barCode: row.barCode,
        classId:row.classId,
        prdNm: row.prdNm,
        brandNm:row.brandNm,
        prdSts:row.prdSts,
        lastWeekSellNum:row.lastWeekSellNum,
        nowSellNum:row.nowSellNum,
        nowStockNum:row.nowStockNum,
        whsleSpec: row.whsleSpec,
        whsleUnit:row.whsleUnit,
        shopUnit:row.shopUnit,
        whslePrice: row.whslePrice,
        shopPrice: row.shopPrice,
        ordCnt:row.ordCnt,
        whsleCnt:row.whsleCnt,
        tax:row.tax,
        isApart:row.isApart,
    });
    return rows;
}

/**
 * 获取门店信息
 */
function showShopInfo(){
    $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/qryShopInfoByUsr.do",requestObj, function(data) {
        if (data.rspCd == "00000") {
            $('#shopNm').val(data.shopNm);
            $('#shopNo').val(data.shopNo);
            $('#prov').val(data.prov);
            $('#city').val(data.city);
            $('#shopTyp').val(data.shopType);
            initTree(); 
            qryShopOrderPlan(data.shopNo); 
        }
    });
}

/**
 * 获取门店下单周计划
 */
function qryShopOrderPlan(shopNo){
	var requestObj = {
			shopNo : shopNo
	};
    $.postJsonAsync(YOUNG_DOMAIN + "/shop/v1/qryShopOrderPlan.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
        	$week = data.normalPlan;
        	if ("0" != $week){
        		$("#deliverTxt").val("注意！预包装商品只有周"+$week+"才能下单！");
        	} else{
        		$("#deliverTxt").remove();
        	}
        }
    });
}

/**
 * 查询产品信息
 */
function pagePost(requestObj) {
    $table.bootstrapTable({
	  	url : YOUNG_DOMAIN + "/coo/v1/qryStohAllPrdLst.do", // 请求后台的URL（*）
        dataType : "json",
        method : 'post', // 请求方式（*）
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : false, // 是否显示分页（*）
        sortable : true, // 是否启用排序
        sortName : "barCode",
        sortOrder : "desc",
        queryParamsType : "custom",
        height: $("body").height()-80,
        queryParams : function(params) {
            return {
                currentPage : params.pageNumber - 1,
                pageSize : params.pageSize,
                prov : $('#prov').val(),
                city : $('#city').val(),
                shopTyp : $('#shopTyp').val(),
                shopNo : $('#shopNo').val(),
                classId : $("#product-type").val()
            }
//            FIXME 通用查询条件会带往后台，
//            如果遇上不是使用iframe加载的页面而是ajax.load进来的页面则所有页面会带上该条件导致查询不到数据
//            requestObj.currentPage = params.pageNumber - 1;
//            requestObj.pageSize = params.pageSize;
//			  requestObj.prov = $('#prov').val();
//            requestObj.city = $('#city').val();
//            requestObj.shopTyp = $('#shopTyp').val();
//            requestObj.shopNo = $('#shopNo').val();
//            requestObj.classId = $("#product-type").val();
//			  return requestObj;
        },// 传递参数（*）
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
//      pageNumber : 1, // 初始化加载第一页，默认第一页
//      pageSize : 100, // 每页的记录行数（*）
//      pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
        search : false, // 是否显示表格搜索
        strictSearch : true,
        showColumns : false, // 是否显示所有的列
        showRefresh : false, // 是否显示刷新按钮
        minimumCountColumns : 2, //最少允许的列数
        clickToSelect : false, //是否启用点击选中行
        uniqueId : "barCode", //每一行的唯一标识，一般为主键列
        showToggle : false, //是否显示详细视图和列表视图的切换按钮
        cardView : false, //是否显示详细视图
        detailView : false, //是否显示父子表
        showFooter:true,
        responseHandler : function(res) {
			//表格数据变更
            var $products = JSON.parse(window.localStorage.getItem("shopbarCodeLst"));
        	if (null == $products) {
        		return res;
        	}
        	var $rows = res.rows;
        	for(var i in $rows) {
        		var $row = $rows[i];
        		var $barCode = $row.barCode;
        		var $p = $products[$barCode];
    			if($p){
      				$row.ordCnt = $p.ordCnt;
    			}
        	}
        	return res;
        },
        idField : "barCode",
        iconSize : "outline",
        icons : {
            refresh : "glyphicon-repeat",
            toggle : "glyphicon-list-alt",
            columns : "glyphicon-list"
        },
      	columns : [/*{
			field : 'index',
			title : '序号',
			formatter : function(value, row, index) {
			return index+1;
			}
		},*/{
            field : 'barCode',
            title : '条码',
        }, {
            field : "prdNm",
            title : "品名",
            className : "type-td",
        	footerFormatter: function (value) {
        		var trLst = $("#ordrapply_table tbody").children("tr");
        		var tolOrdNum = 0;
        		trLst.each(function(){
	        		var ordNum = $(this).find("input[class='orders2']").val();
	        		if(ordNum > 0){
	        			tolOrdNum ++;
	        		}
        		});
        		var footers='<div id="tolOrdNum" class="inputbox clearfix">'
                    +"总条码数：" + tolOrdNum;
                    +'</div>'
        		return footers;
	    	}
        }, {
            field : "brandNm",
            title : "品牌",
        }, {
            field : "whsleSpec",
            title : "发货<br/>规格",
        }, {
            field : "isApart",
            title : "是否<br/>可拆",
            formatter : function(value, row, index){
                switch (value) {
                case "Y":
                    value = "是";
                    break;
                case "N":
                    value = "否";
                    break;
                }
                return value;
            }
        }, {
            field : "whsleUnit",
            title : "发货<br/>单位"
        }, {
            field : "whslePrice",
            title : "批发<br>价格"
        }, {
            field : "shopUnit",
            title : "零售<br/>单位"
        }, /*{
            field : "nowStockNum",
            title : "当前<br/>库存",
            formatter:function(value,row,index){
            	return parseInt(row.nowStockNum);
            }
        },*/ {
            field : "lastWeekSellNum",
            title : "上一周<br/>期销量",
            formatter:function(value,row,index){
            	return parseInt(row.lastWeekSellNum);
            } 
        }, {
            field : "nowSellNum",
            title : "本周<br/>销量",
            formatter:function(value,row,index){
            	return parseInt(row.nowSellNum);
            }
        }, {
            field : "ordCnt",
            title : "下单数量",
            formatter : function(value, row, index) {
            	var sum=row.ordCnt/row.whsleCnt;
            	if (!(/(^[0-9]\d*$)/.test(sum))){
        			var sums=sum.toFixed(2);
        		}else{
        			var sums=sum;
        		}
                var inputbox='<div class="inputbox clearfix">'
                             +'<span data-unid="'+row.barCode+'"  class="input-reduce">-</span>'
                             +'<input class="orders2" type="text" data-unid="'+row.barCode+'" value="'+sums+'"/>'
                             +'<span data-unid="'+row.barCode+'" class="input-increase">+</span>'
                             +'<span class="inputboxrg">('+row.whsleUnit+')</span>'
                             +'</div>'
                return inputbox;
            }
        }, {
        	field:"totalPrice",
        	title:"单品总价",
        	align:"center",
        	formatter:function(value,row,index){
        		var sum=row.ordCnt;
        		var totalPrice=sum*row.whslePrice;
                var inputbox='<div class="inputbox clearfix" style="min-width:60px;">'
                             +'<input class="orders-total" type="text" data-unid="'+row.barCode+'" value="'+totalPrice.toFixed(2)+'" readonly="readonly" style="border:none;width:60px;"/>'
                             +'</div>'
                return inputbox;
        	},
        	footerFormatter: function (value) {
        		if($("#ordrapply_table tbody").find("tr").hasClass("no-records-found")){
        			var footers='<div>总金额：<span id="allPrice">0.00</span></div>';
        			return footers;
        		}else{
        			var count = 0;
		        	var inputVal=$("#ordrapply_table tbody").find("tr").length;
		        	console.log(inputVal);
		        	for(i=0;i<inputVal;i++){
		        		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
		        		var footers='<div>总金额：<span id="allPrice">'+count.toFixed(2)+'</span></div>';
		        	}
		        	return footers;
        		}
	    	}
        }
    ],
        silent : true, //刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { //没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
            $table.bootstrapTable('removeAll');
        },
        onDblClickRow : function(row) {
            console.log(row);
        },
        onClickRow:function(row, $element){
        },
        onLoadSuccess:function(data) {
        	if(data.rows!=''){
        		var $rows = data.rows;
	        	for(var i in $rows) {
	        		var $row = $rows[i];
	        		var $ordCnts = $row.ordCnt;
	        		var $tr = $table.find("tr[data-uniqueid='" + $row.barCode + "']");
	        		if($ordCnts != 0){
	        			$tr.css("background", "#cceeff");
	        		}
	        		if ($row.checked) {
	        			$tr.css("background", "#cceeff");
	        		}
	        	}
        	}
        }
  	});
    
  	/**
  	 * 减少
  	 */
    $table.on("click", ".input-reduce", function() {
        var $this = $(this);
        var $id = $this.data("unid");
        var $data = $table.bootstrapTable('getRowByUniqueId',
                $id);
        var txtJ;
        var whsleCnt = $data.whsleCnt;
        var classId = $data.classId;
        var txt = $this.siblings('input').val();
        var $tr = $table.find("tr[data-uniqueid='" + $id + "']");
        if (txt == 0) {
            layer.msg("真的不能再少了！");
        } else {
        	txtJ = txt - 1;
            $this.siblings('input').val(txtJ);
            var sum = whsleCnt * txtJ;
            $data.ordCnt = sum;
            var totalPrice=sum*$data.whslePrice;
            $tr.css("background", "#cceeff");
            $this.parents("td").siblings("td").find(".orders-total").val(totalPrice.toFixed(2));
            var count = 0;
	    	var inputVal=$("#ordrapply_table tbody").find("tr").length;
	    	for(i=0;i<inputVal;i++){
	    		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
	    		$("#allPrice").text(count.toFixed(2));
	    	}
        }
        if ($data.ordCnt <= 0) {
            deleteTmpData($data);
        }else{
        	saveTmpData($data);// 数据暂存
        }
        // 数字减一
        if (txt == 1) {
            divNum($data.classId);
        	$tr.css("background", "none");
        }
        
        //计算总条码数
	    var trLst = $("#ordrapply_table tbody").children("tr");
		var tolOrdNum = 0;
		trLst.each(function(){
    		var ordNum = $(this).find("input[class='orders2']").val();
    		if(ordNum > 0){
    			tolOrdNum ++;
    		}
		});
		var footers="总条码数：" + tolOrdNum;
		$("#tolOrdNum").html(footers);
    });
    
	/**
	 * 增加
	 */
	$table.on("click", ".input-increase", function(){
		var $this = $(this);
	    var $id = $this.data("unid");
	    var $data = $table.bootstrapTable('getRowByUniqueId', $id);
	    var whsleCnt = $data.whsleCnt; 
	    var txtJ;
	    var txt=$this.siblings('input').val();
	    var $tr  = $table.find("tr[data-uniqueid='" + $id + "']");
	    txtJ=txt*1 + 1;
	    $this.siblings('input').val(txtJ);
	    var sum=whsleCnt*txtJ;
	    $data.ordCnt = sum;
	    var totalPrice=sum*$data.whslePrice;
	    $this.parents("td").siblings("td").find(".orders-total").val(totalPrice.toFixed(2));
	    var count = 0;
    	var inputVal=$("#ordrapply_table tbody").find("tr").length;
    	for(i=0;i<inputVal;i++){
    		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
    		$("#allPrice").text(count.toFixed(2));
    	}
	    
	    $tr.css("background", "#cceeff");
	    var trLst = $("#ordrapply_table tbody").children("tr");
		var tolOrdNum = 0;
		trLst.each(function(){
    		var ordNum = $(this).find("input[class='orders2']").val();
    		if(ordNum > 0){
    			tolOrdNum ++;
    		}
		});
		var footers="总条码数：" + tolOrdNum;
		$("#tolOrdNum").html(footers);
	    
        saveTmpData($data);//数据暂存
        //数字加一
        if(txt <= 0){
            addNum($data.classId);
        }  

	})
	
	/**
	 * 直接修改
	 */
	$table.on("blur", ".orders2", function(){
		var $this = $(this);
	    var $id = $this.data("unid");
	    var $data = $table.bootstrapTable('getRowByUniqueId', $id);
	    var whsleCnt = $data.whsleCnt;
	    var $value=$this.val();
	    var $tr  = $table.find("tr[data-uniqueid='" + $id + "']");
	    if(isNaN($value)){
	        layer.msg("请输入数字！");
	        // $table.bootstrapTable('load','$data.ordCnt');
	        $this.val('0');
	        $tr.css("background", "none");
	        xgdata($data);
	        return;
	    }
	    if($value<0){
	        layer.msg("最小订单量不能为0！");
	        // $table.bootstrapTable('load','$data.ordCnt');
	        $this.val('0');
	        $tr.css("background", "none");
	        xgdata($data);
	        return;
	    }
	    if($value==0){
	        $tr.css("background", "none");
	        $this.parents("td").siblings("td").find(".orders-total").val(0);
	    	var count = 0;
	    	var inputVal=$("#ordrapply_table tbody").find("tr").length;
	    	for(i=0;i<inputVal;i++){
	    		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
	    		$("#allPrice").text(count);
	    	}
	        return;
	    }
	    var isApart=$data.isApart;
        if(isApart=='Y'){
			if(!(/^\d{1,10}(\.\d{1,2})?$/.test($value))){
				layer.msg("此商品下单数只支持两位小数点！");
				$this.val('0');
		        $tr.css("background", "none");
		        xgdata($data);
        		return;
			}
			var sum=whsleCnt*$value;
	    	$data.ordCnt = sum.toFixed(2);
	    	var totalPrice=$data.ordCnt*$data.whslePrice;
	    	$this.parents("td").siblings("td").find(".orders-total").val(totalPrice.toFixed(2));
	    	var count = 0;
	    	var inputVal=$("#ordrapply_table tbody").find("tr").length;
	    	for(i=0;i<inputVal;i++){
	    		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
	    		$("#allPrice").text(count.toFixed(2));
	    	}
			$tr.css("background", "#cceeff");
        }else{
        	var sum=whsleCnt*$value;
        	if (!(/(^[0-9]\d*$)/.test($value))){
        		layer.msg("此商品下单数不支持小数！");
        		$this.val('0');
		        $tr.css("background", "none");
		        xgdata($data);
        		return;
        	}
        	var sum=whsleCnt*$value;
	    	$data.ordCnt = sum.toFixed(2);
	    	var totalPrice=$data.ordCnt*$data.whslePrice;
	    	$this.parents("td").siblings("td").find(".orders-total").val(totalPrice.toFixed(2));
	    	var count = 0;
	    	var inputVal=$("#ordrapply_table tbody").find("tr").length;
	    	for(i=0;i<inputVal;i++){
	    		count+=+$("#ordrapply_table tbody tr").eq(i).find(".orders-total").val();
	    		$("#allPrice").text(count.toFixed(2));
	    	}
	    	$tr.css("background", "#cceeff");
        }
	    var $oldData = getTmpData($id);
	    var $selected = false;
	    if ($oldData) {
	        if ($oldData.ordCnt > 0) {
	            $selected = true;
	        }
	    }
	    if ($data.ordCnt <= 0) {
	    	deleteTmpData($data);
	    }else{
	    	saveTmpData($data);//数据暂存
	    }
        //判断sku
        if(!$selected){
        	if($value>0){
        	    addNum($data.classId);
        	}	
        }else{
        	if($value<=0){
        		divNum($data.classId);
        		$tr.css("background", "none");
		    }
        }
        
        /**
         * 计算总条码数
         */
	    var trLst = $("#ordrapply_table tbody").children("tr");
		var tolOrdNum = 0;
		trLst.each(function(){
    		var ordNum = $(this).find("input[class='orders2']").val();
    		if(ordNum > 0){
    			tolOrdNum ++;
    		}
		});
		var footers="总条码数：" + tolOrdNum;
		$("#tolOrdNum").html(footers);
	})
	
}

/**
 * 下单预览表格
 */
function orderPost(data){
    $table1.bootstrapTable({
    	data:data,
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : false, // 是否显示分页（*）
        sortable : true, // 是否启用排序
        sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
        search : false, // 是否显示表格搜索
        strictSearch : true,
        showColumns : false, // 是否显示所有的列
        showRefresh : false, // 是否显示刷新按钮
        minimumCountColumns : 2, //最少允许的列数
        uniqueId : "barCode", //每一行的唯一标识，一般为主键列
        showToggle : false, //是否显示详细视图和列表视图的切换按钮
        cardView : false, //是否显示详细视图
        detailView : false, //是否显示父子表
        showFooter:true,
        idField : "barCode",
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
		},{
            field : 'barCode',
            title : '条码',
        }, {
            field : "prdNm",
            title : "品名",
            footerFormatter: function (value) {
        		if($("#orderPost_table tbody").find("tr").hasClass("no-records-found")){
        			var footers="总条码数："+0;
        			return footers;
        		}else{
        			var trLength=$("#orderPost_table tbody").find("tr").length;
		        	var footers="总条码数："+trLength;
		        	return footers;
        		}
	    	}
        }, {
            field : "brandNm",
            title : "品牌",
        }, {
            field : "prdSts",
            title : "商品<br/>状态",
            formatter : function(value, row, index){
                switch (value) {
                case "S":
                    value = "正常";
                    break;
                case "F":
                    value = "失效";
                    break;
                case "U":
                    value = "上架中";
                    break;
                case "D":
                    value = "下架中";
                    break;
                }
                return value;
            }
        }, {
            field : "whsleSpec",
            title : "发货<br/>规格",
        }, {
            field : "isApart",
            title : "是否<br/>可拆",
            formatter : function(value, row, index){
                switch (value) {
                case "Y":
                    value = "是";
                    break;
                case "N":
                    value = "否";
                    break;
                }
                return value;
            }
        }, {
            field : "whsleUnit",
            title : "发货<br/>单位"
        }, {
            field : "whslePrice",
            title : "批发<br/>价格"
        }, /*{
            field : "shopPrice",
            title : "零售<br/>单价"
        }, */{
            field : "shopUnit",
            title : "零售<br/>单位"
        }, /*{
            field : "nowStockNum",
            title : "当前<br/>库存",
            formatter:function(value,row,index){
            	return parseInt(row.nowStockNum);
            }
        },*/ {
            field : "lastWeekSellNum",
            title : "上一周期<br/>销量",
            formatter:function(value,row,index){
            	return parseInt(row.lastWeekSellNum);
            }
        }, {
            field : "nowSellNum",
            title : "本周<br/>销量",
            formatter:function(value,row,index){
            	return parseInt(row.nowSellNum);
            }
        }, {
            field : "ordCnt",
            title : "下单数量",
            formatter : function(value, row, index) {
            	var sum=row.ordCnt/row.whsleCnt; 
            	if (!(/(^[0-9]\d*$)/.test(sum))){
        			var sums=sum.toFixed(2);
        		}else{
        			var sums=sum;
        		}
                var inputbox='<div class="inputbox clearfix">'
                             +'<span data-unid="'+row.barCode+'"  class="input-reduce">-</span>'
                             +'<input class="orders2" type="text" data-unid="'+row.barCode+'" value="'+sums+'" />'
                             +'<span data-unid="'+row.barCode+'" class="input-increase">+</span>'
                             +'<span class="inputboxrg">('+row.whsleUnit+')</span>'
                             +'</div>'
                return inputbox;
            }
        },{
            field : "deliverCnt",
            title : "零售单位数量",
            formatter : function(value, row, index){
            	var ordcnt=row.ordCnt;
            	var sum='<div>'
	                    +'<span>'+parseInt(ordcnt)+'</span>'
	                    +'<span>('+row.shopUnit+')</span>'
	                    +'</div>'
                return sum;
            }
        }, {
        	field:"totalPrice",
        	title:"单品总价",
        	formatter:function(value,row,index){
        		var sum=row.ordCnt;
        		var totalPrice=sum*row.whslePrice;
                var inputbox='<div class="inputbox clearfix" style="min-width:60px;">'
                             +'<input class="orders-total" type="text" data-unid="'+row.barCode+'" value="'+totalPrice.toFixed(2)+'" readonly="readonly" style="border:none;width:60px;"/>'
                             +'</div>'
                return inputbox;
        	},
        	footerFormatter: function (value) {
        		if($("#orderPost_table tbody").find("tr").hasClass("no-records-found")){
        			var footers='<div>总金额：<span id="allPrice">0.00</span></div>';
        			return footers;
        		}else{
        			var count = 0;
		        	var inputVal=$("#orderPost_table tbody").find("tr").length;
		        	console.log(inputVal);
		        	for(i=0;i<inputVal;i++){
		        		count+=+$("#orderPost_table tbody tr").eq(i).find(".orders-total").val();
		        		var footers='<div>总金额：<span id="allPrice">'+count.toFixed(2)+'</span></div>';
		        	}
		        	return footers;
		        	console.log(footers+"1111");
        		}
	    	}
        }, {
            title : "操作",
            width : "6%",
            clickToSelect : false,
            formatter : function(value, row, index) {
                var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-barCode" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                        + row.barCode
                        + '><i class="glyphicon glyphicon-trash"></i>删除</button>';
                return delete_button;
            }
        }],
        silent : true, //刷新事件必须设置
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { //没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError : function(data) {
        },
        onDblClickRow : function(row) {
            console.log(row);
        }
    });
}

/**
 * 减少
 */
$table1.on("click", ".input-reduce", function(){
	var $this = $(this);
    var $id = $this.data("unid");
    var $data = $table1.bootstrapTable('getRowByUniqueId', $id);
    var txtJ;
    var whsleCnt = $data.whsleCnt; 
    var classId=$data.classId;
    var txt=$this.siblings('input').val();
    var $tr  = $table.find("tr[data-uniqueid='" + $id + "']");
    if(txt==1){
        layer.msg("下单数不能小于1！");
    }else{
       txtJ=txt-1;
       $this.siblings('input').val(txtJ);
       var sum=whsleCnt*txtJ;
       $data.ordCnt = sum;
    }
    if ($data.ordCnt <= 0) {
    	deleteTmpData($data);
    	return;
    }
    saveTmpData($data);//数据暂存 
    $table.bootstrapTable('refresh');
    $table1.bootstrapTable('load','$data.ordCnt');
});

/**
 * 增加
 */
$table1.on("click", ".input-increase", function(){
	var $this = $(this);
    var $id = $this.data("unid");
    var $data = $table1.bootstrapTable('getRowByUniqueId', $id);
    var whsleCnt = $data.whsleCnt;
    var txtJ;
    var txt=$this.siblings('input').val();
    var $tr  = $table.find("tr[data-uniqueid='" + $id + "']");
    txtJ=txt*1 + 1;
    $this.siblings('input').val(txtJ);
    var sum=whsleCnt*txtJ;
    $data.ordCnt = sum;
    $tr.css("background", "#cceeff");
    saveTmpData($data);//数据暂存 
	$table.bootstrapTable('refresh');
	$table1.bootstrapTable('load','$data.ordCnt');
})

/**
 * 直接修改
 */
$table1.on("blur", ".orders2", function(){
		var $this = $(this);
	    var $id = $this.data("unid");
	    var $data = $table1.bootstrapTable('getRowByUniqueId', $id);
	    var whsleCnt = $data.whsleCnt;
	    var $value=$this.val();
	    if(isNaN($value)){
	        layer.msg("请输入数字！");
	        $table1.bootstrapTable('load','$data.ordCnt');
	        return;
	    }
	    if($value<=0){
	        layer.msg("下单最小订单量不能为0！");
	        $table1.bootstrapTable('load','$data.ordCnt');
	        return;
	    }
	    var isApart=$data.isApart;
	    if(isApart=='Y'){
			if(!(/^\d{1,10}(\.\d{1,2})?$/.test($value))){
				layer.msg("此商品下单数只支持两位小数点！");
				$table1.bootstrapTable('load','$data.ordCnt');
        		return;
			}
        	var sum=whsleCnt*$value;
	    	$data.ordCnt = sum.toFixed(2);
        }else{
        	if (!(/(^[0-9]\d*$)/.test($value))){
        		layer.msg("此商品下单数不支持小数！");
        		$table1.bootstrapTable('load','$data.ordCnt');
        		return;
        	}
        	console.log(whsleCnt)
        	var sum=whsleCnt*$value;
	    	$data.ordCnt = sum.toFixed(2);
        }
	    $table.bootstrapTable('refresh');
	    $table1.bootstrapTable('load','$data.ordCnt');
	    var $oldData = getTmpData($id);
	    var $selected = false;
	    if ($oldData) {
	        if ($oldData.ordCnt > 0) {
	            $selected = true;
	        }
	    }
	    if ($data.ordCnt <= 0) {
	    	deleteTmpData($data);
	    }else{
	    	saveTmpData($data);//数据暂存
	    }
        //判断sku
        if(!$selected){
        	if($value>0){
        	    addNum($data.classId);
        	}	
        }else{
        	if($value<=0){
        		divNum($data.classId);
		    }
        }
})

/**
 * 获取缓存产品
 */
function getTmpData($barCode) {
	var $products = JSON.parse(window.localStorage.getItem("shopbarCodeLst"));
	if ($.isEmptyObject($products)) {
		return null;
	}
	if ($barCode) {
	    return $products[$barCode];
	}
	var $p = new Array();
	for(var i in $products) {
		$p.push($products[i]);
	}
	return $p;
}

/**
 * 删除缓存产品
 */
function deleteTmpData(data) {
	var products, storeage = window.localStorage, products = storeage.getItem("shopbarCodeLst");
	// 获取产品集合
	products = JSON.parse(products);
	if (null != products) {
		delete products[data.barCode];
		storeage.setItem("shopbarCodeLst", JSON.stringify(products));
	}
}

/**
 * 保存缓存产品
 */
function saveTmpData(data){
	var products, storeage = window.localStorage, products = storeage.getItem("shopbarCodeLst");
	// 获取产品集合
	products = JSON.parse(products);
	if (null == products) {
		products = new Object();
	}
	products[data.barCode] = data;
	storeage.setItem("shopbarCodeLst", JSON.stringify(products));
	return;
}

/**
 * 获取树形菜单的数字Map
 */
function getTreeNum() {
    var $temp = JSON.parse($localStorage.getItem("shoptreeNum"));
    return $temp == null ? {} : $temp;
}

/**
 * 减少树形菜单数字
 */
function divNum($classId) {
    var $treeNum = getTreeNum();
    var $ref = $tree.jstree(true);
    // 获取当前选中节点
    var $node = $ref.get_node($classId);
    var $span = $('.' + $classId);
    var spant = $span.text();
    if (spant > 0) {
        spant--;
        $span.text(spant);
        if(spant==0){
        	$span.removeClass("red");
        }
        $treeNum[$classId] = spant;
        var $parents = $node.parents;
        for(var i in $parents) {
            var $parent = $parents[i];
            if ($parent == "#") {
                continue;
            }
            var $parentSpan = $("." + $parent);
            var $spanNum = $parentSpan.text();
            console.log($spanNum)
            if($spanNum==1){
            	 $parentSpan.removeClass("red");
            }
           	$parentSpan.text(--$spanNum);
            $treeNum[$parent] = $spanNum;
        }
        $localStorage.setItem("shoptreeNum", JSON.stringify($treeNum));
    }
}

/**
 * 删除本地缓存
 */
function removeTreeNum() {
    $localStorage.removeItem("shoptreeNum");
    $localStorage.removeItem("shopbarCodeLst");
}

/**
 * 增加树形菜单数字
 */
function addNum($classId) {
    var $treeNum = getTreeNum();
    //改变当前节点
    var $ref = $tree.jstree(true);
    // 获取当前选中节点
    var $node = $ref.get_node($classId);
    var $span=$('.'+$classId);
    var spant=$span.text();
    spant++;
    $span.text(spant);
    $span.addClass("red");
    console.log('span'+$span);
    $treeNum[$classId] = spant;
    var $parents = $node.parents;
    for(var i in $parents) {
        var $parent = $parents[i];
        if ($parent == "#") {
            continue;
        }
        var $parentSpan = $("." + $parent);
        var $spanNum = $parentSpan.text();
        $parentSpan.text(++$spanNum);
        console.log('$parentSpan'+$parentSpan);
        $parentSpan.addClass("red");
        $treeNum[$parent] = $spanNum;
    }
    $localStorage.setItem("shoptreeNum", JSON.stringify($treeNum));
}

/**
 * 初始化树形菜单
 */ 
function initTree() {
    var $tree = $("#product-type-tree");
    var $request = {
        countProduct: true,
        type: ['preck'],
        prov: $("#prov").val(), 
        city: $("#city").val()
    };
    $.postJson(YOUNG_DOMAIN + "/producttype/v1/tree.do", $request, function(data) {
        if (data.rspCd == "00000") {
            var $types = data.productTypes;
            var $temp = JSON.parse($localStorage.getItem("shoptreeNum"));
            var $treeNum = $temp == null ? {} : $temp;
            for(var i in $types) {
                var $num = !$treeNum[$types[i].id] ? 0 : $treeNum[$types[i].id];
                var $id = $types[i].id == "#" ? -1 : $types[i].id;
                var span = "<span class='"+ $id +" red' >" + $num + "</span>";
                var spans = "<span class='"+ $id + "'>" + $num + "</span>";
                if($num>0){
                	$types[i].text = $types[i].text + "("+span+"/" + $types[i].data.count + ")";
                }else{
                	$types[i].text = $types[i].text + "("+spans+"/" + $types[i].data.count + ")";
                }  
            }
            $localStorage.setItem("shoptreeNum", JSON.stringify($treeNum));
            $tree.jstree({ 
                'core' : { 
                    'data' : $types,
                    "check_callback" : true
                },
                types : {
                    "#" : {
                        valid_children : [ 1 ]
                    },
                    1 : {
                        valid_children : [ 2 ]
                    },
                    2 : {
                        valid_children : [ 3 ]
                    },
                    3 : {
                        valid_children : [ 4 ]
                    },
                    4 : {
                        valid_children : []
                    }
                },
                "plugins" : [ "dnd", "types" ]
            });
        }
    });
}