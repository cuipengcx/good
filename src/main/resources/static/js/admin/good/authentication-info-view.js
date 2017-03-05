$(function () {
    
});


function saveCheck(obj, url, flag) {
    var msg = "";
    if(flag == "true"){
        msg = "确定要通过吗?";
    }else {
        msg = "确定要拒绝吗?";
    }
    layer.confirm(msg, function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"PUT",
            dataType:"json",
            url: url,
            data:{
                "isAccess": flag,
                "timestamp":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
                    succeedMessage(data.responseText);
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.location.reload();
                    parent.layer.close(index);
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