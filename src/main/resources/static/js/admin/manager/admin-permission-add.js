$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-admin-permission-add").validate({
        rules:{
            // username:{
            //     required:true,
            //     minlength:4,
            //     maxlength:16,
            //     remote: {
            //         url: "/admin/manager/user/isExist",
            //         type: "get",
            //         data: {
            //             username: function () {
            //                 return $("#username").val();
            //             }
            //         }
            //     }
            // },
            type:{
                required:true
            },
            name:{
                required:true
            },
            parentId:{
                required:true
            },
            url:{
                required:true
            },
            perms: {
                required: true
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
                url: "/admin/permission",
                dataType:"json",
                success: function(data){
                    if(data.status == "success"){
                        succeedMessage(data.message);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.location.reload();
                        parent.layer.close(index);
                    }else {
                        $(form).find(":submit").attr("disabled", false);
                        errorMessage(data.message);
                    }
                }
            });
            return false; // 非常重要，如果是false，则表明是不跳转，在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
        }
    });


    //页面初始化的时候初始化显示隐藏表单元素
    var checkedValue = $("input[name='type']:checked").val();
    if (checkedValue == '0') {
        $(".parentDiv").hide();
        $(".URLDiv").hide();
        $(".permsDiv").hide();
        $(".iconDiv").show();
        $("#parentName").val("");
        //动态改变验证规则
        $("#parentId").rules("remove");
        $("#url").rules("remove");
        $("#perms").rules("remove");
    } else if(checkedValue == '1'){
        $(".parentDiv").show();
        $(".URLDiv").show();
        $(".permsDiv").show();
        $(".sortDiv").show();
        $(".iconDiv").hide();
        //动态改变验证规则
        $("#parentId").rules("add",{required: true});
        $("#url").rules("add",{required: true});
        $("#perms").rules("add",{required: true});
    } else if(checkedValue == '2'){
        $(".parentDiv").show();
        $(".URLDiv").hide();
        $(".permsDiv").show();
        $(".iconDiv").hide();
        //动态改变验证规则
        $("#parentId").rules("add",{required: true});
        $("#url").rules("remove");
        $("#perms").rules("add",{required: true});
    }

    //为iCheck绑定选中事件
    $("input[name='type']").on('ifChecked', function(event){
        var value = $(this).val();
        if (value == '0') {
            $(".parentDiv").hide();
            $(".URLDiv").hide();
            $(".permsDiv").hide();
            $(".iconDiv").show();
            $("#parentName").val("");
            //动态改变验证规则
            $("#parentId").rules("remove");
            $("#url").rules("remove");
            $("#perms").rules("remove");
        } else if(value == '1'){
            $(".parentDiv").show();
            $(".URLDiv").show();
            $(".permsDiv").show();
            $(".iconDiv").hide();
            //动态改变验证规则
            $("#parentId").rules("add",{required: true});
            $("#url").rules("add",{required: true});
            $("#perms").rules("add",{required: true});
            getPermissionList(value);
        } else if(value == '2'){
            $(".parentDiv").show();
            $(".URLDiv").hide();
            $(".permsDiv").show();
            $(".iconDiv").hide();
            //动态改变验证规则
            $("#parentId").rules("add",{required: true});
            $("#url").rules("remove");
            $("#perms").rules("add",{required: true});
            getPermissionList(value);
        }
    });

    /**
     * 根据类型获取上级节点列表
     * 因为左测菜单目前只有一级目录，所以添加权限节点选择类型为目录时是不需要选择上级节点
     * @param typeValue
     */
    function getPermissionList(typeValue) {
        var type = "";
        if(typeValue == '1'){
            type = "0";
        }else if(typeValue == '2'){
            type = "1";
        }
        $.ajax({
            type:"GET",
            dataType:"json",
            url: "/admin/permission/getPermissionList?type="+type,
            data:{
                "timestamp":new Date().getTime()
            },
            success: function (data) {
                if (data.status == "success") {
                    $("#parentId").html("");
                    $("#parentName").val("");
                    var opt = "<option value=''>--请选择--</option>";
                    $.each(data.permissionList, function (i, v) {
                        opt += "<option value='" + v.id + "'>" + v.name + "</option>"
                    });
                    $("#parentId").html(opt);
                }
            }
        });
    }

    //为parentName赋值
    $("#parentId").change(function () {
       $("#parentName").val($(this).find("option:selected").text())
    });
});