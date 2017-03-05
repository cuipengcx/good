
function project_create_check (){
	 if($('input:radio[name="projectType"]:checked').val()==null){
		 errorMessage("请选择项目类型的发起方式!",2000);
		 return;
	 }
	 if(!($("input[type='checkbox']").is(':checked'))){
		 errorMessage("请勾选'我已阅读并同意平台项目所有协议'",2000);
		 return;
	 }
//	 console.log($('input:radio[name="projectType"]:checked').val());
	 //验证成功，跳转
	 location.href="/front/project/createProjectStepFirst?projectType="+$('input:radio[name="projectType"]:checked').val(); 
}