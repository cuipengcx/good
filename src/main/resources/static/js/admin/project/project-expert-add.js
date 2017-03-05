
/**
 * 上传图片
 * @param fileElementId
 * @param imageElementId
 */
function uploadImage(fileElementId, imageElementId) {
	var path = $("#" + fileElementId).val();	
	if(path == "") {
		errorMessage("请选择上传的图片",2000);
		return;
	}
	if (!isImageFile(path)) {
		errorMessage("请上传图片",2000);
		return;
	}
	jQuery.ajaxFileUpload({
		url : "/upload/uploadimage",
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'text',
		success : function(data) {
			var data = eval('(' + data + ')'); 
			if (data.error > 0) {
				$("#" + imageElementId).attr("src", "");
				$("#imgCode").val("");				
				errorMessage(data.message,5000);
				return;
			}else{
				succeedMessage("图片上传成功",5000);
				$("#" + imageElementId).attr("src", data.dataurl);
				$("#imgCode").val(data.dataurl);
			}
		},
		error : function(data, status, e) {
			alert("上传图片失败");
		}
	})
}
/**
 * 根据扩展名判断是否为图片
 */
function isImageFile(path) {
	if (path.length == 0) {
		return false;
	}
	var lowerPath = path.toLowerCase();
	var number = lowerPath.lastIndexOf(".");
	if(number == -1) 
		return false;
    var checkKey = lowerPath.substring(number+1,lowerPath.length);
   if(checkKey == "jpg" || checkKey =="jpeg"  || checkKey =="png" || checkKey == "gif" ) {
	    return true;
	}
	return false;
}
function addbanner(id){
	var divid = parseInt(id) + 1;
	var addbanner='<div style="float: left;width: 270px;" id="divbanner'+divid+'"><input type="file" id="bannerPic'+divid+'" name="bannerPic"><a style="color: red;" onclick="removebanner('+divid+');">x</a>  </div>';
	$('#divbannerpic').append(addbanner);
}
function removebanner(id){
	$('#divbanner'+id).remove();
}
//查看项目详情
function project_detail(title,url,w,h){
	layer_show(title,url,w,h);
}

$(function(){
	 $('.skin-minimal input').iCheck({
	        checkboxClass: 'icheckbox-blue',
	        radioClass: 'iradio-blue',
	        increaseArea: '20%'
	    }); 
	 
	 $("#form-expert-comment-add").validate({
		 rules:{
			 expertName:{
					required:true
			 },
			 contents:{
				 required:true
			 },
			 expertTitle:"required",
			 imgCode:{
				 required:true
			 }
		 },
		 messages:{
			 expertName:"专家姓名不能为空！",
			 contents:"专家评论不能为空!",
			 imgCode:"请上传图片",
			 expertTitle:"职称不能为空！"
		 },
		 onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
        	 $(form).ajaxSubmit({
        		 	url:"/admin/comment/ajaxAddComment",
        		 	dataType:"json",
        		 	type:"post",
        		 	success:function(data){
        		 		 var index = parent.layer.getFrameIndex(window.name);
      	               parent.location.reload();
      	               parent.layer.close(index);       		 		       		 		        		 		
        		 	},
        		 	error:function (XMLHttpRequest, textStatus, errorThrown) {
                        if(XMLHttpRequest.status == 403){
                            layer_close();
                            errorMessage('无权操作!');
                        }else {
                            errorMessage("系统错误!");
                        }
                    }
        	 
        	 });
        	 return false; // 非常重要，如果是false，则表明是不跳转，在本页上处理，也就是ajax，如果是非false，则传统的form跳转。
        }
	 });
});