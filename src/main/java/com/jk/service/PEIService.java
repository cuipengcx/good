/** 
 * Project Name:good-manage 
 * File Name:PEIService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月16日上午10:07:23 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.List;

import com.jk.model.PEI;

/** 
 * ClassName:PEIService <br/> 
 * Function: TODO项目分数<br/> 
 * Date:     2017年2月16日 上午10:07:23 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface PEIService  extends BaseService<PEI> {
	
	/**
	 * 
	 * insertBath:(批量插入). <br/> 
	 * @author dfz
	 * @param listPET
	 * @return 
	 * Date:2017年2月16日上午11:31:42 
	 * @since JDK 1.8
	 */
	public  int insertBath(List<PEI> listPET);
	/**
	 * 
	 * findByProjectId:(通过项目的id，查询出分数列表). <br/> 
	 * @author dfz
	 * @param projectId  
	 * @return 
	 * Date:2017年2月17日下午2:08:28 
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
  