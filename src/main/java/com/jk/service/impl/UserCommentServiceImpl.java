/** 
 * Project Name:good-manage 
 * File Name:UserCommentServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月23日上午11:02:36 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.UserCommentMapper;
import com.jk.model.UserComment;
import com.jk.service.UserCommentService;

/** 
 * ClassName:UserCommentServiceImpl <br/> 
 * Function: 会员评论serviceImpl <br/> 
 * Date:     2017年2月23日 上午11:02:36 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Service
@Transactional
public class UserCommentServiceImpl  extends BaseServiceImpl<UserComment> implements UserCommentService{

	@Resource
	private UserCommentMapper userCommentMapper;

	/**
	 * 通过项目id,查询出该项目下所有的会员评论
	 */
	public PageInfo<UserComment> selectAllByProjectId(Map map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
		List<UserComment> userCommentList=userCommentMapper.selectAllByProjectId(Long.parseLong(map.get("projectId").toString()));
		return new PageInfo<UserComment>(userCommentList);
	}
	
	

}
  