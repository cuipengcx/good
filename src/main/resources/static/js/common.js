
/**
 * 公共方法
 * @param obj
 * @param id
 */
/* 替换被拦截的html代码,请遵循JAVA定义规则 */
function replaceAllHTML(content){

	if(content == null || content == '')
		return '';

	content = content.replaceAll("#", "html_j");
	content = content.replaceAll("<img", "html_i");
	content = content.replaceAll("<a", "html_a");
	content = content.replaceAll("<frame", "html_f");

	return content;
}

/**
 * 替换全部
 */
String.prototype.replaceAll  = function(s1,s2){     
    return this.replace(new RegExp(s1,"gm"),s2);     
}

/**
 * 全局的ajax访问，处理ajax清求时sesion超时和无权限提示
 */
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete:function(XMLHttpRequest,textStatus){
		//通过XMLHttpRequest取得响应头，Session-Status和No-Permission，
		var status = XMLHttpRequest.status;
		var sessionStatus=XMLHttpRequest.getResponseHeader("Session-Status");
		var noPermission=XMLHttpRequest.getResponseHeader("No-Permission");
		var sessionStatusJson = eval('('+sessionStatus+')');
		var noPermissionJson = eval('('+noPermission+')');

		//session超时
		if(status == '408' && sessionStatusJson != '' && sessionStatusJson.code == '408'){
			errorMessage("登录超时，请重新登录！");
		}
		if(status == '403' && noPermissionJson != '' && noPermissionJson.code == '403'){
			errorMessage('没有操作权限！');
		}
	}
});

