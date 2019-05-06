/**
 * 个人中心页面JS
 */
var $oldPwd;
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
$(function() {
    // 初始化table
    var $table = $('#deptpost_table');
    var $form = $("#edit-password-form");
    initUserInfo();
    // 表单校验
    $form.validate({
        rules : {
        	oldUsrPwd : "required",
        	usrPwd : "required",
        	confirmUsrPwd : "required"
        },
        messages : {
        	oldUsrPwd : "请输入原密码",
        	usrPwd : "请输入新密码",
        	confirmUsrPwd: "请输入确认密码"
        }
    });
    $form.on("click", "#editSelfpwd", function() {
    	// 表单验证不通过不会进行下一步操作
        if (!$form[0].checkValidity()) {
            return;
        }
        if ($oldPwd != $("#oldUsrPwd").val()) {
        	toastr.error("原始密码不正确", "error");
        	return
        }
        if ($("#confirmUsrPwd").val() != $("#newUsrPwd").val()) {
        	toastr.error("两次输入密码不一致", "error");
        	return
        } else {
            var userInfo = $form.serializeJSON();
            $.postJson(YOUNG_DOMAIN + "/user/v1/updateSelfPwd.do", userInfo,function(data) {
                if (data.rspCd == "00000") {
                    toastr.success("修改密码成功", "success");
                } else {
                    toastr.error(data.rspInf, "error");
                }
            });
        }
    });
});

/**
 * 获取原始密码
 */
function initUserInfo(){
    // 查询当前用户个人信息
    $.postJson(YOUNG_DOMAIN + "/user/v1/userInfo.do", {}, function(response) {
        var userInfo = response.userInfo;
        $oldPwd = userInfo.usrPwd
    });
}
