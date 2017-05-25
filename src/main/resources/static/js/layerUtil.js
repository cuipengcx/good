var loading;

/**
 * 默认提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function goodMessage(value,time){
	layer.msg(value,{icon:1,time:time});
}

/**
 * 成功提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function succeedMessage(value,time){
	layer.msg(value,{icon:1,time:time});
}

/**
 * 错误提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function errorMessage(value,time){
	layer.msg(value,{icon:2,time:time,offset:'50%',shift: 6});
}

/**
 * 疑问提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function askMessage(value,time){
	layer.msg(value,{icon:3,time:time,offset:'50%',shift: 6});
}

/**
 * 锁定提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function lockMessage(value,time){
    layer.msg(value,{icon:4,time:time,offset:'50%',shift: 6});
}

/**
 * 难过提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function sadMessage(value,time) {
	layer.msg(value,{icon:5,time:time,offset:'50%',shift: 6});
}

/**
 * 微笑提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function smileMessage(value,time) {
	layer.msg(value,{icon:6,time:time});
}

/**
 * 警告提示
 * @param value 提示内容
 * @param time  显示时间(ms), 默认3ms
 */
function warningMessage(value,time) {
	layer.msg(value,{icon:7,time:time,offset:'50%',shift: 6});
}

/**
 * 遮罩层
 * @param value
 */
function loadingMessage(value,time){
	if(value == '' || value == undefined){
		value = "加载中";
	}
	loading = layer.msg(value,{icon: 16,time:0,shade: 0.1});
	return loading;
}

/**
 * 关闭弹层||遮罩层
 * @param index
 */
function closeMessage(index){
	layer.close(index);
}

