/**
 * 角色js
 */
var _template = {
    ol : '<ol class="dd-list"></ol>',
    li : '<li class="dd-item"></li>',
    div : '<div class="dd-handle"><input type="checkbox" name="permissionIds" />&nbsp;</div>',
    span : '<span class="label label-info"></span>',
    i : '<i class=""></i>',
    button : '<span class="pull-right"><i class="glyphicon glyphicon-plus" title="添加子节点" aria-hidden="true"></i> <i class="glyphicon glyphicon-minus" title="删除子节点" aria-hidden="true"></i></span>'
}
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
$(function() {
    var $table = $('#role_table');
    // 初始化table
    $table.bootstrapTable({
                url : YOUNG_DOMAIN + "/admin/v1/role/page/query.do", // 请求后台的URL（*）
                dataType : "json",
                method : 'post', // 请求方式（*）
                toolbar : '#toolbar', // 工具按钮用哪个容器
                striped : true, // 是否显示行间隔色
                cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination : true, // 是否显示分页（*）
                sortable : true, // 是否启用排序
                sortName : "CRE_DT",
                sortOrder : "ASC",
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
                uniqueId : "roleNo", // 每一行的唯一标识，一般为主键列
                showToggle : true, // 是否显示详细视图和列表视图的切换按钮
                cardView : false, // 是否显示详细视图
                detailView : false, // 是否显示父子表
                responseHandler : handel,
                idField : "roleNo",
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
                            field : 'roleNo',
                            title : '角色编号',
                            sortable : true
                        },
                        {
                            field : "roleNm",
                            title : "角色名称",
                            sortable : true
                        },
                        {
                            field : "roleSts",
                            title : "状态",
                            sortable : true,
                            formatter : function(value, row, index) {
                                return value == "S" ? "正常" : "禁用";
                            }
                        },
                        {
                            field : "roleDesc",
                            title : "描述",
                            sortable : true
                        },
                        {
                            title : "操作",
                            width : "15%",
                            clickToSelect : false,
                            formatter : function(value, row, index) {
                                var edit_button = '<button name="edit-role" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                                        + row.roleNo
                                        + '><i class="glyphicon glyphicon-edit"></i> 编辑</button>';
                                var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-role" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                                        + row.roleNo
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
                    $('#reportTable').bootstrapTable('removeAll');
                },
                onSort : function(name, order) {
                    switch (name) {
                    case "roleNo":
                        sort = "role_no";
                        break;
                    case "roleNm":
                        sort = "role_nm";
                        break;
                    default:
                        sort = "role_no";
                        break;
                    }
                }
            });

    var $toolbar = $("#toolbar");
    
    /**
     * 添加角色
     */
    $toolbar.on("click", "#add-role", function() {
        $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/add-role.html", function(data) {
            var l = layer.open({
                id : "add-role-dialog",
                type : 1,
                title : "添加角色",
                content : data,
                area : [ "70%", "90%" ],
                maxmin: true,
                closeBtn : 1,
                scrollbar : false,
                fix : true,
                move : false,
                shift : 5,
                btn : [ "添加", "取消" ],
                success : function() {
                    initTree();
                    initAddDialog();
                },
                yes : function(index) {
                    var $addroleForm = $("#add-role-form");
                    if (!$addroleForm.valid()) {
                        return;
                    }
                    var $roleInfo = $addroleForm.serializeJSON();
                    var permissionNos = new Array();
                    var $tree = $(".dd");
                    $tree.find(":checked").each(function() {
                    	permissionNos.push($(this).val());
                    });
                    $roleInfo.permissionNos = permissionNos;
                    $.postJson(YOUNG_DOMAIN + "/admin/v1/role/add.do", $roleInfo, function(data) {
                        layer.close(index);
                            if (data.rspCd == "00000") {
                                toastr.success("添加角色成功", "添加成功");
                                $table.bootstrapTable("refresh");
                            } else {
                                toastr.error(data.rspInf, "添加失败");
                            }
                    });
                }
            });
        });
    });

    $toolbar.on("click", "#delete-role", function() {
        var $selected = $table.bootstrapTable('getAllSelections');
        if ($selected.length == 0) {
            toastr.warning("请先选择角色");
            return;
        }
        var roleNos = new Array();
        for(var i = 0; i < $selected.length; i++) {
            roleNos.push($selected[i].roleNo);
        }
        deleteRole(roleNos);
    });

    $table.on("click", "button[name='edit-role']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        // 先读取role信息
        var $loadingRole = layer.load(2);
        $.postJson(YOUNG_DOMAIN + "/admin/v1/role/query.do", {"roleNo": $uniqueid}, function(data){
            layer.close($loadingRole);
            var $role = data.role;
            var $permissionNos = data.permissionNos;
            // 查询到才读取
            if (null != data && data.rspCd == "00000") {
                $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/edit-role.html", function(data) {
                    var l = layer.open({
                        id : "edit-role-dialog",
                        type : 1,
                        title : "编辑角色",
                        content : data,
                        area : [ "70%", "90%" ],
                        maxmin: true,
                        closeBtn : 1,
                        scrollbar : false,
                        fix : true,
                        move : false,
                        shift : 5,
                        btn : [ "确定", "取消" ],
                        success : function() {
                            initTree();
                            initEditDialog($role, $permissionNos);
                        },
                        yes : function(index) {
                            var $editroleForm = $("#edit-role-form");
                            if (!$editroleForm.valid()) {
                                return;
                            }
                            var $roleInfo = $editroleForm.serializeJSON();
                            var permissionNos = new Array();
                            var $tree = $(".dd");
                            $tree.find(":checked").each(function() {
                            	permissionNos.push($(this).val());
                            });
                            $roleInfo.permissionNos = permissionNos;
                            $.postJson(YOUNG_DOMAIN + "/admin/v1/role/update.do", $roleInfo,
                                    function(data) {
                                        if (data.rspCd == "00000") {
                                            toastr.success("更新角色成功", "更新成功");
                                            $table.bootstrapTable("refresh");
                                            layer.close(index);
                                        } else {
                                            toastr.error(data.rspInf, "更新失败");
                                        }
                                    });
                        }
                    });
                });
            } else {
                toastr.error("未能读取到角色信息请刷新重试", "打开弹出层失败");
            }
        });
    });
    $table.on("click", "button[name='delete-role']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        deleteRole([$uniqueid]);
    });
});
/**
 * 删除角色
 */
function deleteRole(roleNos) {
    layer.confirm("已经关联用户的角色将不会被删除，确定继续吗？", {
        title : "删除角色",
        icon : 0,
        btn : [ "删除", "不了" ]
    }, function(index) {
        var url = YOUNG_DOMAIN + "/admin/v1/role/delete.do";
        $.postJson(url, {"roleNos": roleNos}, function(data) {
            if (data.rspCd == "00000") {
                toastr.success("删除角色成功", "删除成功");
            }
            // 部分成功，组装信息
            if (data.rspCd == "00001") {
                var message = "以下角色删除失败：" + data.faildRoleNos + "</br> 原因：已经关联用户";
                toastr.warning(message, "部分成功");
            }
            if (data.rspCd != "00000" && data.rspCd != "00001") {
                toastr.error(data.rspInf, "删除失败");
                layer.close(index);
                return;
            }
            $("#role_table").bootstrapTable("refresh");
            layer.close(index);
        });
    });
}
function initCheckbox() {
    var $checkall = $("#checkall");
    $checkall.on("click", function() {
        $tree.find(":checkbox").prop("checked", $(this).prop("checked"));
    });
    var $tree = $(".dd");
    $tree.on("click", ".dd-item", function() {
        var $checkboxs = $tree.find(":checkbox");
        var $li = $(this);
        var $ol = $li.closest("ol");
        var $checkbox = $ol.find(":checkbox");
        $checkbox.prop("checked", !$li.find(":checkbox").prop("checked"));
        var $checked = $tree.find(":checked");
        if ($checkboxs.length == $checked.length) {
            $checkall.prop("checked", true);
        } else {
            $checkall.prop("checked", false);
        }
    });
}
function initEditDialog(role, permissionIds) {
    initCheckbox();
    var $editRoleForm = $("#edit-role-form");
    $editRoleForm.find("#roleNo").val(role.roleNo);
    $editRoleForm.find("#roleNm").val(role.roleNm);
    $editRoleForm.find("#roleDesc").val(role.roleDesc);
    if (role.roleSts == "S") {
        $editRoleForm.find("#S-status").prop("checked", true);
    } else {
        $editRoleForm.find("#F-status").prop("checked", true);
    }
    if (null != permissionIds && permissionIds.length > 0) {
        for(var i = 0; i < permissionIds.length; i++) {
            $("#permission").find("#" + permissionIds[i]).prop("checked", true);
        }
    }
    $editRoleForm.validate({
        rules : {
            roleNm : "required"
        },
        messages : {
            roleNm : "请输入角色名称"
        }
    });
}
/**
 * 初始化dialog
 */
function initAddDialog() {
    initCheckbox();
    var $addroleForm = $("#add-role-form");
    $addroleForm.validate({
        onkeyup : false,
        rules : {
            roleNo : {
                required : true,
                checkRoleNo : true
            },
            roleNm : "required"
        },
        messages : {
            roleNo : {
                required : "请输入角色编号",
                checkRoleNo : "该角色编号已存在"
            },
            roleNm : "请输入角色名称"
        }
    });
}

/**
 * 初始化树形菜单
 */
function initTree() {
    $.ajax({
        url : YOUNG_DOMAIN + "/permission/v1/query/all.do",
        type : "post",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : JSON.stringify({}),
        async : false,
        success : function(data) {
            console.log(data);
            if (data.rspCd == "00000") {
                generatorTree(data.menus, 0, $(".dd"));
            }
        }
    });
}

/**
 * 递归生成树形菜单
 * @param data 菜单集合
 * @param parentId 父ID
 * @param element 元素
 */
function generatorTree(data, parentId, element) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].fPerNo == parentId) {
            generatorTreeOL(data[i], element);
        }
    }
}

function generatorTreeOL(data, element) {
    var span = $(_template.span);
    if (null != data.perIco && "" != data.perIco) {
        var i_ele = $(_template.i).addClass(data.perIco);
        span.append(i_ele);
    }
    var div = $(_template.div).append(span)
    div.append(" " + data.perName);
    div.find(":checkbox").prop("id", data.perNo).val(data.perNo);
    var li = $(_template.li).data("id", data.perNo).data("this", data).append(
            div);
    var ol = $(_template.ol).append(li);
    // 是否有子菜单
    if (data.hasChild) {
        generatorTree(data.childPermissions, data.perNo, ol);
    }
    element.append(ol);
}
function handel(res) {
    console.log(res);
    return res;
}
function queryParams(params) { // 配置参数
	// 默认排序列
	var sort = "CRE_DT";
    var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize : params.pageSize, // 页面大小
        currentPage : params.pageNumber - 1, // 页码
        sort : "" == sort ? params.sortName : sort, // 排序列名
        sortName : params.sortOrder, // 排位命令（desc，asc）
        search : params.searchText //查询条件
    };
    return temp;
}