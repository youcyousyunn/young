/**
 * 个人中心页面JS
 */
$(function() {
    // 初始化table
    var $table = $('#deptpost_table');
    var $form = $("#edit-userInfos-form");
	initDatePicker($form);
    initUserInfo($form);
    $form.on("click", "#editSelfInfo", function() {
        var userInfo = $form.serializeJSON();
        $.postJson(YOUNG_DOMAIN + "/user/v1/updateSelfInfo.do", userInfo,function(data) {
            if (data.rspCd == "00000") {
                toastr.success("编辑个人信息成功", "编辑成功");
            } else {
                toastr.error(data.rspInf, "编辑失败");
            }
        });
    });
});

function initUserInfo($form){
    //查询当前用户个人信息
    $.postJson(YOUNG_DOMAIN + "/user/v1/userInfo.do", {}, function(response) {
        var userInfo = response.userInfo;
        var roles=response.roles;
        initDeptmentSelect($form,{
            "deparNo" : userInfo.deparNo,
            "deparNm" : userInfo.deparNm,
            "usrNo" : userInfo.usrNo
        });
        initRoleSelect(roles);
        $("#usrNo").val(userInfo.usrNo);
        $("#usrNm").val(userInfo.usrNm);
        $("#jobNo").val(userInfo.jobNo);
        $("#sex").val(userInfo.sex);
        $("#duties").val(userInfo.duties);
        $("#mblNo").val(userInfo.mblNo);
        $("#birthday").val(userInfo.birthday);
        $("#deparNo").val(userInfo.deparNo);
        $("#deparNm").val(userInfo.deparNm);
        $("#entryDt").val(userInfo.entryDt);
        $("#wechat").val(userInfo.wechat);
        $("#qq").val(userInfo.qq);
        if(userInfo.sex=="M"){
            $form.find("#M-status").prop("checked", true);
        }else{
            $form.find("#W-status").prop("checked", true);
        }
        
        if (userInfo.usrSts == "S") {
            $form.find("#S-status").prop("checked", true);
        } else {
            $form.find("#F-status").prop("checked", true);
        }
    });
}

function updateSelfInfo($form){
   
}

/**
 * 获取部门下拉框信息
 */
function initDeptmentSelect($form, selectedDeptment) {
	var usrNo = selectedDeptment.usrNo;
    // 获取部门
    $.postJson(YOUNG_DOMAIN + "/deptment/v1/all.do", {usrNo : usrNo}, function(response) {
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
        }
        $("#deptmentSelect").select2({
            width : "100%"
        });
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
        }
        $("#roleSelect").select2({
            width : "100%"
        });
    });
}

/**
 * 日期控件初始化
 */
function initDatePicker($form) {
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
}

toastr.options = {
    closeButton: false,  
    debug: false,  
    progressBar: true,  
    positionClass: "toast-top-center",  
    onclick: null,  
    showDuration: "300",  
    hideDuration: "1000",  
    timeOut: "2000",  
    extendedTimeOut: "1000",  
    showEasing: "swing",  
    hideEasing: "linear",  
    showMethod: "fadeIn",  
    hideMethod: "fadeOut"  
}; 



