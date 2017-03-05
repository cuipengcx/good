$(function(){
	$("#project-create-step1").validate({
		errorElement: 'span',
		errorPlacement: function(error, element) {
		           error.appendTo(element.parent());
		},
		rules:{
			projectPicture:"required",
			projectName:"required",
			projectLead:"required",
			industryId:{required:true},
			cityId:"required",
			projectCapital:{		//资金
    			required:true,
    			money:true  			
    		},
    		earnings:{				//收益人数
    			required:true,
    			isIntGtZero:true
    		},
    		projectPartner:"required"
		},
		 messages: {
			 projectPicture:"项目图片不能为空！",
			 projectName: "不能为空！",
		     projectLead:"不能为空！",
		     industryId:{required:"请选择项目所属类别！"},
			 cityId:"请选择项目城市!",
			 projectCapital:{
	    			required:"预计需要金额!",
	    			money:"预计需要金额格式错误!"
	    		},
    		earnings:{
    			required:"预计收益人数不能为空!",
    			earnings:"格式错误!"
    		},
	    	projectPartner:"项目伙伴不能为空!"
		 },     
		onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
        	var projectPicture=$("#projectPicture").val();//项目图片
        	var projectName=$("#projectName").val();//项目名称
        	var projectLead=$("#projectLead").val();//项目导语
        	var industryId=$("#industryId").val();//项目地域
        	var cityId=$("#cityId").val();//项目地域
        	var projectCapital=$("#projectCapital").val();
        	var earnings=$("#earnings").val();
        	var projectPartner=$("#projectPartner").val();
        	$("#projectCapital").val($("#projectCapital").val()*100);
        	var message="";
        	$(".aaa").each(function(i,e){//分数
        		if(i==0){
        			message+=this.value;
        		}else{
        			if(this.value!=""){
        			message+=";"+this.value;
        			}
        		}
        	});
        	var projectPartner=$("#projectPartner").val(message);
        	alert("暂停---------------------------------------");

        }				
	});
});