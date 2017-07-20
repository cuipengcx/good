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

/**
 * 暂停
 * @param obj
 * @param url
 */
function job_pause(obj, url, id) {
    var $this = $(obj);
    layer.confirm('确认要暂停吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"PUT",
            dataType:"json",
            url: url,
            data:{
                "timestamp":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
                    $("#status" + id).html("<span class='label radius label-danger'>禁用</span>");
                    $this.replaceWith("<a style='text-decoration:none' onclick=\"job_resume(this, '/admin/job/resume/"+id+"','"+id+"');\" href='javascript:;\' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a>");
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
 * 恢复
 * @param obj
 * @param url
 */
function job_resume(obj, url, id) {
    var $this = $(obj);
    layer.confirm('确认要恢复吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"PUT",
            dataType:"json",
            url: url,
            data:{
                "timestamp":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
                    $("#status" + id).html("<span class='label radius label-success'>启用</span>");
                    $this.replaceWith("<a style='text-decoration:none' onclick=\"job_pause(this, '/admin/job/pause/"+id+"','"+id+"');\" href='javascript:;\' title='暂停'><i class='Hui-iconfont'>&#xe6e5;</i></a>");
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
 * 运行一次
 * @param obj
 * @param url
 */
function job_run(obj, url) {
    layer.confirm('确认要运行吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"PUT",
            dataType:"json",
            url: url,
            data:{
                "timestamp":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
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
 * 查看调度任务详情
 * @param title
 * @param url
 * @param w
 * @param h
 */
function job_view(title, url, w, h) {
    layer_show(title,url,w,h);
}

/**
 * 调度日志
 * @param title
 * @param url
 */
function job_log(title, url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}