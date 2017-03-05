/** 
 * Project Name:good-manage 
 * File Name:ExpertCommentService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月20日上午11:11:17 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jk.model.ExpertComment;

/** 
 * ClassName:ExpertCommentService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月20日 上午11:11:17 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ExpertCommentService  extends BaseService<ExpertComment>{

	public PageInfo<ExpertComment> findList(Map map);
	
	public int updateByExpertComment(ExpertComment expertComment);
	
}
  