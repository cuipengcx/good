$(function () {
    $("#form-content-edit").validate({
        rules:{
            title:{
                required:true,
                minlength:5,
                maxlength:200
            },
            titleDesc:{
                required:true,
                minlength:10,
                maxlength:500
            },
            textarea:{
                minlength:100,
                maxlength:1000
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
                type: 'POST',
                url: "/admin/content/update",
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