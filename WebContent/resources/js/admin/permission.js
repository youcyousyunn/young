/**
 * 权限管理JS
 * @author Baron.Luo
 */

/**
 * 初始化
 */

/**
 * 生成树的元素
 */
var _template = {
    ol : '<ol class="dd-list"></ol>',
    li : '<li class="dd-item"></li>',
    div : '<div class="dd-handle"></div>',
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
    initFonticonPicker();
    var $tree = $("#permission_tree");
    /**
     * 添加
     */
    $tree.on("click", ".glyphicon-plus", function(e) {
        // 阻止事件DOM传递,不知道是啥的人可以注释下面那句话再去页面试试~
        e.stopPropagation();
        var $li = $(this).closest("li");
        var fTree = $li.data("this");
        var level = fTree.perLv;
        var parentId = fTree.perNo;
        var $parentElement = $li.closest("ol");
        // 添加子菜单
        loadAddChildDialog(parentId, level, $parentElement);
    });
    // 更新form表单
    var $updateForm = $("#update-child-form");
    /**
     * 查看菜单信息
     */
    $tree.on("click", ".dd-handle", function() {
        var $li = $(this).closest("li");
        var data = $li.data("this");
        $updateForm.data("li", $li);
        $updateForm.find("#name").val(data.perName);
        $updateForm.find("#type").val(data.perType);
        $updateForm.find("#perNo").val(data.perNo);
        if (data.perSts == "S") {
            $updateForm.find("#S_status").prop("checked", true);
        } else {
            $updateForm.find("#F_status").prop("checked", true);
        }
        $updateForm.find("#url").val(data.perUrl);
        $(".selected-icon").empty();
        if (null != data.perIco && "" != data.perIco) {
            $(".selected-icon").append("<i class='" + data.perIco + "'></i>");
            $updateForm.find("#font-select").val(data.perIco);
        } else {
            $(".selected-icon").append("<i class='fip-icon-block'></i>");
            $updateForm.find("#font-select").val("");
        }
        $updateForm.find("#desc").val(data.perDesc);
    });
    
    /**
     * 更新操作
     */
    $updateForm.on("click", ".btn-success", function() {
        var id = $updateForm.find("#perNo").val();
        // 判断隐藏域是否为有值
        if (isNaN(id) || id == "") {
            toastr.warning("请先点选需要更改的权限");
            return;
        }
        $updateForm.validate({
            rules : {
                name : "required",
                url : "required"
            },
            messages : {
                name : "请输入权限名称",
                url : "请输入权限URL"
            }
        });
        if ($updateForm.valid()) {
            var permissionInfo = $updateForm.serializeJSON();
            var url = YOUNG_DOMAIN + "/permission/v1/update.do";
            var load = layer.load(2);
            $.postJson(url, permissionInfo, function(data) {
                layer.close(load);
                if (data.rspCd == "00000") {
                    $updateForm.find(".btn-default").click();
                    var $li = $updateForm.data("li");
                    updatePermissionDataInfo($li, permissionInfo);
                    toastr.success("更新成功", "成功");
                } else {
                    toastr.error(data.rspInf, "失败");
                }
            });
        }
    });
    /**
     * 重置操作
     */
    $updateForm.on("click", ".btn-default", function() {
        $updateForm.find("#perNo").val("");
        $updateForm.find("#font-select").val("");
        $updateForm.find("div").removeClass("has-success has-error");
        $updateForm.find("span[class^='help-block']").remove();
        $(".selected-icon").empty();
        $(".selected-icon").append("<i class='fip-icon-block'></i>");
    });
    /**
     * 添加一级菜单
     */
    $("#tree-div").on("click", ".btn-success", function(e) {
        e.stopPropagation();
        var $parentElement = $(".dd");
        loadAddChildDialog(0, 1, $parentElement);
    });
    $tree.on("click", ".glyphicon-minus", function(e) {
        e.stopPropagation();
        // 当前节点
        var $li = $(this).closest("li");
        // 找到父节点ol再找到下面所有li
        var $ol = $li.closest("ol");
        var $childLi = $ol.find("li");
        layer.confirm("删除节点同时也会删除其子节点，确定要继续吗？", {
            icon : 3,
            title : "警告",
            btn : [ "任然删除", "不了" ]
        }, function(index) {
            var ids = new Array();
            $childLi.each(function(e) {
                ids.push($($childLi[e]).data("id"));
            });
            $.postJson(YOUNG_DOMAIN + "/permission/v1/delete.do", {
                "permissionNos" : ids
            }, function(data) {
                if (data.rspCd == "00000") {
                    toastr.success("删除权限成功", "删除成功");
                    $ol.remove();
                } else {
                    toastr.error(data.rspInf, "删除失败");
                }
            });
            layer.close(index);
        });
    });
    initTree();
});
/**
 * 更新li上的data
 * 
 * @param $li li元素
 * @param permissionInfo 权限信息
 */
function updatePermissionDataInfo($li, permissionInfo) {
    var $liData = $li.data("this");
    $liData.perName = permissionInfo.name;
    $liData.perSts = permissionInfo.status;
    $liData.perIco = permissionInfo.icon;
    $liData.perDesc = permissionInfo.desc;
    $liData.perUrl = permissionInfo.url;
    $liData.perType = permissionInfo.type;
    $li.data("this", $liData);
    // 更新显示
    var span = $(_template.span);
    if (null != permissionInfo.icon && "" != permissionInfo.icon) {
        var i = $(_template.i).addClass(permissionInfo.icon);
        span.append(i);
    }
    var $div = $li.find("div");
    $div.html(span).append(" " + permissionInfo.name).append(_template.button);
}
/**
 * 打开弹出层
 * 
 * @param parentId 父id
 * @param level 级别
 * @param parentElement 父元素
 */
function loadAddChildDialog(parentId, level, parentElement) {
    $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/add-child.html", function(data) {
        var l = layer.open({
            id : "add-child",
            type : 1,
            title : "新增子节点",
            content : data,
            closeBtn : 1,
            scrollbar : false,
            fix : true,
            move : false,
            shift : 5,
            area : ["50%", "auto"],
            success : function(a) {
                $("#add-child-form").find("#font-select").fontIconPicker({
                    source : fnt_awesome_icons,
                    theme : "fip-bootstrap"
                });
                // $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});
            },
            btn : [ '添加', '取消' ],
            yes : function() {
                var $addForm = $("#add-child-form");
                $addForm.validate({
                    rules : {
                        name : "required",
                        url : "required"
                    },
                    messages : {
                        name : "请输入权限名称",
                        url : "请输入权限URL"
                    }
                });
                // 表单验证不通过不会进行登录操作
                if (!$addForm.valid()) {
                    return;
                }
                var permissionInfo = $addForm.serializeJSON();
                permissionInfo.parentId = parentId;
                permissionInfo.level = parseInt(level + 1, 0);
                console.log(permissionInfo);
                addChildPermission(permissionInfo, parentElement, l);
            }
        });
    });
}
/**
 * 添加子菜单
 * 
 * @param permissionInfo 菜单
 * @param parentElement 父元素
 * @param dialog 弹出层
 */
function addChildPermission(permissionInfo, parentElement, dialog) {
    var url = YOUNG_DOMAIN + "/permission/v1/add.do";
    var load = layer.load(2);
    $.ajax({
        url : url,
        type : "post",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : JSON.stringify(permissionInfo),
        success : function(data) {
            layer.close(load);
            if (data.rspCd == "00000") {
                toastr.success("添加菜单成功", "添加成功");
                // 生成菜单
                generatorTreeOL(data.permission, parentElement);
                layer.close(dialog);
            } else {
                toastr.error(data.rspInf, "添加失败");
            }
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
 * 初始化图标选择器
 */
function initFonticonPicker() {
    $("#update-child-form").find("#font-select").fontIconPicker({
        source : fnt_awesome_icons,
        theme : "fip-bootstrap"
    });
}

/**
 * 递归生成树形菜单
 * 
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
    var button = $(_template.button);
    div.append(button);
    div.append(" " + data.perName);
    var li = $(_template.li).data("id", data.perNo).data("this", data).append(
            div);
    var ol = $(_template.ol).append(li);
    // 是否有子菜单
    if (data.hasChild) {
        generatorTree(data.childPermissions, data.perNo, ol);
    }
    element.append(ol);
}