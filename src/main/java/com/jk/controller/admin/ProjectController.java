/** 
 * Project Name:good-manage 
 * File Name:ProjectController.java 
 * Package Name:com.jk.controller.admin 
 * Date:2017年2月7日下午4:17:58 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Area;
import com.jk.model.Industry;
import com.jk.model.PEI;
import com.jk.model.Project;
import com.jk.model.ProjectMilepost;
import com.jk.service.AreaService;
import com.jk.service.PEIService;
import com.jk.service.ProjectMilepostService;
import com.jk.service.ProjectService;
import com.jk.service.impl.AreaServiceImpl;
import com.jk.service.impl.PEIServiceImpl;
import com.jk.service.impl.ProjectMilepostServiceImpl;
import com.jk.service.impl.ProjectServiceImpl;

/** 
 * ClassName:ProjectController <br/> 
 * Function: 项目管理. <br/> 
 * Date:     2017年2月7日 下午4:17:58 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectController extends BaseController{
    private static final String BASE_PATH = "admin/project/";
	
    @Autowired
    private ProjectService projectServiceImpl;
    @Autowired
    private PEIService peiServiceImpl;  
    @Autowired
    private AreaService areaServiceImpl;
    @Autowired
    private ProjectMilepostService projectMilepostServiceImpl;
    /**
     * 
     * projectList:(项目列表). <br/>  
     * @author dfz
     * @param pageNum  当前页数
     * @param projectName  项目名
     * @param startTime    开始时间
     * @param endTime		结束时间
     * @param modelMap
     * @return 
     * @since JDK 1.8
     */
	@RequestMapping("/projectList")
	public String  projectList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            Project model, String projectName, String startTime, String endTime, ModelMap modelMap,String userName,String authenticationName){
		try {
			 log.debug("分页查询项目列表参数! pageNum = {}, username = {}, startTime = {}, endTime = {},userName={},authenticationName={}", pageNum, projectName, startTime, endTime,userName,authenticationName);
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("project_name", projectName);
			 map.put("create_time", startTime);
			 map.put("end_time", endTime);
			 map.put("authenticationName",authenticationName);
			 map.put("userName",userName);
			 map.put("pageNum",pageNum);
			 map.put("pageSize",PAGESIZE);
			 PageInfo<Project> pageInfo=projectServiceImpl.findPage(map);
			 log.info("分页查询管理员列表成功！ pageInfo = {}", pageInfo);
			 List<Industry> industryList=projectServiceImpl.findAllIndustry();
			 log.info("查询所有行业信息成功！ industryList = {}", industryList);
			 modelMap.put("pageInfo", pageInfo);
	         modelMap.put("projectName", projectName);
	         modelMap.put("startTime", startTime);
	         modelMap.put("endTime", endTime);
	         modelMap.put("userName",userName);
	         modelMap.put("authenticationName",authenticationName);
	         modelMap.put("industryList",industryList);
		} catch (Exception e) {
			log.error("分页查询项目列表错误! e = {}", e.getMessage());
		}
		
		return BASE_PATH+"project-list";
	}
	
	/**
	 * 
	 * projectDelete:(删除项目). <br/> 
	 * @author dfz
	 * @param id 项目主键
	 * @return 
	 * @since JDK 1.8
	 */
	@ResponseBody
	@RequestMapping("/projectDelete/{id}")
	public ModelMap projectDelete(@PathVariable("id") String id){
		ModelMap map=new ModelMap();
		log.info("需要删除的项目id={}",id);
		if(StringUtils.isBlank(id)||!StringUtils.isNumeric(id)){
			map.put("status",FAILURE);
            map.put("message","删除失败!");			
		}else{
			int flag=0;
			try {
				flag=projectServiceImpl.projectDelete(Long.parseLong(id));
			} catch (NumberFormatException e) {
				log.error("删除项目错误! e = {}，项目id={}", e.getMessage(),id);
			}
			if(flag>0){			
				map.put("status",SUCCESS);
				map.put("message","删除成功!");
			}else{
				map.put("status",FAILURE);
	            map.put("message","删除失败!");
			}
		}		
		return map;
	}
	/**
	 * 
	 * projectUpdateAdminStatus:(修改启用状态). <br/> 
	 * @author JK 
	 * @param id  主键
	 * @param mark   admin_status
	 * @return 
	 * @since JDK 1.8
	 */
	@ResponseBody
	@RequestMapping("/projectUpdateAdminStatus")
	public ModelMap projectUpdateAdminStatus(String id,String mark){
		ModelMap map=new ModelMap();
		log.info("后台管理员操作修改启用状态的项目id={},启用状态={}(注:0启用，1禁用)",id,mark);
		if(StringUtils.isBlank(id)||!StringUtils.isNumeric(id)||StringUtils.isBlank(mark)||!StringUtils.isNumeric(mark)){
			map.put("status",FAILURE);
            map.put("message","修改状态失败!");
            return map;
		}
		int flag=0;
		try {
			flag=projectServiceImpl.projectUpdateAdminStatus(id, mark);
		} catch (NumberFormatException e) {
			log.error("修改项目启用状态错误! e = {}，项目id={}", e.getMessage(),id);
		}
		if(flag>0){			
			map.put("status",SUCCESS);
			map.put("message","修改成功!");
		}else{
			map.put("status",FAILURE);
            map.put("message","修改失败!");
		}
		return map;
	}
	
	/**
	 * 
	 * checkProject:(项目审核). <br/> 
	 * @author dfz 
	 * @param id  项目主键
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/checkProject/{id}")
	public String checkProject(@PathVariable("id") String id,ModelMap map){	
		log.info("项目审核开始，传入的项目id={}",id);
		Project project=projectServiceImpl.findById(Long.parseLong(id));
		List<Industry> industryList=projectServiceImpl.findAllIndustry();
		log.info("查询所有行业信息成功！ industryList = {}",industryList);
		List<Area> areaParentList=areaServiceImpl.findParentAreaList();
		List<Area> areaChildList=areaServiceImpl.findChildAreaList();
		log.info("查询所有父类城市成功！ areaParentList = {}",areaParentList);
		log.info("查询所有子类城市成功！ areaChildList = {}",areaChildList);
		map.put("project",project);
		map.put("industryList",industryList);
		map.put("areaParentList",areaParentList);
		map.put("areaChildList",areaChildList);
		return BASE_PATH+"project-edit";
	}
	/**
	 * 
	 * checkProjectStatus:(ajax保存，项目审核状态). <br/> 
	 * @author dfz
	 * @param score
	 * @param failReasion
	 * @return 
	 * Date:2017年2月9日下午4:59:23 
	 * @since JDK 1.8
	 */
	@ResponseBody
	@RequestMapping("/checkProjectStatus")
	public ModelMap checkProjectStatus(String projectId,String failReasion,String status){
		ModelMap modelMap=new ModelMap();
		log.info("ajax保存项目审核状态传入的参数:project={},status={},failReasion={}",projectId,status,failReasion);
		if(StringUtils.isBlank(projectId)||!StringUtils.isNumeric(projectId)||StringUtils.isBlank(status)||!StringUtils.isNumeric(status)){
			modelMap.put("status",FAILURE);
            modelMap.put("message","审核失败!");
            return modelMap;
		}
		int flag=0;
		try {
			flag=projectServiceImpl.checkProjectStatus(projectId, failReasion, status);
		} catch (Exception e) {
			log.error("ajax保存项目审核状态失败。    传入的参数:project={},status={},failReasion={}，失败信息e={}",projectId,status,failReasion,e.getMessage());
		}
		if(flag>0){			
			modelMap.put("status",SUCCESS);
			modelMap.put("message","审核成功!");
		}else{
			modelMap.put("status",FAILURE);
			modelMap.put("message","审核失败!");
		}
		return modelMap;
	}
	
	/**
	 * 
	 * checkProjectEdit:(项目编辑). <br/> 
	 * @author dfz
	 * @param id
	 * @param map
	 * @return 
	 * Date:2017年2月13日下午3:14:29 
	 * @since JDK 1.8
	 */
	@RequestMapping("/checkProject/edit/{id}")
	public String checkProjectEdit(@PathVariable("id") String id,ModelMap map){	
		log.info("项目编辑开始，传入的项目id={}",id);
		Project project=projectServiceImpl.findById(Long.parseLong(id));
		List<Industry> industryList=projectServiceImpl.findAllIndustry();
		log.info("查询所有行业信息成功！ industryList = {}", industryList);		
		List<Area> areaParentList=areaServiceImpl.findParentAreaList();
		List<Area> areaChildList=areaServiceImpl.findChildAreaList();
		log.info("查询所有父类城市成功！ areaParentList = {}",areaParentList);
		log.info("查询所有子类城市成功！ areaChildList = {}",areaChildList);
		map.put("project",project);
		map.put("industryList",industryList);
		map.put("areaParentList",areaParentList);
		map.put("areaChildList",areaChildList);
		return BASE_PATH+"project-edit-update";
	}
	/**
	 * ajax项目后台编辑保存
	 */
	@RequestMapping("/ajaxProjectUpdate")
	@ResponseBody
	public ModelMap ajaxProjectUpdate(Project project){
		ModelMap modelMap=new ModelMap();
		log.info("项目编辑开始，传入的项目项目信息={}",project.toString());
		int flag=0;
		try {
			project.setModifyTime(new Date());
			flag=projectServiceImpl.updateProject(project);
		} catch (Exception e) {
			log.error("项目编辑保存失败，传入项目{}，查询失败e={}",project.toString(),e.getMessage());
		}
		if(flag>0){
			modelMap.put("status",SUCCESS);
			modelMap.put("message","编辑保存成功!");
		}else{
			modelMap.put("status",FAILURE);
			modelMap.put("message","编辑保存失败!");
		}
		return modelMap;
	}

	/**
	 * 
	 * projectCheckScoreList:(待打分项目列表). <br/> 
	 * @author dfz
	 * @param pageNum
	 * @param model
	 * @param projectName
	 * @param startTime
	 * @param endTime
	 * @param modelMap
	 * @return 
	 * Date:2017年2月17日上午10:47:20 
	 * @since JDK 1.8
	 */
	@RequestMapping("/projectCheckScoreList")
	private String  projectCheckScoreList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			 Project model, String projectName, String startTime, String endTime, ModelMap modelMap,String flag){
		try {
			 log.debug("分页查询项目列表参数! pageNum = {}, username = {}, startTime = {}, endTime = {}", pageNum, projectName, startTime, endTime);
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("project_name", projectName);
			 map.put("create_time", startTime);
			 map.put("end_time", endTime);
			 map.put("pageNum",pageNum);
			 map.put("pageSize",PAGESIZE);
			 if(StringUtils.isNotBlank(flag)){
				 map.put("is_score",Integer.valueOf(1));  //0：未打分   1：已打分
			 }else{				 
				 map.put("is_score",Integer.valueOf(0));  //0：未打分   1：已打分
			 }
			 PageInfo<Project> pageInfo=projectServiceImpl.findPage(map);
			 log.info("分页查询管理员列表成功！ pageInfo = {}", pageInfo);
			 List<Industry> industryList=projectServiceImpl.findAllIndustry();
			 log.info("查询所有行业信息成功！ industryList = {}", industryList);
			 modelMap.put("pageInfo", pageInfo);
	         modelMap.put("projectName", projectName);
	         modelMap.put("startTime", startTime);
	         modelMap.put("endTime", endTime);
	         modelMap.put("industryList",industryList);
		} catch (Exception e) {
			log.error("分页查询项目列表错误! e = {}", e.getMessage());
		}		
		return BASE_PATH+"project-score-list";
	}
	
	/**
	 * 
	 * projectScoreCheck:(打分/重新打分). <br/> 
	 * @author dfz
	 * @param id
	 * @param map
	 * @return 
	 * Date:2017年2月17日下午3:50:16 
	 * @since JDK 1.8
	 */
	@RequestMapping("/projectScoreCheck/{id}")
	public String projectScoreCheck(@PathVariable("id") String id,ModelMap map){
		log.info("项目查找开始，传入的项目id={}",id);
		map.put("project_id",id);	
		Project project=null;
		try {
			project=projectServiceImpl.findById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			log.error("项目查找失败，传入项目id={}，查询失败e={}",id,e.getMessage());
		}
		List<PEI> listPEI=new ArrayList<PEI>();
		int[] arr=new int[20];
		if(project!=null&&(1==project.getIsScore())){  //1：已打分   查询出所有的打分项,重新打分
			listPEI=peiServiceImpl.findByProjectId(Long.parseLong(id));
			arr=new int[listPEI.size()];
			for (int i = 0; i <listPEI.size(); i++) {
				arr[i]=Integer.parseInt(listPEI.get(i).getCode());
			}					
		}
		map.put("arr", arr);
		return BASE_PATH+"project-score-check";
	}
	
	/**
	 * 
	 * saveProjectScore:(ajax保存项目打分). <br/> 
	 * @author dfz
	 * @param codes  评分规则分数
	 * @param projectId 项目id
	 * @return 
	 * Date:2017年2月16日上午11:56:33 
	 * @since JDK 1.8
	 */
	@RequestMapping("/saveProjectScore")
	@ResponseBody
	public ModelMap saveProjectScore(String[] codes,String projectId,String code){
		ModelMap modelMap=new ModelMap();
		List<PEI> listPEI=new ArrayList<PEI>();
		int flag=0;
		log.info("项目打分开始，传入信息codes={},项目的id={}",codes.toString(),projectId);
		if(codes!=null&&codes.length>=0&&StringUtils.isNotBlank(projectId)&&StringUtils.isNumeric(projectId)&&StringUtils.isNotBlank(code)&&StringUtils.isNumeric(code)){	
			for (int i = 0; i < codes.length; i++) {
				PEI pei=new PEI();
				pei.setProjectId(Long.parseLong(projectId));
				pei.setNumber(i+1);
				pei.setCode(codes[i]);
				listPEI.add(pei);
			}
			log.info("传入的项目打分组模型:"+listPEI.toString());
			try {
				try {
					flag=peiServiceImpl.deleteByProjectId(Long.parseLong(projectId));
				} catch (Exception e) {
					log.error("保存项目打分失败，原因:! e = {}", e.getMessage());
				}
				flag=peiServiceImpl.insertBath(listPEI);
			} catch (Exception e) {
				log.error("保存项目打分失败，原因:! e = {}", e.getMessage());
			}
			if(flag>0){
				Project project=new Project();
				project.setId(Long.parseLong(projectId));
				project.setIsScore(1);
				project.setScore(new BigDecimal(code));
				flag=projectServiceImpl.updateProject(project);
				if(flag>0){
					modelMap.put("status",SUCCESS);
					modelMap.put("message","保存成功!");
				}else{
					modelMap.put("status",FAILURE);
					modelMap.put("message","保存失败!");
				}
			}else{
				modelMap.put("status",FAILURE);
				modelMap.put("message","编辑保存失败!");
			}
		}else{
			modelMap.put("status",FAILURE);
			modelMap.put("message","参数丢失");
		}						
		return modelMap;
	}	
	/**
	 * 
	 * lookProjectDetail:(查看项目详情). <br/> 
	 * @author dfz
	 * @param id  项目id
	 * @return 
	 * Date:2017年2月22日下午2:47:24 
	 * @since JDK 1.8
	 */
	@RequestMapping("/lookProjectDetail/{id}")
	public String lookProjectDetail(@PathVariable("id") String id,ModelMap map){	
		log.info("查看项目详情，传入的项目id={}",id);
		Project project=projectServiceImpl.findById(Long.parseLong(id));
		List<Industry> industryList=projectServiceImpl.findAllIndustry();
		log.info("查询所有行业信息成功！ industryList = {}", industryList);
		List<Area> areaParentList=areaServiceImpl.findParentAreaList();
		List<Area> areaChildList=areaServiceImpl.findChildAreaList();
		log.info("查询所有父类城市成功！ areaParentList = {}",areaParentList);
		log.info("查询所有子类城市成功！ areaChildList = {}",areaChildList);
		map.put("project",project);
		map.put("industryList",industryList);
		map.put("areaParentList",areaParentList);
		map.put("areaChildList",areaChildList);
		return BASE_PATH+"project-edit-look";
	}
	
	/**
	 * 
	 * projectMilepostList:(跳转对应项目的). <br/> 
	 * @author dfz
	 * @param pageNum      页数
	 * @param projectId   项目id
	 * @param modelMap
	 * @return 
	 * Date:2017年2月22日下午2:55:30 
	 * @since JDK 1.8
	 */
	@RequestMapping("/projectMilepostList")
	public String projectMilepostList(@RequestParam(value="pageNum",defaultValue="1")int pageNum,String projectId,ModelMap modelMap){	
		log.info("进入项目里程碑页面,传入参数:pageNum={},projectId={}",pageNum,projectId);		
		Map<String,Object> map=new HashMap<String,Object>();
		 map.put("pageNum",pageNum);
		 map.put("pageSize",PAGESIZE);
		 map.put("projectId",Long.parseLong(projectId));
		PageInfo<ProjectMilepost> pageInfo=new PageInfo<ProjectMilepost>();
		try {
			pageInfo=projectMilepostServiceImpl.findProjectMilepostListByProjectId(map);
		} catch (Exception e) {
			log.error("进入项目里程碑页面,出错！！！,传入参数：pageNum={},projectId={},错误信息={}",pageNum,projectId,e.getMessage());
		}
		modelMap.put("pageInfo",pageInfo);		
		modelMap.put("projectId",projectId);
		return BASE_PATH+"project-milepost-list";
	}
	
	@ResponseBody
	@RequestMapping("/projectDeleteMilepost/{id}")
	public ModelMap projectDeleteMilepost(@PathVariable("id") String id){
		ModelMap map=new ModelMap();
		log.info("删除该条项目里程碑,传入参数:id={}",id);		
		if(StringUtils.isBlank(id)||!StringUtils.isNumeric(id)){
			map.put("status",FAILURE);
            map.put("message","删除失败!");
            return map;
		}
		int flag=0;
		try {
			flag=projectMilepostServiceImpl.deleteById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			log.error("删除里程碑错误! e = {}，里程碑id={}", e.getMessage(),id);
		}
		if(flag>0){
			map.put("status",SUCCESS);
			map.put("message","成功!");
		}else{
			map.put("status",FAILURE);
			map.put("message","失败!");
		}			
		return map;
	}
	/**
	 * 
	 * lookMilepostImg:(跳转图片列表页面). <br/> 
	 * @author dfz
	 * @param id
	 * @return 
	 * Date:2017年2月22日下午7:01:13 
	 * @since JDK 1.8
	 */
	@RequestMapping("/lookMilepostImg/{id}")
	public String lookMilepostImg(@PathVariable("id") String id,ModelMap modelMap){
		ProjectMilepost projectMilepost =new ProjectMilepost();
		List<String> listImg=new ArrayList<String>();
		log.info("查询出里程碑对象,传入参数:id={}",id);	
		try {
			projectMilepost=projectMilepostServiceImpl.findByProjectMilepostId(Long.parseLong(id));
		} catch (NumberFormatException e) {
			log.error("查询里程碑错误! e = {}，里程碑id={}", e.getMessage(),id);
		}
		if(StringUtils.isNotBlank(projectMilepost.getProjectNumber())){
			String imgCodes=projectMilepost.getImgCodes();
			if(StringUtils.isNotBlank(imgCodes)){
				if(imgCodes.indexOf(";")>-1){
					String[] arr=imgCodes.split(";");
					for (int i = 0; i < arr.length; i++) {
						listImg.add(arr[i]);
					}
				}else{
					listImg.add(imgCodes);
				}
			}
		}
		log.info("查出的图组：listImg={}",listImg.toArray());
		modelMap.put("listImg", listImg);
		modelMap.put("projectMilepost", projectMilepost);
		return BASE_PATH+"project-milepost-img";
	}
}
  