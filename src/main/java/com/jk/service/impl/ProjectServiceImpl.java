/** 
 * Project Name:good-manage 
 * File Name:ProjectServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月8日上午10:04:06 
 * 
*/  
  
package com.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ProjectMapper;
import com.jk.model.Industry;
import com.jk.model.Project;
import com.jk.service.ProjectService;

/** 
 * ClassName:ProjectServiceImpl <br/> 
 * Function: 项目service. <br/> 
 * Date:     2017年2月8日 上午10:04:06 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Transactional
@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;;
	/**
	 * 分页查询出项目条数
	 */
    @Transactional(readOnly=true)
	public PageInfo<Project> findPage(Map map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
		List<Project> listProject=projectMapper.projectList(map);
		return new PageInfo<Project>(listProject);
	}
	/**
	 * 删除项目
	 */
    @Transactional
	public int projectDelete(long id) {
		return projectMapper.projectDelete(id);
	}
    /**
     * 修改项目是否启用状态
     */
	@Transactional
	public int projectUpdateAdminStatus(String id,String admin_status) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",Long.parseLong(id));
		map.put("admin_status",Integer.parseInt(admin_status));
		return projectMapper.projectUpdateAdminStatus(map);
	}
	/**
	 * 通过主键，查出对应的对象
	 */
	@Transactional(readOnly=true)
	public Project findById(long id) {
		return projectMapper.findById(id);
	}
	/**
	 * 查出所有的行业分类
	 */
	@Transactional
	public List<Industry> findAllIndustry() {
		return projectMapper.findAllIndustry();
	}
	/**
	 * 修改项目状态
	 */
	public int checkProjectStatus(String projectId,String failReasion,String status) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",Long.parseLong(projectId));
		map.put("fail_reasion",failReasion);
		map.put("status",Integer.parseInt(status));
		return projectMapper.checkProjectStatus(map);
	}
	/**
	 * 后台编辑项目
	 */
	public int updateProject(Project project){
		return projectMapper.updateProject(project);
	}

}
  