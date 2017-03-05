/** 
 * Project Name:good-manage 
 * File Name:ProjectController.java 
 * Package Name:com.jk.controller.front 
 * Date:2017年2月24日下午2:22:13 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.controller.front;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jk.controller.BaseController;
import com.jk.model.Area;
import com.jk.model.GoodUser;
import com.jk.model.Industry;
import com.jk.service.AreaService;
import com.jk.service.ProjectService;

/** 
 * ClassName:ProjectController <br/> 
 * Function:   项目前台控制器. <br/> 
 * Date:     2017年2月24日 下午2:22:13 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Controller
@RequestMapping("/front/project")
public class FrontProjectController  extends  BaseController{
	
    @Autowired
    private AreaService areaServiceImpl;	
    @Autowired
    private ProjectService projectServiceImpl;
	
	private static final String PROJECT_PATH="front/project/";
	/*
	 * 判断用户是否已经认证过邮箱，个人，企业
	 */
	@ResponseBody
	@PostMapping("/checkGoodUserAuthenticationInfo")
	public ModelAndView checkGoodUserAuthenticationInfo(){	
		ModelAndView mv=new ModelAndView();
//		Subject subject = SecurityUtils.getSubject();
//		GoodUser goodUser=(GoodUser) subject.getPrincipal();
		GoodUser goodUser=new GoodUser();
		goodUser.setPersonAuthed(true);
		goodUser.setMobilePhone("111111");
		goodUser.setEmailAuthed(false);
		goodUser.setTeamAuthed(false);
		boolean flag=false;
		if((goodUser.getPersonAuthed()||goodUser.getTeamAuthed())&&goodUser.getEmailAuthed()&&StringUtils.isNotBlank(goodUser.getMobilePhone())){
			flag=true;			
		}
		mv.addObject("flag",flag);
		mv.addObject("goodUser",goodUser);
		mv.setViewName("front/project/project-alert");
		return mv;
	}
	/**
	 * 
	 * toCreateInit:(上传项目初始化，选择项目类型). <br/> 
	 * @author dfz
	 * @param modelMap
	 * @return 
	 * Date:2017年2月27日下午2:04:48 
	 * @since JDK 1.8
	 */
	@RequestMapping("/toCreateInit")
	public String  toCreateInit(ModelMap modelMap){
		GoodUser goodUser=new GoodUser();
		goodUser.setPersonAuthed(true);
		goodUser.setMobilePhone("111111");
		goodUser.setEmailAuthed(false);
		goodUser.setTeamAuthed(true);
		modelMap.put("goodUser", goodUser);
		return PROJECT_PATH+"project-create";
	}
	/**
	 * 
	 * createProjectFirst:(跳转上传项目第一步). <br/> 
	 * @author dfz
	 * @param projectType
	 * @return 
	 * Date:2017年2月27日下午2:15:40 
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	@RequestMapping("/createProjectStepFirst")
	public String createProjectFirst(String projectType,ModelMap modelMap) throws Exception{
		if(!StringUtils.isNotBlank(projectType)||!StringUtils.isNumeric(projectType)){
			throw new Exception("参数异常,即：获取的项目类型异常，projectType="+projectType);
		}
		GoodUser goodUser=new GoodUser();
		goodUser.setPersonAuthed(true);
		goodUser.setMobilePhone("111111");
		goodUser.setEmailAuthed(false);
		goodUser.setTeamAuthed(true);		
		modelMap.put("goodUser", goodUser);
		
		
		modelMap.put("projectType", projectType);
		
		List<Industry> industryList=projectServiceImpl.findAllIndustry();
		List<Area> areaParentList=areaServiceImpl.findParentAreaList();
		List<Area> areaChildList=areaServiceImpl.findChildAreaList();
		log.info("查询所有行业信息成功！ industryList = {}", industryList);
		log.info("查询所有父类城市成功！ areaParentList = {}",areaParentList);
		log.info("查询所有子类城市成功！ areaChildList = {}",areaChildList);
		modelMap.put("industryList", industryList);
		modelMap.put("areaParentList", areaParentList);
		modelMap.put("areaChildList", areaChildList);
		return PROJECT_PATH+"project-create-step1";
	}
}
  