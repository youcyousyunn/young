/**
 * 注册JS,仅在register.html中引用
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
    var $form = $(".m-t");
    
    /**
     * 表单验证规则
     */
    $form.validate({
        rules : {
            loginNm : "required",
            password : "required",
            confirmPwd: "required",
            email: "required"
        },
        messages : {
            loginNm : "请输入用户名",
            password : "请输入密码",
            confirmPwd: "请确认密码",
            email: "请输入邮箱"
        }
    });
    
    $form.on("click", "#identify", function() {
    	var identifyInfo = {};
        var url = YOUNG_DOMAIN + "/user/v1/sendIdentifyCode.do";
        $.ajax({
            url : url,
            type : "post",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(identifyInfo),
            success : function(data) {
                if (null != data && data.rspCd == "00000") {
                	toastr.success("验证码已发送！", "success");
                	sendIdentifyCode();
                }else{
                	toastr.error("验证码发送失败！", "error");
                }
            }
        });
    });

    $form.on("click", "#register", function() {
        // 表单验证不通过不会进行注册操作
        if (!$form[0].checkValidity()) {
            return;
        }
        if ($("#confirmPwd").val() != $("#password").val()) {
        	toastr.error("两次输入密码不一致", "error");
        	return
        }
        var iCheck = $("#iCheck")[0].checked
        if (!iCheck) {
        	toastr.warning("请同意注册协议", "warning");
        	return
        }
        var registerInfo = $form.serializeJSON();
        var url = YOUNG_DOMAIN + "/user/v1/register.do";
        $.ajax({
            url : url,
            type : "post",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(registerInfo),
            success : function(data) {
                if (null != data && data.rspCd == "00000") {
                	toastr.success("注册成功！", "success");
                    // 成功跳转到登录页
                	setTimeout(function () {
                        window.location.href = YOUNG_DOMAIN + "/login.html";
                    }, 1000);
                }else{
                    toastr.error(data.rspInf, "注册失败");
                }
            }
        });
    });
    
    function sendIdentifyCode(){
    	// 设置二分钟的倒计时
    	var currentTime = 120;
        var text = "";
        $("#identify").attr('disabled',true)
        var interval = setInterval(function(){
          currentTime -= 1
          text = currentTime + 's'
          $("#identify").text(text)
          if (currentTime <= 0) {
            clearInterval(interval)
            text = '重新发送'
            currentTime = 120
            $("#identify").text(text)
            $("#identify").attr('disabled',false)
          }
        }, 1000)
    };
});