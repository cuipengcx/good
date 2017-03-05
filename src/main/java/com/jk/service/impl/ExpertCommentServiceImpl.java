/** 
 * Project Name:good-manage 
 * File Name:ExpertCommentServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月20日上午11:15:39 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ExpertCommentMapper;
import com.jk.model.ExpertComment;
import com.jk.service.ExpertCommentService;

/** 
 * ClassName:ExpertCommentServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月20日 上午11:15:39 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Service
@Transactional
public class ExpertCommentServiceImpl  extends BaseServiceImpl<ExpertComment> implements ExpertCommentService{

	@Autowired
	private ExpertCommentMapper  expertCommentMapper;
	
	
	/**
	 * 查询出该项目下所有的专家评论
	 */
	public PageInfo<ExpertComment> findList(Map map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
		List<ExpertComment> list=expertCommentMapper.findList(map);
		return new PageInfo<ExpertComment>(list);
	}


	/**
	 * 编辑评论表
	 */
	public int updateByExpertComment(ExpertComment expertComment) {
		return expertCommentMapper.updateByExpertComment(expertComment);
	}
	
	

}
  