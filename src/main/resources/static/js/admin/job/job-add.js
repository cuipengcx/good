$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-job-add").validate({
        rules:{
            jobName:{
                required:true,
                minlength:4,
                maxlength:16,
                remote: {
                    url: "/admin/manager/user/isExist",
                    type: "get",
                    data: {
                        jobName: function () {
                            return $("#jobName").val();
                        }
                    }
                }
            },
            jobGroup:{
                required:true
            },
            cron:{
                required:true
            },
            beanClass:{
                required:true
            },
            methodName:{
                required:true
            }
        },
        messages : {
            jobName : {
                remote: "该任务名称已经被注册！"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).find(":submit").attr("disabled", true);
            $(form).ajaxSubmit({
                type: 'post',
                url: "/admin/job",
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