/** 
 * Project Name:good-manage 
 * File Name:PEIServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月16日上午10:11:46 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.mapper.PEIMapper;
import com.jk.model.PEI;
import com.jk.service.PEIService;

/** 
 * ClassName:PEIServiceImpl <br/> 
 * Function:  处理打分情况service <br/> 
 * Date:     2017年2月16日 上午10:11:46 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Transactional
@Service
public class PEIServiceImpl  extends BaseServiceImpl<PEI> implements PEIService{

	@Autowired
	private PEIMapper peiMapper;
	
	/**
	 * 批量插入打分情况
	 */
	@Transactional
	public int insertBath(List<PEI> listPET) {		
		return peiMapper.insertBath(listPET);
	}
	
	/**
	 * 通过项目id，查询出所有的分数list
	 */
	public List<PEI> findByProjectId(long projectId){
		return peiMapper.findByProjectId(projectId);
	}
	/**
	 * 
	 * deleteByProjectId:(删除). <br/> 
	 * @author dfz
	 * @param projectId
	 * @return 
	 * Date:2017年2月17日下午4:05:07 
	 * @since JDK 1.8
	 */
	public int deleteByProjectId(long projectId){
		return peiMapper.deleteByProjectId(projectId);
	}
}
  