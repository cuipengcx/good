/** 
 * Project Name:good-manage 
 * File Name:ProjectMilepostServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月22日下午2:16:58 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ProjectMilepostMapper;
import com.jk.model.ProjectMilepost;
import com.jk.service.ProjectMilepostService;

/** 
 * ClassName:ProjectMilepostServiceImpl <br/> 
 * Function: 里程碑 <br/> 
 * Date:     2017年2月22日 下午2:16:58 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Transactional
@Service
public class ProjectMilepostServiceImpl  extends BaseServiceImpl<ProjectMilepost> implements ProjectMilepostService{
	
	@Autowired
	private ProjectMilepostMapper projectMilepostMapper;
	
	
	/**
	 * 查询出一条项目中，所有的里程碑
	 */
	@Transactional(readOnly=true)
	public PageInfo<ProjectMilepost> findProjectMilepostListByProjectId(Map map) {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
			List<ProjectMilepost> listProjectMilepost=projectMilepostMapper.findProjectMilepostListByProjectId(Long.parseLong(map.get("projectId").toString()));
			for (int i = 0; i <listProjectMilepost.size(); i++) {
				if(StringUtils.isNotBlank(listProjectMilepost.get(i).getImgCodes())){
					if(listProjectMilepost.get(i).getImgCodes().contains(";")){
						listProjectMilepost.get(i).setImg(listProjectMilepost.get(i).getImgCodes().split(";")[0]);
					}else{
						listProjectMilepost.get(i).setImg(listProjectMilepost.get(i).getImgCodes());
					}
				}
			}
		return new PageInfo<ProjectMilepost>(listProjectMilepost);
	}


	@Transactional(readOnly=true)
	public ProjectMilepost findByProjectMilepostId(long id) {
		return projectMilepostMapper.findByProjectMilepostId(id);
	}
	
}
  