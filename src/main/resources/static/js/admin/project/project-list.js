/*审核*/
function project_edit(title,url,status){
	if(status==1||status==2){
		lockMessage("项目已被审核，无法重新审核",3000);
		return;
	}
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*编辑*/
function project_edit2(title,url,status){
	if(status==1||status==2){
		lockMessage("项目已被审核，无法重新编辑",3000);
		return;
	}
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}



//去打分
function project_score(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}



/*跳转专家评论列表页面*/
function project_expert(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?projectId="+id
	});
	layer.full(index);
}


/**
 * 项目是否启用
 */
function project_status(obj,id,status){
	var contents="";
	var mark=0;
	if(status==0){
		contents="确认要停用吗？";
		mark=1;
	}else{
		contents="确认要启用吗？";
		mark=0;
	}	
	layer.confirm(contents,function(index){
			$.ajax({
				url:"/admin/project/projectUpdateAdminStatus?"+Math.random(),
				dataType:'json',
				type:'post',
				data:{"id":id,"mark":mark},
				success:function(data){
					if(data.status=="success"){
						//此处请求后台程序，下方是成功后的前台处理……				
						if(status==0){
							$(obj).parents("tr").find(".td-manage").prepend('<a onClick="project_status(this,'+id+',1)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label radius">已停用</span>');
							$(obj).remove();
							layer.msg('已停用!',{icon: 5,time:1000});
						}else{
							$(obj).parents("tr").find(".td-manage").prepend('<a onClick="project_status(this,'+id+',0)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
							$(obj).remove();
							layer.msg('已启用!', {icon: 6,time:1000});
						}
					}else{
						layer.msg('修改失败',{icon:5,time:1000});
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
/*删除*/
function project_del(obj,id){
	layer.confirm('确认要删除吗(删除后数据无法恢复)？',function(index){
		$.ajax({
			type: 'POST',
			url: '/admin/project/projectDelete/'+id,
			dataType: 'json',
			success: function(data){
				if(data.status=='success'){					
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg('删除失败',{icon:5,time:1000});
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