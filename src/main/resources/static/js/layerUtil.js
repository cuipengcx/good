var loading;

/**
 * 成功提示
 * @param value
 */
function succeedMessage(value,time){
	layer.msg(value,{icon:1,time:time});
}

/**
 * 错误提示
 * @param value
 */
function errorMessage(value,time){
	layer.msg(value,{icon:2,time:time});
}

/**
 * 警告提示
 * @param value
 */
function warningMessage(value,time){
    layer.msg(value,{icon:3,time:time});
}

/**
 * 锁定提示
 * @param value
 */
function lockMessage(value,time){
    layer.msg(value,{icon:4,time:time});
}

/**
 * 关闭提示
 * @param value
 */
function closedMessage(value,time){
    layer.msg(value,{icon:5,time:time});
}

/**
 * 打开提示
 * @param value
 */
function openMessage(value,time){
    layer.msg(value,{icon:6,time:time});
}



/**
 * 遮罩层
 * @param value
 */
function loadMessage(value,time){
	if(value == '' || value == undefined){
		value = "加载中";
	}
	loading = layer.msg(value,{icon: 16,time:0,shade: [0.8, '#fff']});
	return loading;
}

/**
 * 关闭弹层
 * @param index
 */
function closeMessage(index){
	layer.close(index);
}

