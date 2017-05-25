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
            onRightClick : onRightClick   //右键事件
        }
    };

    $.ajax({
        url : "/admin/content/cat/tree",
        type : "GET",
        async : true,
        success : function(data) {
            $.fn.zTree.init($("#treeDemo"), setting, data);
            zTree = $.fn.zTree.getZTreeObj("treeDemo");
        },
        error : function() {
            errorMessage("加载失败!");
        }
    });
}

/**
 * 右键事件
 * @param event
 * @param treeId
 * @param treeNode
 * @constructor
 */
function onRightClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    } else if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        showRMenu("node", event.clientX, event.clientY);
    }
}

/**
 * 显示右键菜单
 * @param type
 * @param x
 * @param y
 */
function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    console.log(type);
    if (type=="root") {
        $("#m_del").hide();
    } else {
        $("#m_del").show();
    }
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

    $("body").bind("mousedown", onBodyMouseDown);
}

/**
 * 隐藏右键菜单
 */
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}

/**
 * 点击树以外的地方隐藏右键菜单
 * @param event
 */
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}

// var addCount = 1;
/**
 * 新增节点
 * @type {number}
 */
function addTreeNode() {
    hideRMenu();
    var newNode = { name:"新建节点"};
    // var newNode = { name:"增加" + (addCount++)};
    if (zTree.getSelectedNodes()[0]) {
        newNode.checked = zTree.getSelectedNodes()[0].checked;
        zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
    } else {
        zTree.addNodes(null, newNode);
    }
}

/**
 * 移除节点
 */
function removeTreeNode() {
    hideRMenu();
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg)==true){
                zTree.removeNode(nodes[0]);
            }
        } else {
            zTree.removeNode(nodes[0]);
        }
    }
}

/**
 * 修改节点
 */
function editTreeNode() {
    hideRMenu();
}