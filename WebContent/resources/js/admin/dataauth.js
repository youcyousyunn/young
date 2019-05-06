$(function() {
    /**
     * 新增数据权限
     */
    $("#add-dataauth").on("click",function() {
        $.get(YOUNG_DOMAIN + "/admin/dialog/add-dataauth.html", function(data) {
            layer.open({
                id : "add-dataauth-dialog",
                type : 1,
                title : "添加数据权限",
                content : data,
                area : [ "70%", "90%" ],
                closeBtn : 1,
                scrollbar : false,
                fix : true,
                move : false,
                shift : 5,
                btn : [ "添加", "取消" ],
                success : function() {
                    initUserSelect();
                },
                yes : function(index) {
                    addDataAuth();
                    layer.close(index);
                }
            });
        });
    });
    
    var $authTable = $('#dataauth_table');
    initAuthTable($authTable);
});

function initUserSelect(){
    $("#userSelect").select2({
        width : "100%"
    });
    /**
     * 只能查出当前用户下面部门的所有用户
     */
    $.postJson(YOUNG_DOMAIN + "/user/v1/all.do", {}, function(response) {
        if (response.rspCd == "00000" && response.userInfos.length > 0) {
            var userInfos = response.userInfos;
            var element = "";
            var $mngerUsrNo = $("#mngerUsrNo");
            var $userSelect = $("#userSelect");
            var $mngerNm = $("#mngerNm");
            element += "<option value=''>请选择</option>";
            var $userInfoslength = userInfos.length;//把length提取出来可以在循环处理大数组时能够避免对性能造成巨大的影响
            for (var i = 0; i < $userInfoslength; i++) {
                element += "<option id='depar" + userInfos[i].usrNo
                        + "' value='" + userInfos[i].usrNo + "'>"
                        + userInfos[i].usrNm + "</option>";
            }
            $("#userSelect").html(element);
            $("#userSelect").on("change", function() {
                $mngerUsrNo.val($(this).select2('data')[0].id);
                $mngerNm.val($(this).select2('data')[0].text);
            });
        }
    });
    
    /**
     * 全选
     */
    $("#checkall").on("click",function() {
        if (this.checked) {
            $("input[name='authdata']").prop("checked", true);
        } else {  
            $("input[name='authdata']").prop("checked", false);
        } 
    });
}

function authTypChange(object){
    var authKey = $(object).val();
    if ("" == authKey || undefined == authKey) {
        return;
    }
    $("#permission_tree").empty();
    switch (authKey) {
        case "PROV":
            getProvData();
            break;
        case "CITY":
            getCityData();
            break;
        case "SHOP":
            getShopData();
            break;
        case "STOH":
            getStohData();
            break;
        case "SUPPLI":
            getSuppliData();
            break;
    }
}

function getProvData(){
    var $provData = ["湖南省","江苏省","广东省"];
    for ( var prov in $provData) {
        var $provHtml = $('<div class="checkbox"><label><input type="checkbox" name="authdata" value="'+$provData[prov]+'">'+$provData[prov]+'</label></div>');
        $("#permission_tree").append($provHtml);
    }
}

function getCityData(){
    var $cityData = ["长沙市","常州市","深圳市"];
    for ( var city in $cityData) {
        var $cityHtml = $('<div class="checkbox"><label><input type="checkbox" name="authdata" value="'+$cityData[city]+'">'+$cityData[city]+'</label></div>');
        $("#permission_tree").append($cityHtml);
    }
}

function getShopData(){
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", {}, function(data) {
        if ("00000" == data.rspCd) {
            var $deptments = data.deptments;
            for ( var shop in $deptments) {
                if("SHOP"===$deptments[shop].deparTyp){
                    var $shopHtml = $('<div class="checkbox"><label><input type="checkbox" name="authdata" value="'+$deptments[shop].typeNo+'">'+$deptments[shop].deparNm+'</label></div>');
                    $("#permission_tree").append($shopHtml);
                }
            }
        }else{
            layer.msg("查询门店失败" + data.rspCd + data.rspInf);
        }
    });
}

function getStohData(){
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", {}, function(data) {
        if ("00000" == data.rspCd) {
            var $deptments = data.deptments;
            for ( var stoh in $deptments) {
                if("STOH"===$deptments[stoh].deparTyp){
                    var $stohHtml = $('<div class="checkbox"><label><input type="checkbox" name="authdata" value="'+$deptments[stoh].typeNo+'">'+$deptments[stoh].deparNm+'</label></div>');
                    $("#permission_tree").append($stohHtml);
                }
            }
        }else{
            layer.msg("查询仓库失败" + data.rspCd + data.rspInf);
        }
    });
}

function getSuppliData(){
    $.postJson(YOUNG_DOMAIN + "/suppli/v1/supplier/query.do", {}, function(data) {
        if ("00000" == data.rspCd) {
            var $suppliers = data.suppliers;
            for ( var suppli in $suppliers) {
                var $suppliHtml = $('<div class="checkbox"><label><input type="checkbox" name="authdata" value="'+$suppliers[suppli].suppliNo+'">'+$suppliers[suppli].suppliNm+'</label></div>');
                $("#permission_tree").append($suppliHtml);
            }
        }else{
            layer.msg("查询仓库失败" + data.rspCd + data.rspInf);
        }
    });
}

function addDataAuth(){
    /**
     * 获取用户名
     * 获取authKey
     * 获取authData
     */
    var $usrNo = $('#mngerUsrNo').val();
    var $authKey = $('#authTyp').val();
    var $authData = new Array();
    $("input[name='authdata']").each(function(i){
        if(this.checked){
            $authData[i] = $(this).val();
        }
    });
    
    if ("" == $usrNo) {
        layer.msg("请选择相应的用户");
        return;
    }
    if ("" == $authKey) {
        layer.msg("请选择相应的授权类型");
        return;
    }
    if (0 == $authData.length) {
        layer.msg("请选择相应的授权数据");
        return;
    }
    
    $.postJson(YOUNG_DOMAIN + "/dataauth/v1/addAuthData.do", {authUsrNo:$usrNo,authKey:$authKey,authData:$authData}, function(data) {
        if ("00000" == data.rspCd) {
            layer.msg("添加权限成功");
        }else{
            layer.msg("添加权限失败" + data.rspCd + data.rspInf);
        }
    });
}

function initAuthTable($table){
    // 初始化table
    $table.bootstrapTable({
        url : YOUNG_DOMAIN + "/dataauth/v1/qryAuthDataLst.do", // 请求后台的URL（*）
        dataType : "json",
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : true, // 是否显示分页（*）
        sortable : false, // 是否启用排序
        sortOrder : "desc",
        queryParamsType : "custom",
        queryParams : function queryParams(params) {
            var temp = {
                    pageSize : params.pageSize,
                    currentPage : params.pageNumber - 1
                };
            return temp;
        },// 传递参数（*）
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
        uniqueId : "usrNo", // 每一行的唯一标识，一般为主键列
        showToggle : true, // 是否显示详细视图和列表视图的切换按钮
        cardView : false, // 是否显示详细视图
        detailView : false, // 是否显示父子表
        responseHandler : handel,
        idField : "usrNo",
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
                    field : "usrNm",
                    title : "用户名"
                },
                {
                    field : "ruleSts",
                    title : "权限状态"
                },
                {
                    field : "prov",
                    title : "省"
                }, {
                    field : "city",
                    title : "市"
                },
                {
                    title : "操作",
                    width : "15%",
                    clickToSelect : false,
                    formatter : function(value, row, index) {
                        var edit_button = '<button name="edit-datarole" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                                + row.userNo
                                + ' data-uniqueids='+row.ruleType+'><i class="glyphicon glyphicon-edit"></i> 编辑</button>';
                        var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-datarole" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                                + row.userNo+ ' data-uniqueids='+row.ruleType
                                + '><i class="glyphicon glyphicon-trash"></i> 删除</button>';
                        return edit_button + delete_button;
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
        }
    });
}

function handel(res) {
    return res;
}