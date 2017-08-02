$(function () {
    loadPageSubmit();
});

/**
 * 表单提交方式分页
 */
function loadPageSubmit() {
    //显示分页的容器
    var paging = $("#paging");
    //总页数
    var pages = $("#pages").val();
    //当前页
    var pageNum = $("#pageNum").val();
    //连续显示分页数
    var groups = 6;

    laypage({
        cont: paging, //容器,仅支持id名\原生DOM对象,jquery对象
        pages: pages,   //总页数
        skip:true,  //是否开启跳页
        groups: groups,   //连续显示分页数
        first:'首页',    //首页
        last: '尾页',    //尾页
        skin:'#1E9FFF',    //皮肤
        curr: pageNum,     //当前页
        jump:function(obj,first){   //触发分页后的回调
            //触发分页后的回调,并传递当前页obj.cuur
            if(!first){
                var cuur = obj.curr;    //获取当前页
                $("#pageNum").val(cuur);
                $('form').submit();
            }
        }
    });
}

/**
 * Ajax方式分页
 */
function loadPage(catId,callback) {
    //显示分页的容器
    var paging = $("#paging");
    //当前页
    var pageNum = $("#pageNum").val();
    // //总页数
    var pages = $("#pages").val();

    //连续显示分页数
    var groups = 6;

    laypage({
        cont: paging, //容器,仅支持id名\原生DOM对象,jquery对象
        pages: pages,   //总页数
        // skip:true,  //是否开启跳页
        groups: groups,   //连续显示分页数
        first:'首页',    //首页
        last: '尾页',    //尾页
        // skin:'#1E9FFF',    //皮肤
        curr: pageNum || 1,     //当前页
        jump:function(obj,first){   //触发分页后的回调
            //触发分页后的回调,并传递当前页obj.cuur
            if(!first){
                // var cuur = obj.curr;    //获取当前页
                callback(catId, obj.curr);
            }
        }
    });
}