$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-job-edit").validate({
        rules:{
            jobGroup:{
                required:true,
                remote: {
                    url: "/admin/job/isExist",
                    type: "get",
                    data: {
                        jobName: function () {
                            return $("#jobName").val();
                        },
                        jobGroup: function () {
                            return $("#jobGroup").val();
                        },
                        id: function () {
                            return $("#id").val();
                        }
                    }
                }
            },
            jobName:{
                required:true,
                remote: {
                    url: "/admin/job/isExist",
                    type: "get",
                    data: {
                        jobName: function () {
                            return $("#jobName").val();
                        },
                        jobGroup: function () {
                            return $("#jobGroup").val();
                        },
                        id: function () {
                            return $("#id").val();
                        }
                    }
                }
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
            },
            isAsync: {
                required:true
            }
        },
        messages : {
            jobName : {
                remote: "该任务名称已经被注册！"
            },
            jobGroup: {
                remote: "该任务分组已经被注册！"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            // $(form).find(":submit").attr("disabled", true);
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
                        // $(form).find(":submit").attr("disabled", false);
                        errorMessage(data.message);
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
function initValidate(value) { //远程调度
    if (value == '0') {
        $("#beanClassDiv").hide();
        $("#methodNameDiv").hide();
        $("#remoteUrlDiv").show();
        // $("#remoteRequestMethodDiv").show();
        //动态改变验证规则
        $("#beanClass").rules("remove");
        $("#methodName").rules("remove");
        $("#remoteUrl").rules("add",{required: true,url: true});
        // $("#remoteRequestMethod").rules("add",{required: true});

        //动态改变placeholder
        $("#params").attr("placeholder", "请输入参数 如: key1=100,key2=200,key3=300");
    } else if(value == '1'){  //本地调度
        $("#beanClassDiv").show();
        $("#methodNameDiv").show();
        $("#remoteUrlDiv").hide();
        // $("#remoteRequestMethodDiv").hide();
        //动态改变验证规则
        $("#beanClass").rules("add",{required: true});
        $("#methodName").rules("add",{required: true});
        $("#remoteUrl").rules("remove");
        // $("#remoteRequestMethod").rules("remove");

        //动态改变placeholder
        $("#params").attr("placeholder", "请输入参数");
    }
}