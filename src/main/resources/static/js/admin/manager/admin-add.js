$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-admin-add").validate({
        rules:{
            username:{
                required:true,
                minlength:4,
                maxlength:16,
                remote: {
                    url: "/admin/manager/user/isExist",
                    type: "get",
                    data: {
                        username: function () {
                            return $("#username").val();
                        }
                    }
                }
            },
            password:{
                required:true
            },
            password2:{
                required:true,
                equalTo: "#password"
            },
            sex:{
                required:true
            },
            mobilePhone:{
                required:true,
                isPhone:true
            },
            email:{
                required:true,
                email:true
            },
            roleId:{
                required:true
            }
        },
        messages : {
            username : {
                remote: "该用户名已经被注册！"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).ajaxSubmit({
                type: 'post',
                url: "/admin/manager/user",
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
        }
    });
});