$(function () {

    $("#form-change-password").validate({
        rules:{
            password:{
                required:true
            },
            password2:{
                required:true,
                equalTo: "#password"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).find(":submit").attr("disabled", true);
            $(form).ajaxSubmit({
                type: 'PUT',
                url: "/admin/good/update-password/"+$("#guId").val(),
                dataType:"json",
                success: function(data){
                    if(data.status == "success"){
                        succeedMessage(data.message);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.location.reload();
                        parent.layer.close(index);
                    }else {
                        $(form).find(":submit").attr("disabled", false);
                        warningMessage(data.message);
                    }
                }
            });
            return false; // 非常重要，如果是false，则表明是不跳转，在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
        }
    });

});