/** 
 * Project Name:good-manage 
 * File Name:UserCommentMapper.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月23日上午11:01:08 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;

import com.jk.model.UserComment;
import com.jk.util.MyMapper;

/** 
 * ClassName:UserCommentMapper <br/> 
 * Function: 会员评论mapper <br/> 
 * Date:     2017年2月23日 上午11:01:08 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface UserCommentMapper  extends MyMapper<UserComment>{
	
	public List<UserComment> selectAllByProjectId(long projectId);

}
  