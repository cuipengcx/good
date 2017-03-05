
/**
 * 会员认证弹出框js
 */
function  checkUserAuthenticationInfo(){
		$.ajax({
			url:"/front/project/checkGoodUserAuthenticationInfo",
			dataType:"html",
			type:"post",
			timeout:5000,
			success:function(data){
				$("#home_alert").html(data);
			},
			error:function(){
				errorMessage("访问异常,请稍后重试",2000);
			}			
		});		
}
