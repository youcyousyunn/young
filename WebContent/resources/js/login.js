/**
 * 登录JS,仅在login.html中引用
 */
// 提示设置
toastr.options = {
    "closeButton" : true,
    "debug" : false,
    "progressBar" : true,
    "positionClass" : "toast-top-center",
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
    // 获取表单
    var $form = $("#login-form");
    /**
     * 表单验证规则
     */
    $form.validate({
        rules : {
            loginNm : "required",
            password : "required"
        },
        messages : {
        	loginNm : "请输入帐号",
            password : "请输入密码"
        }
    });

    $form.on("click", "#login", function() {
        // 表单验证不通过不会进行登录操作
        if (!$form[0].checkValidity()) {
            return;
        }
        var loginInfo = $form.serializeJSON();
        var url = YOUNG_DOMAIN + "/user/v1/login.do";
        $.ajax({
            url : url,
            type : "post",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(loginInfo),
            success : function(data) {
                if (null != data && data.rspCd == "00000") {
                    // 设置登录信息
                    if (window.localStorage) {
                        window.localStorage.setItem("userName", data.usrNm);
                        window.localStorage.setItem("deptment", data.deparNm);
                    } else {
                        USER_NAME = data.usrNm;
                        DEPTMENT = data.deparNm;
                    }
                    // 成功跳转到主页
                    window.location.href = YOUNG_DOMAIN + "/index.html";
                }else{
                    toastr.error(data.rspInf, "登录失败");
                }
            }
        });
    });
});