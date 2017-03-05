/** 
 * Project Name:good-manage 
 * File Name:ProjectMilepostService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月22日下午2:15:53 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jk.model.ProjectMilepost;

/** 
 * ClassName:ProjectMilepostService <br/> 
 * Function: 里程碑service. <br/> 
 * Date:     2017年2月22日 下午2:15:53 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ProjectMilepostService extends BaseService<ProjectMilepost> {

	/**
	 * 	
	 * findProjectMilepostListByProjectId:(查询特性条件下的所有的里程碑列表). <br/> 
	 * @author dfz
	 * @param map
	 * @return 
	 * Date:2017年2月22日下午3:53:13 
	 * @since JDK 1.8
	 */
	public PageInfo<ProjectMilepost>  findProjectMilepostListByProjectId(Map map);
	
	/**
	 * 
	 * findByProjectMilepostId:(自己写的方法,没有用自带的方法，在类中增加了一些字段). <br/> 
	 * @author dfz
	 * @param id
	 * @return 
	 * Date:2017年2月23日上午9:40:25 
	 * @since JDK 1.8
	 */	
	public ProjectMilepost findByProjectMilepostId(long id);

}
  