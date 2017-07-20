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
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
    layer_show(title,url,w,h);
}
/*管理员-角色-删除*/
function admin_role_del(obj, url){
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
                403: function () {
                    errorMessage("该角色下有用户存在，不允许删除!");
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
/*管理员-角色-编辑*/
function admin_role_edit(title,url,w,h){
    layer_show(title,url,w,h);
}

/**
 * 角色授权
 * @param title
 * @param url
 * @param w
 * @param h
 */
function admin_role_permission(title,url,w,h) {
    layer_show(title,url,w,h);
}