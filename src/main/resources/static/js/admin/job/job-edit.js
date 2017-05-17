$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-job-edit").validate({
        rules:{
            jobName:{
                required:true
            },
            jobGroup:{
                required:true
            },
            cron:{
                required:true
            },
            isLocal:{
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
            // jobName : {
            //     remote: "该任务名称已经被注册！"
            // }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            $(form).find(":submit").attr("disabled", true);
            $(form).ajaxSubmit({
                type: 'PUT',
                url: "/admin/job/"+$("#id").val(),
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


    //页面初始化的时候初始化显示隐藏表单元素
    var checkedValue = $("input[name='isLocal']:checked").val();
    initValidate(checkedValue);

    //为iCheck绑定选中事件
    $("input[name='isLocal']").on('ifChecked', function(event){
        var value = $(this).val();
        initValidate(value);
    });

});


/**
 * 根据条件初始化校验规则
 */
function initValidate(value) {
    if (value == '0') {
        $("#beanClassDiv").hide();
        $("#methodNameDiv").hide();
        $("#remoteUrlDiv").show();
        //动态改变验证规则
        $("#beanClass").val("").rules("remove");
        $("#methodName").val("").rules("remove");
        $("#remoteUrl").rules("add",{required: true,url: true});
    } else if(value == '1'){
        $("#beanClassDiv").show();
        $("#methodNameDiv").show();
        $("#remoteUrlDiv").hide();
        //动态改变验证规则
        $("#beanClass").rules("add",{required: true});
        $("#methodName").rules("add",{required: true});
        $("#remoteUrl").val("").rules("remove");
    }
}