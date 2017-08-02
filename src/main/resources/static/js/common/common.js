/**
 * 是否相等
 */
function equals(obj1, obj2) {
	if (obj1 == obj2)
		return true;

	if (typeof (obj1) == "undefined" || obj1 == null
		|| typeof (obj1) != "object")
		return false;

	if (typeof (obj2) == "undefined" || obj2 == null
		|| typeof (obj2) != "object")
		return false;

	var length1 = 0;
	var length2 = 0;

	for ( var ele in obj1) {
		length1++;
	}

	for ( var ele in obj2) {
		length2++;
	}

	if (length1 != length2)
		return false;

	if (obj1.constructor == obj2.constructor) {
		for ( var ele in obj1) {
			if (typeof (obj1[ele]) == "object") {
				if (!equals(obj1[ele], obj2[ele]))
					return false;
			} else if (typeof (obj1[ele]) == "function") {
				if (!obj1[ele].toString().equals(obj2[ele].toString()))
					return false;
			} else if (obj1[ele] != obj2[ele])
				return false;
		}

		return true;
	}

	return false;
}

/**
 * 是否包含对象
 *
 * @param obj
 * @returns {Boolean}
 */
Array.prototype.inArray = function(obj) {
	for (i = 0; i < this.length; i++) {
		if (equals(this[i], obj))
			return true;
	}

	return false;
}

/**
 * 查找对象在数组中的位置
 * @param obj
 * @returns
 */
Array.prototype.indexOf = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		if (equals(this[i], obj))
			return i;
	}

	return -1;
}

/**
 * 方法:removeObject(obj)
 * 功能:根据元素值删除数组元素.
 * 参数:obj
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObject = function(obj) {
	var index = this.indexOf(obj);
	if (index > -1) {
		this.splice(index, 1);
	}

	return this;
};

/**
 * 方法:removeObjectById(id)
 * 功能:根据元素值删除数组元素.
 * 参数:id
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObjectById = function(id) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i].id == id){
			return this.splice(i, 1);
		}
	}

	return this;
};

/**
 * 方法:removeObjectAtIndex(dx)
 * 功能:根据元素位置值删除数组元素.
 * 参数:dx
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObjectAtIndex = function(dx) {
	if (isNaN(dx) || dx < this.length || dx > this.length) {
		return this;
	}

	return this.splice(dx, 1);
};

/**
 * 方法:findObjectById(id)
 * 功能:根据id查找数组元素.
 * 参数:id
 * 返回:数组元素
 */
Array.prototype.findObjectById = function(id) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i].id == id){
			return this[i];
		}
	}

	return null;
};

/**
 * 删除所有空白
 * @returns
 */
String.prototype.trim = function() {
	return this.replace(/\s+/g, "");
}

/**
 * 是否为空白
 * @returns {Boolean}
 */
function strIsBlank(str2check) {
	return !str2check;
}

/**
 * 删除左边的空白
 *
 * @returns
 */
String.prototype.lTrim = function() {
	return this.replace(/(^\s+)/g, "");
}

/**
 * 删除右边的空白
 *
 * @returns
 */
String.prototype.rTrim = function() {
	return this.replace(/(\s+$)/g, "");
}

/**
 * 是否有效的手机号码
 *
 * @returns
 */
String.prototype.test = function() {
	return (new RegExp(/^([\S^'^‘^’]{6,20})$/)
		.test(this));
}

/**
 * 是否有效的手机号码
 *
 * @returns
 */
String.prototype.isMobileNum = function() {
	return (new RegExp(/^((13[0-9])|(14[4,7])|(15[^4,\D])|(17[6-8])|(18[0-9]))(\d{8})$/)
		.test(this));
}

/**
 * 是否有效的邮箱
 *
 * @returns
 */
String.prototype.isEmail = function() {
	return (new RegExp(
//			/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.([a-zA-Z0-9_-]){2,3}){1,2})$/)
		/^([\w-_])+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/)
		.test(this));
}

/**
 * 是否是QQ邮箱
 */
String.prototype.isQQEmail = function(){
	/*
	 var k = '@';
	 var i = this.indexOf(k);
	 var qq = this.substr((i + 1), 2);

	 if(qq == 'qq' || qq == 'QQ')
	 return true;
	 */
	return false;
}

//验证身份证
String.prototype.isCardNo = function() {
	return (new RegExp(/^[1-9]([0-9]{16}|[0-9]{13})[xX0-9]$/)
		.test(this));
}

/**
 * 是否为数字
 */
String.prototype.isNumber=function(){
	return (new RegExp(/^[0-9]*$/).test(this));
}
/**
 * 密码是否正确
 */
String.prototype.password=function(){
	return (new RegExp(/^[\w]{6,12}$/).test(this));
}
/**
 * 是否日期
 *
 * @returns
 */
String.prototype.isDate = function() {
	return (new RegExp(
		/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/ig)
		.test(this));
}

/**
 * 替换全部
 */
String.prototype.replaceAll  = function(s1,s2){
	return this.replace(new RegExp(s1,"gm"),s2);
}

/**
 * 克隆(深拷贝)
 *
 * @param obj
 * @returns
 */
function clone(obj) {
	if (typeof (obj) != 'object') {
		return obj;
	}

	var re = {};

	if (obj.constructor == Array) {
		re = [];
	}

	for ( var i in obj) {
		re[i] = clone(obj[i]);
	}

	return re;
}


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
	beforeSend: function (XMLHttpRequest) {
		//弹出遮罩层
		loadingMessage();
		//禁用页面submit按钮既可以一定程度防止表单的重复提交，又能大大减轻服务器端压力.
		$("form").find(":submit").attr("disabled", true);
	},
	complete:function(XMLHttpRequest,textStatus,errorMsg){
		//关闭遮罩层
		closeMessage(loading);

		//重新启用页面submit按钮,使其可以再次点击
		$("form").find(":submit").attr("disabled", false);

		//Http响应状态码
		var status = XMLHttpRequest.status;

		if(status == 400){  //请求参数不合法
			var resJson = eval('('+XMLHttpRequest.responseText+')');

			//后端数据校验未通过
			if(resJson.code == 2000){
				//通过XMLHttpRequest取得响应头,X-Refresh-Token-Form,获取refreshTokenForm用于刷新页面tokenForm
				var refreshTokenForm = XMLHttpRequest.getResponseHeader("X-Refresh-Token-Form");
				//刷新页面所有的tokenForm
				if(refreshTokenForm != "" && refreshTokenForm != null){
					$("input[name='tokenForm']").val(refreshTokenForm);
				}
			}

			//表单重复提交
			if(resJson.code == 2075){
				sadMessage(resJson.msg);
			}
		}else if(status == 401){    //session超时
			//登录状态,通过XMLHttpRequest取得响应头,X-Session-Status
			var sessionStatus=XMLHttpRequest.getResponseHeader("X-Session-Status");
			if(sessionStatus == 'Session-Timeout'){
				sadMessage("登录超时，请重新登录！");
				top.location = "/admin/login";
			}

			var resJson = eval('('+XMLHttpRequest.responseText+')');
			//已被踢出登录
			if(resJson.code == 2076){
				layer.confirm(resJson.msg, {icon: 5, title:'提示',btn: ['重新登录', '取消']}, function(index){
					//执行退出并跳转到登录
					top.location = "/admin/logout";
					closeMessage(index);
				});
			}
		}else if(status == 403){      //没有权限
			//权限状态,通过XMLHttpRequest取得响应头,X-No-Permission
			var noPermission=XMLHttpRequest.getResponseHeader("X-No-Permission");
			if(noPermission == 'No-Permission'){
				sadMessage('没有操作权限！');
			}
		}
	}
});

