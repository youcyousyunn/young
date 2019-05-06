var sort = "deparNo"; //排序列名
var sortName = "desc"; //排位命令（desc，asc）
pagePost(requestObj);

$(function() {
    $('#action_table').on("click", "button[name='upd-deparpost']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        var $nodeno = $(this).data("nodeno");
        $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/edit-deparpost.html", function(data) {
            layer.open({
                title: '修改审核部门或者岗位',
                type: 1,
                area: ['35%', 'auto'],
                skin: 'layui-layer-rim',
                shadeClose: false,
                //点击遮罩关闭
                content: data,
                btn: ['修改', '取消'],
                yes: function(index) {
                    requestObj.actionNo=$uniqueid;
                    requestObj.nodeNo=$nodeno
                    requestObj.deparNo=$('#deparSel').val();
                    requestObj.postId=$('#postSel').val();
                    $.postJson(YOUNG_DOMAIN + "/sys/v1/updFlowAction.do", requestObj, function(data) {
                        $('#action_table').bootstrapTable('refresh');
                        if (data.rspCd == "00000") {
                            layer.msg('修改成功');
                            layer.close(index);
                        }else{
                            layer.msg('修改失败：'+data.rspCd+','+data.rspInf);
                            layer.close(index);
                        }
                    });
                },
                success: function(layero, index){
                    initDepart(requestObj);
                    selectPost('');
                }
            });
        });
    });
});

/**
 * 初始化部门下拉列表
 */
function initDepart(requestObj){
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            for (var int = 0; int < data.deptments.length; int++) {
                var innerHtml = '<option value ="'+data.deptments[int].deparNo+'">'+data.deptments[int].deparNm+'</option>';
                $('#deparSel').append(innerHtml);
            }
        }else{
            layer.msg('部门列表查询失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

/**
 * 初始化岗位下拉列表
 */
function selectPost(obj){
    requestObj.deparNo=$(obj).val();
    $.postJson(YOUNG_DOMAIN + "/deptpost/v1/find.do", requestObj, function(data) {
        if (data.rspCd == "00000") {
            $('#postSel').empty();
            $('#postSel').append('<option value ="">--所有--</option>');
            for (var int = 0; int < data.posts.length; int++) {
                var innerHtml = '<option value ="'+data.posts[int].postId+'">'+data.posts[int].postNm+'</option>';
                $('#postSel').append(innerHtml);
            }
        }else{
            layer.msg('岗位列表查询失败：'+data.rspCd+','+data.rspInf);
        }
    });
}

function pagePost(requestObj) {
    // 初始化table
    $('#action_table').bootstrapTable({
        url: YOUNG_DOMAIN + "/sys/v1/qryFlowActionLst.do",
        //请求后台的URL（*）
        dataType: "json",
        method: 'post',
        //请求方式（*）
        toolbar: '#toolbar',
        //工具按钮用哪个容器
        striped: true,
        //是否显示行间隔色
        cache: false,
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,
        //是否显示分页（*）
        sortable: true,
        //是否启用排序
        sortName: "suppliNo",
        sortOrder: "desc",
        queryParamsType:"custom",
        queryParams: queryParams,
        //传递参数（*）
        sidePagination: "server",
        //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,
        //初始化加载第一页，默认第一页
        pageSize: 10,
        //每页的记录行数（*）
        pageList: [10, 25, 50, 100],
        //可供选择的每页的行数（*）
        search: true,
        //是否显示表格搜索
        strictSearch: true,
        showColumns: true,
        //是否显示所有的列
        showRefresh: true,
        //是否显示刷新按钮
        minimumCountColumns: 2,
        //最少允许的列数
        clickToSelect: true,
        //是否启用点击选中行
        uniqueId: "suppliNo",
        //每一行的唯一标识，一般为主键列
        showToggle: true,
        //是否显示详细视图和列表视图的切换按钮
        cardView: false,
        //是否显示详细视图
        detailView: false,
        //是否显示父子表
        responseHandler: handel,
        idField: "suppliNo",
        iconSize: "outline",
        icons: {
            refresh: "glyphicon-repeat",
            toggle: "glyphicon-list-alt",
            columns: "glyphicon-list"
        },
        columns: [{
            field: 'index',
            checkbox: true
        },
        {
            field: "actionNo",
            title: "动作编号"
        },
        {
            field: 'nodeNo',
            title: '节点号'
        },
        {
            field: 'actionNm',
            title: '动作名称'
        },
        {
            field: "upStep",
            title: "上一节点"
        },
        {
            field: "downStep",
            title: "下一节点"
        },
        {
            field: "deparNm",
            title: "部门名称"
        },
        {
            field: "postNm",
            title: "岗位名称"
        },
        {
            field: "oper",
            title: "操作",
            clickToSelect : false,
            formatter : function(value, row, index) {
                var edit_button = '<button name="upd-deparpost" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                        + row.actionNo+ ' data-nodeno='+row.nodeNo+'><i class="glyphicon glyphicon-edit"></i>修改部门或者岗位</button>';
                return edit_button;
            }
        }],
        silent: true,
        //刷新事件必须设置
        formatLoadingMessage: function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches: function() { //没有匹配的结果
            return '无符合条件的记录';
        },
        onLoadError: function(data) {
            $('#suppli_table').bootstrapTable('removeAll');
        },
        onDblClickRow: function(row) {
            console.log(row);
        }
    });
}
function handel(res) {
    console.log(res);
    return res;
}

function queryParams(params) { // 参数传递
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize : params.pageSize, // 页面大小
        currentPage : params.pageNumber - 1, // 页码
        sort : "" == sort ? params.sortName : sort, // 排序列名
        sortName : params.sortOrder,
        actionNm : params.searchText
        // 排位命令（desc，asc）
    };
    return temp;
    
}

