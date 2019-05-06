/**
 * 打开运营公司
 */
function initCompanHtml($tree, deptmentInfo){
    $.get(YOUNG_DOMAIN + "/admin/dialog/add-compan.html",function(html) {
        layer.open({
            id : "add-compan-dialog",
            type : 1,
            title : "运营公司信息补全",
            content : html,
            maxmin: true,
            zIndex : 1000,
            btn : [ "确定", "取消" ],
            area : [ "80%", "90%" ],
            success : function() {
                $('#mngerTxt').val(deptmentInfo.mngerNm);
                $('#cotldNm').val(deptmentInfo.deparNm);
                displayMap();
                initDate();
            },
            yes : function(index) {
                var companInfo = {
                    cotldNm : deptmentInfo.deparNm,
                    prov : $('#prov').val(),
                    city : $('#city').val(),
                    district : $('#district').val(),
                    workAdrr : $('#workAdrr').val(),
                    workPhone : $('#workPhone').val(),
                    mnger : deptmentInfo.mngerNm,
                    mngUsrNo : deptmentInfo.mngerUsrNo,
                    creDt : $('#creDt').val(),
                    lngUde : $('#lngUde').val(),
                    latUde : $('#latUde').val()
                };
                deptmentInfo.companiesPo=companInfo;
                addSubmit($tree, deptmentInfo);
                layer.close(index);
            }
        });
    });
}

function editCompanHtml($jstree, deptmentInfo){
    $.get(YOUNG_DOMAIN + "/resources/html/admin/dialog/edit-compan.html",function(html) {
        layer.open({
            id : "edit-compan-dialog",
            type : 1,
            title : "运营公司信息修改",
            content : html,
            maxmin: true,
            zIndex : 1000,
            btn : [ "确定", "取消" ],
            area : [ "80%", "90%" ],
            success : function() {
                $('#mngerTxt').val(deptmentInfo.mngerNm);
                $('#cotldNm').val(deptmentInfo.deparNm);
                displayMap();
                initDate();
                initCompanInfo(deptmentInfo);
            },
            yes : function(index) {
                var companInfo = {
                    cotldNm : deptmentInfo.deparNm,
                    prov : $('#prov').val(),
                    city : $('#city').val(),
                    district : $('#district').val(),
                    workAdrr : $('#workAdrr').val(),
                    workPhone : $('#workPhone').val(),
                    mnger : $('#mngerNm').val(),
                    mngUsrNo : $('#mngerUsrNo').val(),
                    creDt : $('#creDt').val(),
                    lngUde : $('#lngUde').val(),
                    latUde : $('#latUde').val(),
                    cotldSts : $('#horizontal input[name="deparSts"]:checked ').val()
                };
                deptmentInfo.fDeparNo = $('#fDeparNo').val();
                deptmentInfo.deparSts = $('#horizontal input[name="deparSts"]:checked ').val();
                deptmentInfo.mngerUsrNo = $('#mngerUsrNo').val();
                deptmentInfo.mngerNm = $('#mngerNm').val();
                deptmentInfo.companiesPo=companInfo;
                updSubmit($jstree, deptmentInfo);
                layer.close(index);
            }
        });
    });
}

function initCompanInfo(deptmentInfo){
    $.postJson(YOUNG_DOMAIN + "/compan/v1/qryCompanInfo.do", {cotldNo:deptmentInfo.typeNo}, function(data) {
        if (data.rspCd == "00000") {
            var $form = $("#horizontal");
            $('#provCity').val(data.prov+' '+data.city+' '+data.district);
            $('#prov').val(data.prov);
            $('#city').val(data.city);
            $('#district').val(data.district);
            $('#workAdrr').val(data.workAdrr);
            $('#workPhone').val(data.workPhone);
            $('#lngUde').val(data.lngUde);
            $('#latUde').val(data.latUde);
            if (deptmentInfo.deparSts.trim() == "S") {
                $form.find("#S-status").prop("checked", true);
            } else {
                $form.find("#F-status").prop("checked", true);
            }
            initAddDeptmentSelect(deptmentInfo);
            initUserSelect({
                usrNo : deptmentInfo.mngerUsrNo,
                usrNm : deptmentInfo.mngerNm
            });
        } else {
            layer.msg("查询失败" + data.rspCd + data.rspInf);
        }
    });
}
