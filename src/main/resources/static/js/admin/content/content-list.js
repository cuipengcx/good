/*内容-添加*/
function content_add(title,url, catId){

    if(catId == null){
        errorMessage("请选择分类");
        return false;
    }
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*内容-编辑*/
function content_edit(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*产品-删除*/
function content_del(obj,url){
    layer.confirm('确认要删除吗？',function(index){
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
 * 初始化树
 */
function inintTree(){
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: false,
            selectedMulti: false
        },
        data : {
            simpleData : {
                enable : true,
                idKey : "id",
                pIdKey : "parentId",
                rootPId : 0
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    //demoIframe.attr("src",treeNode.file + ".html");
                    return true;
                }
            },
            onClick: function(event,treeId,treeNode) {
                window.location.href = "/admin/content?catId="+treeNode.id;
            }
        }
    };

    $.ajax({
        url : "/admin/content/cat/tree",
        type : "GET",
        async : true,
        success : function(data) {
            $.fn.zTree.init($("#treeDemo"), setting, data);
        },
        error : function() {
            errorMessage("加载失败!");
        }
    });
}