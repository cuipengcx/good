/** 
 * Project Name:good-manage 
 * File Name:PEIMapper.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月16日上午10:20:57 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;

import com.jk.model.PEI;
import com.jk.util.MyMapper;

/** 
 * ClassName:PEIMapper <br/> 
 * Function: TODO 项目打分情况 <br/> 
 * Date:     2017年2月16日 上午10:20:57 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface PEIMapper  extends MyMapper<PEI>{

	public  int insertBath(List<PEI> listPET);
	
	/**
	 * 
	 * findByProjectId:(这里用一句话描述这个方法的作用). <br/> 
	 * @author dfz
	 * @param projectId
	 * @return 
	 * Date:2017年2月17日上午11:46:36 
	 * @since JDK 1.8
	 */
	public List<PEI> findByProjectId(long projectId);
	/**
	 * 
	 * deleteByProjectId:(删除). <br/> 
	 * @author dfz
	 * @param projectId
	 * @return 
	 * Date:2017年2月17日下午4:05:07 
	 * @since JDK 1.8
	 */
	public int deleteByProjectId(long projectId);
}
  