/**
 * 认证审核预览
 * @param title
 * @param url
 * @param w
 * @param h
 */
function authentication_info_check(obj, url, w, h) {
    var _this = $(obj);
    layer.confirm('审核认证？', {
            btn: ['通过','不通过','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type:"PUT",
                dataType:"json",
                url: url,
                data:{
                    "isAccess": true,
                    "timestamp":new Date().getTime()
                },
                statusCode: {
                    200 : function(data){
                        $("#status").html("<span class='label radius label-success'>通过</span>");
                        _this.remove();
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
        },
        function(){
            $.ajax({
                type:"PUT",
                dataType:"json",
                url: url,
                data:{
                    "isAccess": false,
                    "timestamp":new Date().getTime()
                },
                statusCode: {
                    200 : function(data){
                        $("#status").html("<span class='label radius label-danger'>拒绝</span>");
                        _this.remove();
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
 * 查看详情
 * @param title
 * @param url
 * @param w
 * @param h
 */
function authentication_info_view(title, url, w, h) {
    // var index = layer.open({
    //     type: 2,
    //     title: title,
    //     content: url
    // });
    // layer.full(index);
    layer_show(title, url, w, h);
}