$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
});


function ajaxScoreCheck(){
	var codes=new Array();
	var code=0;
	$(".select").each(function(i,e){//分数
		var value=this.value;
		codes[i]=value;
		code+=parseInt(value);
	});
	var projectId=$("#projectId").val();
	layer.confirm("根据您的打分情况，该项目的PEI值为:"+code+",是否继续提交?",function(index){
        $.ajax({
        	url: "/admin/project/saveProjectScore",
            type: 'post',
            dataType:"json",
            data:{"codes":codes,"projectId":projectId,"code":code},
            traditional: true,
            success: function(data){
            	if(data.status=='success'){	
            	   layer.msg(data.message);
	               var index = parent.layer.getFrameIndex(window.name);
	               parent.location.reload();
	               parent.layer.close(index);
				}else{
					errorMessage("保存失败",2000);
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
	});
}
		