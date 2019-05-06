
$(function() {
    var $table = $('#products_table');
    initCitySelections($table);
    initProducsTable($table);
    
});

function initProducsTable($table){
	// 初始化table
	$table.bootstrapTable({
		url : YOUNG_DOMAIN + "/product/v1/qryProductlst.do", // 请求后台的URL（*）
		dataType : "json",
		method : 'post', // 请求方式（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortName : "barCode",
		sortOrder : "desc",
		queryParamsType : "custom",
		queryParams : queryParams,// 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : true,
		showColumns : true, // 是否显示所有的列
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		uniqueId : "barCode", // 每一行的唯一标识，一般为主键列
		showToggle : true, // 是否显示详细视图和列表视图的切换按钮
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
		columns : [
		           {
		        	   field : "index",
		        	   checkbox : true
		           },
		           {
		        	   field : 'barCode',
		        	   title : '商品条码',
		        	   sortable : true
		           },
		           {
		        	   field : 'prdNm',
		        	   title : '商品名称',
		        	   sortable : true
		           },
		           {
		        	   field : "sourcSpec",
		        	   title : "采购规格",
		        	   sortable : true
		           },
		           {
		        	   field : "sourcPrice",
		        	   title : "采购价格",
		        	   sortable : true
		           },
		           /*{
                        field : "whsleSpec",
                        title : "批发规格",
                        sortable : true
                    },*/
		           {
		        	   field : "whslePrice",
		        	   title : "批发价格",
		        	   sortable : true
		           },
		           {
		        	   field : "whlseType",
		        	   title : "门店类型",
		        	   formatter : function(value, row, index){
		        		   switch(value){
		        		   case "JOIN":
		        			   value = "加盟";
		        			   break;
		        		   case "SELF":
		        			   value = "自营";
		        			   break;
		        		   default:
		        			   value = "";
		        		   break;
		        		   }
		        		   return value;
		        	   }
		           },
		           {
		        	   field : "suppliNm",
		        	   title : "供应商名称",
		        	   sortable : true
		           }/*,
                    {
                        title : "操作",
                        width : "10%",
                        clickToSelect : false,
                        formatter : function(value, row, index) {
                            var edit_button = '<button name="edit-plan" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                                    + row.shopNo
                                    + '><i class="glyphicon glyphicon-plus-sign"></i> 编辑</button>';
                            return edit_button;
                        }
                    }*/],
                    silent : true, // 刷新事件必须设置
                    formatLoadingMessage : function() {
                    	return "请稍等，正在加载中...";
                    },
                    formatNoMatches : function() { // 没有匹配的结果
                    	return '无符合条件的记录';
                    },
                    onLoadError : function(data) {
                    	$('#reportTable').bootstrapTable('removeAll');
                    },
                    onSort : function(name, order) {
                    }
	});
	var $toolbar = $("#toolbar");
}
function handel(res) {
    console.log(res);
    return res;
}

/**
 * 导出商品总库信息
 */
$('#products_download').click(function(){
	var search = $("#search").val().trim();
	var city = $("#city").val();
    window.location.href = YOUNG_DOMAIN + "/cmm/v1/CommonExport.do?downType=13&city="+city+"&search="+search;
});

/**
 * 初始化城市列表
 */
function initCitySelections($table) {
    $.postJsonAsync(YOUNG_DOMAIN + "/sys/v1/getCityLst.do", {}, function(data) {
        if (data.rspCd == "00000") {
            var $row = data.row;
            // 创建data
            var $data = new Array();
            $data[0] = ({id: '长沙市', text: '长沙市'});
            for(var i in $row) {
                $data.push({id: $row[i].areaNm, text: $row[i].areaNm});
            }
            $("#city").select2({
                data: $data,
                minimumInputLength : 0,
                width : "auto",
                language : "zh-CN",
                placeholder : "请输入城市名称"
            });
            $("#city").on("select2:select", function(e) {
                $table.bootstrapTable("refresh");
            });
        }
    });
}

/**
 * 点击搜索
 */
function searchBtn(){
	var $table = $('#products_table');
	initProducsTable($table);
}

/**
 * 传递参数
 */
function queryParams(params) { // 配置参数
    requestObj.currentPage = params.pageNumber - 1;
    requestObj.pageSize = params.pageSize;
    requestObj.search = params.searchText;
    $("#search").val(params.searchText);
    requestObj.city = $("#city").val();
    return requestObj;
}

