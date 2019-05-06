/**
 * 部门操作JS
 */
$(function() {
    // 初始化树形菜单
    var $tree = $("#deptment-tree");
    var $deptment_toolbar = $("#deptment-toolbar");
    // 重命名
    $deptment_toolbar.on("click", "#rename-deptment", function() {
        var $jstree = $tree.jstree(true);
        var $id = $jstree.get_selected();
        if ($id > 0) {
            var $node = $jstree.get_node($id);
            var oldName = $node.text;
            $jstree.edit($id, $node.text, function(node, status, isCanceled) {
                if (!isCanceled && node.text != "" && oldName != node.text) {
                    $.postJson(YOUNG_DOMAIN + "/deptment/v1/rename.do", {
                        "deptmentNo" : node.id,
                        "deptmentName" : node.text
                    }, function(data) {
                        if (data.rspCd == "00000") {
                            layer.msg("修改部门名称成功");
                        } else {
                            layer.msg("修改部门名称失败" + data.rspCd + data.rspInf);
                        }
                    });
                }
            });
        }
    });
    
    // 删除
    $deptment_toolbar.on("click", "#delete-deptment", function() {
        var $jstree = $tree.jstree(true);
        var $id = $jstree.get_selected();
        if ($id > 0) {
            layer.confirm("确定要删除该部门吗？有用户关联的无法删除！", {
                btn : [ "确定", "我再想想" ],
            }, function(index) {
                var $load = layer.load(2);
                $.postJson(YOUNG_DOMAIN + "/deptment/v1/delete.do", {
                    deparNo : $id[0]
                }, function(data) {
                    if (data.rspCd == "00000") {
                        $jstree.delete_node($id);
                        layer.msg("删除部门成功");
                    } else {
                        layer.msg("删除部门失败" + data.rspCd + data.rspInf);
                    }
                });
                layer.close($load);
                layer.close(index);
            });
        }
    });
    
    // 新增
    $deptment_toolbar.on("click", "#add-deptment", function() {
        $.get(YOUNG_DOMAIN + "/admin/dialog/add-deptment.html", function(data) {
            layer.open({
                id : "add-deptment-dialog",
                type : 1,
                title : "添加部门",
                content : data,
                zIndex : 1000,
                btn : [ "添加", "取消" ],
                area : [ "40%", "90%" ],
                success : function() {
                    // 初始化用户下拉框
                    initUserSelect();
                    // 初始化部门下拉框
                    initAddDeptmentSelect();
                    // 初始化新增负责人按钮
                    initAddMngUsr();
                    var $form = $("#add-deptment-form");
                    $form.validate({
                        rules : {
                            deparNm : {
                                required : true,
                                maxlength : 10
                            }
                        },
                        messages : {
                            deparNm : {
                                required : "请输入部门名称",
                                maxlength : "最长10个字符"
                            }
                        }
                    });
                },
                yes : function(index) {
                    var $form = $("#add-deptment-form");
                    var $deptTyp;
                    if ($form.valid()) {
                        var deptmentInfo = $form.serializeJSON();
                        // 计算排序
                        var fDeparNo = deptmentInfo.fDeparNo;
                        var $jstree = $tree.jstree(true);
                        var orderNum = "";
                        var $fDeptment = $jstree.get_node(fDeparNo);
                        var num = $fDeptment.children.length + 1;
                        orderNum = "" + fDeparNo + num;
                        deptmentInfo.orderNum = orderNum;
                        $deptTyp = deptmentInfo.deparTyp;
                        
                        //如果为三大特殊部门则需要另外补充东西
                        if("NORMAL"!=$deptTyp){
                            console.log($deptTyp);
                            switch ($deptTyp) {
                                case "COMPAN":
                                    initCompanHtml($tree, deptmentInfo);
                                    break;
                                case "STOH":
                                    if(!checkStohMngUsr(deptmentInfo.mngerUsrNo)){
                                        return;
                                    }else{
                                        layer.close(index);
                                        initWareHouseHtml($tree, deptmentInfo);
                                    }
                                    break;
                                case "SUPPLI":
                                    layer.close(index);
                                    initSuppliHtml($tree, deptmentInfo);
                                    break;
                                case "SHOP":
                                    if(!checkShopMngUsr(deptmentInfo.mngerUsrNo)){
                                        return;
                                    }else{
                                        layer.close(index);
                                        initShopHtml($tree, deptmentInfo);
                                    }
                                    break;
                                default:
                                    layer.msg("未知部门类型");
                                    break;
                            }
                        }else{
                            addSubmit($tree, deptmentInfo);
                            layer.close(index);
                        }
                    }else{
                        return;
                    }
                }
            });
        });
    });
    
    // 编辑
    $deptment_toolbar.on("click", "#edit-deptment", function() {
        var $jstree = $tree.jstree(true);
        var $id = $jstree.get_selected();
        if ($id > 0) {
            var load = layer.load(2);
            $.postJson(YOUNG_DOMAIN + "/deptment/v1/get.do", {deparNo : $id[0]}, function(data) {
                if (data.rspCd == "00000") {
                  //如果为三大特殊部门则需要另外补充东西
                    if ("NORMAL" != data.deptment.deparTyp) {
                        console.log("111");
                        console.log(data.deptment.typeNo);
                        console.log(data.deptment.mngerNm);
                        console.log(data.deptment.deparNm);
                        switch (data.deptment.deparTyp) {
                            case "COMPAN":
                                editCompanHtml($jstree, data.deptment)
                                break;
                            case "STOH":
                                editWareHouseHtml($jstree, data.deptment);
                                break;
                            case "SHOP":
                                editShopHtml($jstree, data.deptment);
                                break;
                            default:
                                layer.msg("未知部门类型");
                                break;
                        }
                    } else{
                        $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/edit-deptment.html",function(html) {
                            layer.open({
                                id : "edit-deptment-dialog",
                                type : 1,
                                title : "编辑部门",
                                content : html,
                                btn : [ "确定", "取消" ],
                                area : [ "40%", "90%" ],
                                success : function() {
                                    // 初始化用户下拉框
                                    initUserSelect({
                                        usrNo : data.deptment.mngerUsrNo,
                                        usrNm : data.deptment.mngerNm
                                    });
                                    // 初始化部门下拉框
                                    initAddDeptmentSelect(data.deptment);
                                    var $form = $("#edit-deptment-form");
                                    $form.find("#deparNm").val(data.deptment.deparNm);
                                    $form.find("#deparNo").val(data.deptment.deparNo);
                                    $form.find("#deparTyp").val(data.deptment.deparTyp);
                                    $form.find("#typeNo").val(data.deptment.typeNo);
                                    if (data.deptment.deparSts.trim() == "S") {
                                        $form.find("#S-status").prop("checked", true);
                                    } else {
                                        $form.find("#F-status").prop("checked", true);
                                    }
                                    $form.find("#deparDesc").val(data.deptment.deparDesc);
                                    $form.validate({
                                        rules : {
                                            deparNm : {
                                                required : true,
                                                maxlength : 10
                                            }
                                        },
                                        messages : {
                                            deparNm : {
                                                required : "请输入部门名称",
                                                maxlength : "最长10个字符"
                                            }
                                        }
                                    });
                                },
                                yes : function(index) {
                                    var $form = $("#edit-deptment-form");
                                    if (!$form.valid()) {
                                        return;
                                    }
                                    var load = layer.load(2);
                                    var fDeparNo = $form.find("#fDeparNo").val();
                                    var node = $jstree.get_node($form.find("#deparNo").val());
                                    var deptmentInfo = $form.serializeJSON();
                                    updSubmit($jstree, deptmentInfo);
                                    layer.close(index);
                                }
                            });
                        })
                    }
                } else {
                    layer.msg("修改失败" + data.rspCd + data.rspInf);
                }
                layer.close(load);
            });
        }
    });
});

/**
 * 初始化树形菜单
 */
function initTree($table, showAll, callback) {
    var clickNum = 0;
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/tree.do", {showAll: showAll}, function(data) {
        if (data.rspCd == "00000") {
            data.deptments[0].state = {"selected" : true};
            $("#deparNo").val(data.deptments[0].id);
            var $tree = $('#deptment-tree');
            $tree.on("loaded.jstree", function(e, tree) {
                $tree.jstree("open_all");
            }).on("refresh.jstree", function() {
                $tree.jstree("open_all");
            }).on("changed.jstree", function(node, action, selected, e) {
                var $id = $tree.jstree().get_selected();
                $("#deparNo").val($id);
                if (clickNum >= 2) {
                    $table.bootstrapTable("refresh");
                }
                clickNum++;
            }).jstree({
                'core' : {
                    'data' : data.deptments,
                    "check_callback" : true
                },
                "plugins" : [ "dnd" ]
            }).on("move_node.jstree", function(e, nodes) {
                // 获取父级
                var parent = $tree.jstree().get_node(nodes.node.parent);
                var children = parent.children;
                var deptments = new Array();
                for (var i = 0; i < children.length; i++) {
                    deptments.push({
                        deparNo: children[i],
                        fDeparNo: parent.id,
                        orderNum: parent.id + i
                    });
                }
                $.postJson(YOUNG_DOMAIN + "/deptment/v1/reorder.do", {deptments: deptments}, function(data) {
                    if (data.rspCd == "00000") {
//                        toastr.success("挪动位置成功", "成功");
                    }
                });
            });
            if(callback || typeof callback != 'undefined' || callback != undefined) {
                callback();
            }
            return $tree;
        } else {
            layer.msg(data.rspCd+":"+data.rspInf);
        }
    });
}

function addSubmit($tree, deptmentInfo){
    $.postJsonAsync(YOUNG_DOMAIN + "/deptment/v1/add.do",deptmentInfo, function(data) {
        if (data.rspCd == "00000") {
            var $jstree = $tree.jstree(true);
            var node = {
                id : data.deptmentNo,
                text : deptmentInfo.deparNm,
                icon : "glyphicon glyphicon-user"
            };
            $jstree.create_node(deptmentInfo.fDeparNo, node);
            layer.msg("新增部门成功");
            layer.close(index);
        } else {
            layer.msg("新增部门失败" + data.rspCd + data.rspInf);
        }
    });
}

function updSubmit($jstree, deptmentInfo){
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/update.do", deptmentInfo, function(data) {
        var $load = layer.load();
        if (data.rspCd == "00000") {
            var $id = $jstree.get_selected();
            $jstree.rename_node($id, deptmentInfo.deparNm);
            layer.msg("修改成功");
        } else {
            layer.msg("修改失败" + data.rspCd + data.rspInf);
        }
        layer.close($load);
    });
}

function initDate(){
    $('#openDate').on('click', function(){
        jeDate({
            dateCell:"#creDt",
            format:"YYYY-MM-DD",
            isinitVal:true,
            festival:true,
            marks:['2015-01-03'],
            isTime:false, //是否开启时间选择
            zIndex:3000,
            minDate:"2014-09-19 00:00:00",//最小日期
            okfun:function(val){}//点击确定后的回调
        })
    });
}

function initAddDeptmentSelect(selectedDeptment) {
	var usrNo = selectedDeptment.mngerUsrNo;
    // 获取部门
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", {usrNo : usrNo}, function(response) {
        if (null != response && response.deptments.length > 0) {
            var deptments = response.deptments;
            var element = "<option value=''>请选择</option>";
            var $deparNo = $("#fDeparNo");
            var $deparNm = $("#fDeparNm");
            for (var i = 0; i < deptments.length; i++) {
                if (null != selectedDeptment && deptments[i].deparNo == selectedDeptment.deparNo) {
                    continue;
                }
                element += "<option id='depar" + deptments[i].deparNo
                        + "' value='" + deptments[i].deparNo + "'>"
                        + deptments[i].deparNm + "</option>";
            }
            $("#deptmentSelect").html(element);
            $("#deptmentSelect").on("change", function() {
                $deparNo.val($(this).select2('data')[0].id);
                $deparNm.val($(this).select2('data')[0].text);
            });
            if (null != selectedDeptment) {
                console.log(selectedDeptment);
                $deparNo.val(selectedDeptment.fDeparNo);
                $deparNm.val(selectedDeptment.fDeparNm);
                $("#deptmentSelect").val(selectedDeptment.fDeparNo);
            }
        }else{
            layer.msg("获取部门失败"+data.rspCd+data.rspInf);
        }
        $("#deptmentSelect").select2({
            width : "100%"
        });
    });
}

function initAddMngUsr(){
    $('#addMngerUser').on('click', function(){
        var $deparNo = $("#fDeparNo").val();
        var $deparNm = $("#fDeparNm").val();
        if (undefined === $deparNo || '0' === $deparNo) {
            layer.msg("请选择父级部门！！！");
            return false;
        }
        var $depar = new Object();
        $depar.deparNo = $deparNo;
        $depar.deparNm = $deparNm;
        initAddUsrInfo($depar);
    });
}

function displayMap(callback){
    // 百度地图API功能
    var map = new BMap.Map("baiduMap");
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
    
    //获取默认地址
    var lngude = 112.977746;
    var latude = 28.208465;
    
    //获取浏览器当前地址
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            lngude = r.point.lng;
            latude = r.point.lat;
        }
    },{enableHighAccuracy: true})
    
    var point = new BMap.Point(lngude,latude);
    map.centerAndZoom(point,12);
    map.enableScrollWheelZoom(true);
    map.addControl(top_left_control);        
    map.addControl(top_left_navigation);     
    map.addControl(top_right_navigation);
    
    var geoc = new BMap.Geocoder();    
    map.addEventListener("click", function(e){ 
        map.clearOverlays();                  //删除其它标注
        var pt = e.point;
        var marker = new BMap.Marker(pt);     // 创建标注
        map.addOverlay(marker);               // 将标注添加到地图中
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        $('#lngUde').val(e.point.lng);
        $('#latUde').val(e.point.lat);
        geoc.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            $('#prov').val(addComp.province);
            $('#city').val(addComp.city);
            $('#district').val(addComp.district);
            $('#provCity').val(addComp.province+' '+addComp.city+' '+addComp.district);
            $('#workAdrr').val(addComp.province+' '+addComp.city+' '+addComp.district+' '+addComp.street+' '+addComp.streetNumber);
            
            if (typeof callback === "function"){
                callback(); 
            }
        });
    });
}