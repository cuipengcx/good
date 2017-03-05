/** 
 * Project Name:good-manage 
 * File Name:ProjectMapper.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月7日下午5:29:54 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.Industry;
import com.jk.model.Project;
import com.jk.util.MyMapper;

/** 
 * ClassName:ProjectMapper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月7日 下午5:29:54 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ProjectMapper  extends MyMapper<Project>{
	
	public List<Project> projectList(Map map);
	
	public int projectDelete(long id);
	
	public int projectUpdateAdminStatus(Map map);
	
	public Project  findById(long id);
	
	public List<Industry> findAllIndustry();
	
	public int checkProjectStatus(Map map);
	
	public int  updateProject(Project project);

}
  