/**
 * 角色js
 */
//初始化table
var $table = $('#user_table');
$(function() {
    var $tree = initTree($table, true);
    var $toolbar = $("#toolbar");
    $table.bootstrapTable({
        url : YOUNG_DOMAIN + "/user/v1/page/query.do", // 请求后台的URL（*）
        dataType : "json",
        dataField : "data",
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination : true, // 是否显示分页（*）
        sortable : false, // 是否启用排序
        sortName : "roleNo",
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
                    title : "姓名"
                },
                {
                    field : "sex",
                    title : "性别",
                    formatter : function(value, row, index) {
                        return value == "M" ? "男" : "女";
                    }
                },
                {
                    field : "usrSts",
                    title : "状态",
                    formatter : function(value, row, index) {
                        return value == "S" ? "正常" : "禁用";
                    }
                },
                {
                    field : "mblNo",
                    title : "手机号"
                },
                {
                    field : "deparNm",
                    title : "部门"
                },{
                     field : "birthday",
                     title : "生日"
                },{
                    title : "操作",
                    width : "13%",
                    clickToSelect : false,
                    formatter : function(value, row, index) {
                        var edit_button = '<button name="edit-user" type="button" class="btn btn-primary btn-xs" data-uniqueid='
                                + row.usrNo
                                + '><i class="glyphicon glyphicon-edit"></i> 编辑</button>';
                        var delete_button = '&nbsp;&nbsp;&nbsp;<button name="delete-user" type="button" class="btn btn-danger btn-xs" data-uniqueid='
                                + row.usrNo
                                + '><i class="glyphicon glyphicon-trash"></i> 删除</button>';
                        return edit_button + delete_button;
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
            $('#reportTable').bootstrapTable('removeAll');
        },
    });

    $toolbar.on("click", "#delete-user", function() {
        var $selected = $table.bootstrapTable('getAllSelections');
        if ($selected.length == 0) {
            layer.msg("请先选择角色");
            return;
        }
        var userNos = new Array();
        for (var i = 0; i < $selected.length; i++) {
            userNos.push($selected[i].usrNo);
        }
        deleteUser(userNos);
    });

    $toolbar.on("click", "#add-user", function() {
        initAddUsrInfo();
    });

    /**
     * 编辑用户信息
     */
    $table.on("click", "button[name='edit-user']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        // 读取用户数据
        var load = layer.load(2);
        $.postJson(YOUNG_DOMAIN + "/user/v1/query.do", {"usrNo" : $uniqueid}, function(data) {
            layer.close(load);
            if (null != data && data.rspCd == "00000") {
                initEditDialog(data.userInfo, data.roles);
                return;
            }
            layer.msg("读取用户信息失败"+data.rspCd+":"+data.rspInf);
        });
    });
    $table.on("click", "button[name='delete-user']", function(e) {
        var $uniqueid = $(this).data("uniqueid");
        deleteUser([ $uniqueid ]);
    });
});

/**
 * 编辑用户信息弹窗
 */
function initEditDialog(userInfo, roles) {
    $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/edit-user.html", function(data) {
        layer.open({
            id : "edit-user-dialog",
            type : 1,
            title : "编辑用户",
            content : data,
            area : [ "70%", "70%" ],
            closeBtn : 1,
            scrollbar : false,
            fix : true,
            move : false,
            shift : 5,
            btn : [ "确定", "取消" ],
            success : function() {
                var $form = $("#edit-role-form");
                initRoleSelect(roles);
                initDeptmentSelect($form, {
                    "deparNo" : userInfo.deparNo,
                    "deparNm" : userInfo.deparNm
                });
                initDatePicker($form);
                initUserInfo($form, userInfo);
                initEditValidate($form);
            },
            yes : function(index) {
                var $form = $("#edit-role-form");
                if ($form.valid()) {
                    var userInfo = $form.serializeJSON();
                    console.log(userInfo);
                    var roleInputs = $("#roleInput").val().split(",");
                    var roles = new Array();
                    if (roleInputs.length > 0) {
                        for (var i = 0; i < roleInputs.length; i++) {
                            roles.push(roleInputs[i]);
                        }
                        userInfo.roleNos = roles;
                    }
                    console.log(userInfo);
                    var load = layer.load(2);
                    $.postJson(YOUNG_DOMAIN + "/user/v1/update.do", userInfo,function(data) {
                        layer.close(load);
                        if (data.rspCd == "00000") {
                            layer.msg("编辑用户成功");
                            $("#user_table").bootstrapTable("refresh");
                            layer.close(index);
                        } else {
                            layer.msg("编辑用户失败"+data.rspCd+data.rspInf);
                        }
                    });
                }
            }
        });
    });
}

/**
 * 初始化用户添加
 * @param $selected
 */
function initAddUsrInfo($depar){
    var deparTyp,typeNo;
    if (undefined === $depar || null === $depar) {
        var $depar = new Object();
        var $tree = $('#deptment-tree').jstree(true);
        var $selected = $tree.get_selected()[0];
        if ("0" === $selected) {
            layer.msg("请先选择一个部门");
            return;
        }
        var $node = $tree.get_node($selected);
        $depar.deparNo = $selected;
        $depar.deparNm = $node.text;
        //获取部门相关分类属性信息
        for(attr in $node.data){
            deparTyp = ""+attr;
            typeNo = $node.data[attr];
        }
    }
    $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/add-user.html", function(data) {
        layer.open({
            id : "add-user-dialog",
            type : 1,
            title : "新增用户",
            content : data,
            area : [ "70%", "80%" ],
            closeBtn : 1,
            scrollbar : false,
            fix : true,
            move : false,
            shift : 5,
            btn : [ "确定", "取消" ],
            success : function() {
                var $form = $("#add-role-form");
                $form.find("#deparNo").val($depar.deparNo);
                $form.find("#deparNm").val($depar.deparNm);
                initRoleSelect();
                initDatePicker($form);
                initValidate($form);
            },
            yes : function(index) {
                var $form = $("#add-role-form");
                if ($form.valid()) {
                    var userInfo = $form.serializeJSON();
                    var roleInputs = $("#roleInput").val().split(",");
                    var roles = new Array();
                    if (roleInputs.length > 0) {
                        for (var i = 0; i < roleInputs.length; i++) {
                            roles.push(roleInputs[i]);
                        }
                        userInfo.roleNos = roles;
                    }
                    var load = layer.load(2);
                    $.postJson(YOUNG_DOMAIN + "/user/v1/add.do", userInfo,function(data) {
                        layer.close(load);
                        if (data.rspCd == "00000") {
                            layer.msg("新增用户成功");
                            $table.bootstrapTable("refresh");
                        } else {
                            layer.msg("新增失败" + data.rspCd + data.rspInf);
                        }
                        layer.close(index);
                    });
                }
            }
        });
    });
}

/**
 * 初始化下拉框
 */
function initUserInfo($form, $userInfo) {
    $form.find("#mblNo").val($userInfo.mblNo);
    $form.find("#jobNo").val($userInfo.jobNo);
    $form.find("#usrNm").val($userInfo.usrNm);
    $form.find("#duties").val($userInfo.duties);
    $form.find("#entryDt").val($userInfo.entryDt);
    $form.find("#birthday").val($userInfo.birthday);
    $form.find("#qq").val($userInfo.qq);
    $form.find("#wechat").val($userInfo.wechat);
    $form.find("#userNo").val($userInfo.usrNo);
    if ($userInfo.sex == "M") {
        $form.find("#M-status").prop("checked", true);
    } else {
        $form.find("#W-status").prop("checked", true);
    }
    if ($userInfo.usrSts == "S") {
        $form.find("#S-status").prop("checked", true);
    } else {
        $form.find("#F-status").prop("checked", true);
    }
}

function initEditValidate($form) {
    $form.validate({
        onkeyup : false,
        rules : {
            usrNm : {
                required : true,
                maxlength : 15
            },
            mblNo : {
                required : true,
                digits : true,
                maxlength : 11,
                minlength : 11
            },
            jobNo : {
                required : true,
                maxlength : 10,
                checkChinese : true
            },
            duties : {
                required : true,
                maxlength : 10
            }
        },
        messages : {
            usrNm : {
                required : "请输入姓名",
                maxlength : "最长15个字符"
            },
            mblNo : {
                required : "请填写手机号",
                digits : "请输入正确的手机号",
                maxlength : "请输入正确的手机号",
                minlength : "请输入正确的手机号"
            },
            jobNo : {
                required : "请填写工号",
                maxlength : "工号最长10个字符",
                checkChinese : "不允许输入中文"
            },
            duties : {
                required : "请填写职务",
                maxlength : "最长10个字符"
            }
        }
    });
}

/**
 * 初始化角色下拉框
 */
function initRoleSelect(selectedRoles) {
    // 获取角色
    $.postJson(YOUNG_DOMAIN + "/admin/v1/role/all.do", {}, function(response) {
        if (null != response && response.roles.length > 0) {
            var roles = response.roles;
            var element = "";
            for (var i = 0; i < roles.length; i++) {
                element += "<option id=" + roles[i].roleNo + " value='"
                        + roles[i].roleNo + "'>" + roles[i].roleNo + "/"
                        + roles[i].roleNm + "</option>";
            }
            $("#roleSelect").html(element);
            $("#roleSelect").on("change", function() {
                var roleData = $(this).select2("data");
                $("#roleInput").val("");
                if (roleData.length > 0) {
                    var roles = "";
                    for (var i = 0; i < roleData.length; i++) {
                        if (i == roleData.length - 1) {
                            roles += roleData[i].id;
                        } else {
                            roles += roleData[i].id + ",";
                        }
                    }
                    $("#roleInput").val(roles);
                }
            });
            if (null != selectedRoles && selectedRoles.length > 0) {
                var selectRoles = "";
                for (var i = 0; i < selectedRoles.length; i++) {
                    $("#roleSelect").find("#" + selectedRoles[i].roleNo).prop(
                            "selected", true);
                    if (i == selectedRoles.length - 1) {
                        selectRoles += selectedRoles[i].roleNo;
                    } else {
                        selectRoles += selectedRoles[i].roleNo + ",";
                    }
                }
                $("#roleInput").val(selectRoles);
            }
        }else{
        	layer.msg("获取角色失败"+data.rspCd+data.rspInf);
        }
        $("#roleSelect").select2({
            width : "100%"
        });
    });
}

function initUserSelect(selecedUser) {
    // 获取用户
    $.postJson(YOUNG_DOMAIN + "/user/v1/all.do", selecedUser, function(response) {
        if (response.rspCd == "00000" && response.userInfos.length > 0) {
            var userInfos = response.userInfos;
            var element = "";
            var $mngerUsrNo = $("#mngerUsrNo");
            var $mngerNm = $("#mngerNm");
            element += "<option value=''>请选择</option>";
            for (var i = 0; i < userInfos.length; i++) {
                element += "<option id='depar" + userInfos[i].usrNo
                        + "' value='" + userInfos[i].usrNo + "'>"
                        + userInfos[i].usrNm + "</option>";
            }
            $("#userSelect").html(element);
            $("#userSelect").on("change", function() {
                $mngerUsrNo.val($(this).select2('data')[0].id);
                $mngerNm.val($(this).select2('data')[0].text);
            });
            if (null != selecedUser) {
                $mngerUsrNo.val(selecedUser.usrNo);
                $mngerNm.val(selecedUser.usrNm);
                $("#userSelect").val(selecedUser.usrNo);
            }
        }else{
        	layer.msg("获取用户失败"+data.rspCd+data.rspInf);
        }
        $("#userSelect").select2({
            width : "100%"
        });
    });
}

function initDeptmentSelect($form, selectedDeptment) {
    // 获取部门
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", {}, function(response) {
        if (null != response && response.deptments.length > 0) {
            var deptments = response.deptments;
            var element = "";
            var $deparNo = $form.find("#deparNo");
            var $deparNm = $form.find("#deparNm");
            for (var i = 0; i < deptments.length; i++) {
                if (i == 0 && null == selectedDeptment) {
                    $deparNo.val(deptments[i].deparNo);
                    $deparNm.val(deptments[i].deparNm);
                    element += "<option id='depar" + deptments[i].deparNo
                            + "' selected='selected' value='"
                            + deptments[i].deparNo + "'>"
                            + deptments[i].deparNm + "</option>";
                } else {
                    element += "<option id='depar" + deptments[i].deparNo
                            + "' value='" + deptments[i].deparNo + "'>"
                            + deptments[i].deparNm + "</option>";
                }
            }
            $("#deptmentSelect").html(element);
            $("#deptmentSelect").on("change", function() {
                $deparNo.val($(this).select2('data')[0].id);
                $deparNm.val($(this).select2('data')[0].text);
            });
            if (null != selectedDeptment) {
                $deparNo.val(selectedDeptment.deparNo);
                $deparNm.val(selectedDeptment.deparNm);
                $("#deptmentSelect").val(selectedDeptment.deparNo);
                console.log($deparNo.val());
            }
        }else{
        	layer.msg("获取部门失败"+data.rspCd+data.rspInf);
        }
        $("#deptmentSelect").select2({
            width : "100%"
        });
    });
}

/**
 * 日期控件初始化
 */
function initDatePicker($form) {
    // 生日
    $form.on("click", "#birthday-div", function() {
        jeDate({
            dateCell : "#birthday",
            format : "YYYY-MM-DD",
            isinitVal : false,
            festival : false,
            isTime : false, // 是否开启时间选择
            zIndex : 99999999
        })
    });
    // 入职日期
    $form.on("click", "#entryDt-div", function() {
        jeDate({
            dateCell : "#entryDt",
            format : "YYYY-MM-DD",
            isinitVal : false,
            festival : false,
            isTime : false, // 是否开启时间选择
            zIndex : 99999999
        })
    });
}

function initValidate($form) {
    $form.validate({
        onkeyup : false,
        rules : {
            usrNm : {
                required : true,
                maxlength : 15
            },
            mblNo : {
                required : true,
                digits : true,
                maxlength : 11,
                minlength : 11,
                checkMobileNo : true
            },
            jobNo : {
                required : true,
                maxlength : 10,
                checkChinese : true,
                checkJobNo : true
            },
            duties : {
                required : true,
                maxlength : 10
            }
        },
        messages : {
            usrNm : {
                required : "请输入姓名",
                maxlength : "最长15个字符"
            },
            mblNo : {
                required : "请填写手机号",
                digits : "请输入正确的手机号",
                maxlength : "请输入正确的手机号",
                minlength : "请输入正确的手机号",
                checkMobileNo : "手机号已经存在"
            },
            jobNo : {
                required : "请填写工号",
                maxlength : "工号最长10个字符",
                checkChinese : "不允许输入中文",
                checkJobNo : "工号已经存在"
            },
            duties : {
                required : "请填写职务",
                maxlength : "最长10个字符"
            }
        }
    });
}

/**
 * 删除用户
 */
function deleteUser(usrNos) {
    layer.confirm("确定要删除用户吗？", {
        title : "删除用户",
        icon : 0,
        btn : [ "删除", "不了" ]
    }, function(index) {
        var url = YOUNG_DOMAIN + "/user/v1/delete.do";
        $.postJson(url, {
            "usrNos" : usrNos
        }, function(data) {
            if (data.rspCd == "00000") {
                layer.msg("删除用户成功");
            }
            if (data.rspCd != "00000") {
                layer.msg("删除失败"+data.rspCd+data.rspInf);
                layer.close(index);
                return;
            }
            $("#user_table").bootstrapTable("refresh");
            layer.close(index);
        });
    });
}

// 默认排序列
var sort = "usr_no";
function handel(res) {
    return res;
}
function queryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageSize : params.pageSize, // 页面大小
        currentPage : params.pageNumber - 1, // 页码
        sort : "" == sort ? params.sortName : sort, // 排序列名
        sortName : params.sortOrder,
        deparNo : $("#deparNo").val(),
        search : params.searchText
    // 排位命令（desc，asc）
    };
    return temp;
}

/**
 * 递归生成树形菜单
 * @param data 菜单集合
 * @param parentId 父ID
 * @param element 元素
 */
function generatorTree(data, parentId, element) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].fDeparNo == parentId) {
            generatorTreeOL(data[i], element);
        }
    }
}

function generatorTreeOL(data, element) {
    var span = $(_template.span);
    var div = $(_template.div).append(span)
    var button = $(_template.button);
    div.append(button);
    div.append(" " + data.deparNm);
    var li = $(_template.li).data("id", data.deparNo).data("this", data)
            .append(div);
    var ol = $(_template.ol).append(li);
    // 是否有子菜单
    if (data.hasChild) {
        generatorTree(data.childDeptments, data.deparNo, ol);
    }
    element.append(ol);
}