/** 
 * Project Name:good-manage 
 * File Name:ProjectService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月8日上午9:55:24 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jk.model.Industry;
import com.jk.model.Project;

/** 
 * ClassName:ProjectService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月8日 上午9:55:24 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface ProjectService  extends BaseService<Project>{
	
	/**
	 * 
	 * findPage:(项目列表). <br/> 
	 * 
	 * @param pageNum    页数
	 * @param pageSize   数量
	 * @param projectName 项目名
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @return 
	 * @since JDK 1.8
	 */
	public PageInfo<Project> findPage(Map map);
	
	/**
	 * 
	 * projectDelete:(删除项目). <br/> 
	 * @author JK 
	 * @param id   主键
	 * @return 
	 * @since JDK 1.8
	 */
	public int  projectDelete(long id);
	/**
	 * 
	 * projectUpdateAdminStatus:(修改是否启用状态). <br/> 
	 * @author JK 
	 * @param id   主键
	 * @param admin_status  状态（是否启用）
	 * @return 
	 * @since JDK 1.8
	 */
	public int projectUpdateAdminStatus(String id,String admin_status);
	/**
	 * 
	 * findById:(通过主键查找项目). <br/> 
	 * @author dfz
	 * @param id
	 * @return 
	 * Date:2017年2月9日上午11:35:58 
	 * @since JDK 1.8
	 */
	public Project findById(long id);
	/**
	 * 
	 * findAllIndustry:(查询出所有的行业). <br/> 
	 * @author dfz
	 * @return 
	 * Date:2017年2月9日下午2:27:42 
	 * @since JDK 1.8
	 */
	public List<Industry> findAllIndustry();
	/**
	 * 
	 * checkProjectStatus:(修改项目状态及打分). <br/> 
	 * @author dfz
	 * @param map
	 * @return 
	 * Date:2017年2月9日下午5:03:59 
	 * @since JDK 1.8
	 */
	public int checkProjectStatus(String projectId,String failReasion,String status);
	/**
	 * 
	 * updateProject:(后台编辑项目). <br/> 
	 * @author dfz
	 * @param project  
	 * @return 
	 * Date:2017年2月14日下午2:29:34 
	 * @since JDK 1.8
	 */
	public int updateProject(Project project);
}
  