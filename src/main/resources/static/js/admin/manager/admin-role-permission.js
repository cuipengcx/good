$(function () {
    $("#form-admin-role-permission").validate({
        rules:{
            permissionIds:{
                required:true
            }
        },
        messages : {
            permissionIds : {
                required: "请至少选择一个！"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).ajaxSubmit({
                type: 'PUT',
                url: "/admin/role/"+$("#rid").val()+"/permission",
                dataType:"json",
                success: function(data){
                    if(data.status == "success"){
                        succeedMessage(data.message);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.location.reload();
                        parent.layer.close(index);
                    }else {
                        errorMessage(data.message);
                    }
                }
            });
            return false; // 非常重要，如果是false，则表明是不跳转，在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
        },
        showErrors: function(errorMap, errorList) {
            if(errorList.length>0){
                sadMessage(errorList[0].message);
                return false;
            }
        }
    });
});