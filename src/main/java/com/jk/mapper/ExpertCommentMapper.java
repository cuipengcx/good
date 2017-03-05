/** 
 * Project Name:good-manage 
 * File Name:ExpertCommentMapper.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月20日上午10:05:13 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.ExpertComment;
import com.jk.util.MyMapper;

/** 
 * ClassName:ExpertCommentMapper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月20日 上午10:05:13 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ExpertCommentMapper  extends MyMapper<ExpertComment> {

	public List<ExpertComment> findList(Map map);
	
	public int updateByExpertComment(ExpertComment expertComment);
	
}
  