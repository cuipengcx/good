$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
});



	function saveProject(status){//status:1 审核失败。2 审核通过
		var failReasion=$("#failReasion").val();
		var projectId=$("#projectId").val();
		if(status==1){
			if(failReasion==""){
				errorMessage("失败驳回，请输入驳回原因",2000);
				return;
			}
		}				
        $.ajax({
            type: 'post',
            url: "/admin/project/checkProjectStatus",
            dataType:"json",
            data:{"status":status,"failReasion":failReasion,"projectId":projectId},
            success: function(data){
            	if(data.status=='success'){	
            		succeedMessage(data.message,5000);
	               var index = parent.layer.getFrameIndex(window.name);
	               parent.location.reload();
	               parent.layer.close(index);
				}else{
					errorMessage("审核失败",2000);
				}
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
	}

