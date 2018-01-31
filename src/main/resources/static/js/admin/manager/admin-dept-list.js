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

            },
            onClick: function(event,treeId,treeNode) {
                window.location.href = "/admin/organization?organizationId="+treeNode.id;
            }
        }
    };

    $.ajax({
        url : "/admin/organization/tree",
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