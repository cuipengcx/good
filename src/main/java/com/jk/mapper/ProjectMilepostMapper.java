/** 
 * Project Name:good-manage 
 * File Name:ProjectMilepost.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月22日下午2:14:30 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;

import com.jk.model.ProjectMilepost;
import com.jk.util.MyMapper;

import tk.mybatis.mapper.common.BaseMapper;

/** 
 * ClassName:ProjectMilepost <br/> 
 * Function: 里程碑Mapper<br/> 
 * Date:     2017年2月22日 下午2:14:30 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ProjectMilepostMapper  extends MyMapper<ProjectMilepost>{
	/**
	 * 
	 * findProjectMilepostListByProjectId:(根据项目id，查询出该条项目下所有的里程碑). <br/> 
	 * @author dfz
	 * @param id
	 * @return 
	 * Date:2017年2月22日下午4:03:49 
	 * @since JDK 1.8
	 */
	public List<ProjectMilepost>  findProjectMilepostListByProjectId(long projectId);
	
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
  