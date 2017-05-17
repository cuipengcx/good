/**
 * 添加调度
 * @param title
 * @param url
 * @param w
 * @param h
 */
function job_add(title, url, w, h) {
    layer_show(title,url,w,h);
}

/**
 * 编辑调度
 * @param title
 * @param url
 * @param w
 * @param h
 */
function job_edit(title, url, w, h) {
    layer_show(title,url,w,h);
}

/**
 * 删除调度
 * @param obj
 * @param url
 */
function job_del(obj, url) {
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
