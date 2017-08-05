$(function () {
    $("#form-password").validate({
        rules:{
            oldPassword:{
                required:true
            },
            newPassword:{
                required:true
            },
            password2:{
                required:true,
                equalTo: "#newPassword"
            }
        },
        // messages : {
        //     username : {
        //         remote: "该用户名已经被注册！"
        //     }
        // },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).ajaxSubmit({
                type: 'post',
                url: "/admin/password",
                dataType:"json",
                success: function(data){
                    if(data.status == "success"){
                        succeedMessage(data.message);
                        var index = parent.layer.getFrameIndex(window.name);
                        // parent.location.reload();
                        parent.location.href = "/admin/logout";
                        parent.layer.close(index);
                    }else {
                        errorMessage(data.message);
                    }
                }
            });
            return false; // 非常重要，如果是false，则表明是不跳转，在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
        }
    });
});