/**
 * 个人中心页面JS
 */
$(function() {
    // 初始化table
    var $table = $('#deptpost_table');
    var $form = $("#edit-password-form");
    initUserInfo($form);
    $form.on("click", "#editSelfpwd", function() {
        if ($("#usrPwd").val() != $("#usrPwds").val()) {
            alert("俩次输入的密码不一致");
        } else {
            var userInfo = $form.serializeJSON();
            $.postJson(MNG_DOMAIN + "/user/v1/updateSelfPwd.do", userInfo,function(data) {
                if (data.rspCd == "00000") {
                    toastr.success("编辑个人密码成功", "编辑成功");
                } else {
                    toastr.error(data.rspInf, "编辑失败");
                }
            });
        }
    });
});
function initUserInfo($form) {
    // 查询当前用户个人信息
    $.postJson(MNG_DOMAIN + "/user/v1/userInfo.do", {}, function(response) {
        var userInfo = response.userInfo;
        console.log(userInfo);
        $("#oldusrPwd").val(userInfo.usrPwd);
    });
}

function updateSelfInfo($form) {

}
