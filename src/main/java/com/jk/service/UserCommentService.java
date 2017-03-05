/** 
 * Project Name:good-manage 
 * File Name:UserCommentService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月23日上午11:01:42 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jk.model.UserComment;

/** 
 * ClassName:UserCommentService <br/> 
 * Function: 会员评论 <br/> 
 * Date:     2017年2月23日 上午11:01:42 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface UserCommentService extends BaseService<UserComment> {
	
	/**
	 * 
	 * selectAllByProjectId:(查询一个项目下的会员评论). <br/> 
	 * @author dfz
	 * @param map
	 * @return 
	 * Date:2017年2月23日下午1:52:10 
	 * @since JDK 1.8
	 */
	public PageInfo<UserComment> selectAllByProjectId(Map map);

}
  