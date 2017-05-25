$(function () {

});

/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 */
/*管理员-增加*/
function admin_add(title,url,w,h){
    layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj, url){
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
/*管理员-编辑*/
function admin_edit(title,url,w,h){
    layer_show(title,url,w,h);
}

/**
 * 禁用|启用
 * @param obj
 * @param id
 */
function admin_status(obj,urls,isLock){
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
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_status(this,\''+urls+'\',\'false\')" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
                        $(obj).remove();
                        sadMessage('已禁用!');
                    }else {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_status(this,\''+urls+'\',\'true\')" href="javascript:;" title="禁用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        smileMessage('已启用!');
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