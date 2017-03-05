
/*用户-删除*/
function good_del(obj, url){
    layer.confirm('确认要删除吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"DELETE",
            dataType:"json",
            url: url,
            data:{
                "timestamp":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
                    $(obj).parents("tr").remove();
                    var total = $("#total").text();
                    $("#total").text(parseInt(total)-1);
                    succeedMessage(data.responseText);
                },
                404 : function(data){
                    errorMessage(data.responseText);
                },
                500 : function(){
                    errorMessage('系统错误!');
                }
            }
        });
    });
}

/**
 * 禁用|启用
 * @param obj
 * @param id
 */
function good_status(obj,urls,isLock){
    var msg = "确认要启用吗?";
    if(isLock == "true"){
        msg = "确认要禁用吗?";
    }
    layer.confirm(msg,function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"PUT",
            dataType:"json",
            url: urls,
            data:{
                "timestamp":new Date().getTime()
            },
            statusCode: {
                204 : function(){
                    if(isLock == "true"){
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="good_status(this,\''+urls+'\',\'false\')" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
                        $(obj).remove();
                        closedMessage('已禁用!');
                    }else {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="good_status(this,\''+urls+'\',\'true\')" href="javascript:;" title="禁用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        openMessage('已启用!');
                    }
                },
                404 : function(data){
                    errorMessage(data.responseText);
                },
                500 : function(){
                    errorMessage('系统错误!');
                }
            }
        });
    });
}


/**
 * 查看用户详情
 * @param title 标题
 * @param url   访问地址
 * @param w     弹窗宽度
 * @param h     弹窗高度
 */
function good_view(title,url,w,h) {
    layer_show(title,url,w,h);
}

/*密码-修改*/
function change_password(title,url,w,h){
    layer_show(title,url,w,h);
}

/*发起项目列表*/
function list_start(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*支持项目列表*/
function list_support(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*募集流水*/
function list_get(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*捐赠流水*/
function list_pay(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
