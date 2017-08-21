$(function () {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, "/admin/permission/list", colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});

var Menu = {
    id: "menuTable",
    seItem: null,	//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '菜单ID', field: 'id', visible: false, align: 'center', valign: 'middle', width: '7%'},
        {title: '权限名称', field: 'name', visible: false, align: 'center', valign: 'middle', width: '15%'},
        {title: '父节点名称', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '15%', formatter: function(item, index){
            return item.parentName == null ? '' : item.parentName;
        }},
        {title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true, formatter: function(item, index){
            return item.icon == null ? '' : '<i class="Hui-iconfont">'+item.icon+'</i>';
        }},
        {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true, formatter: function(item, index){
            if(item.type === "0"){
                return '<span class="label label-default radius">目录</span>';
            }
            if(item.type === "1"){
                return '<span class="label label-primary radiu">菜单</span>';
            }
            if(item.type === "2"){
                return '<span class="label label-secondary radius">按钮</span>';
            }
        }},
        {title: '访问URL', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '17%'},
        {title: '权限标识', field: 'perms', align: 'center', valign: 'middle', sortable: true, width: '15%'},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

/**
 * 检查是否选中
 */
Menu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        errorMessage("请先选中一条记录！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};

/**
 * 搜索
 */
Menu.search = function () {
    var queryData = {};

    queryData['menuName'] = $("#menuName").val();
    Menu.table.refresh({query: queryData});
};


/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 */
/*管理员-权限-添加*/
function admin_permission_add(title,url,w,h){
    layer_show(title,url,w,h);
}

/*管理员-权限-删除*/
function admin_permission_del(obj, url){
    if(Menu.check()){
        layer.confirm('确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                type:"DELETE",
                dataType:"json",
                url: url+"/"+Menu.seItem.id,
                data:{
                    "timestamp":new Date().getTime()
                },
                statusCode: {
                    200 : function(data){
                        window.location.reload();
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
}

/*管理员-权限-编辑*/
function admin_permission_edit(title,url,w,h){
    if(Menu.check()){
        layer_show(title,url+"/"+Menu.seItem.id,w,h);
    }
}