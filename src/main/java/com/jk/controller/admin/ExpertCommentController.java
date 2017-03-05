/** 
 * Project Name:good-manage 
 * File Name:ExpertCommentController.java 
 * Package Name:com.jk.controller.admin 
 * Date:2017年2月17日下午4:33:09 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.controller.admin;

import java.util.Date;
import java.util.HashMap;
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
import com.jk.model.ExpertComment;
import com.jk.model.Project;
import com.jk.model.UserComment;
import com.jk.service.ExpertCommentService;
import com.jk.service.ProjectService;
import com.jk.service.UserCommentService;

/** 
 * ClassName:ExpertCommentController <br/> 
 * Function: 专家评论相关功能类 <br/> 
 * Date:     2017年2月17日 下午4:33:09 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Controller
@RequestMapping("/admin/comment")
public class ExpertCommentController  extends BaseController{
	private  static final String PATH_ROAD="admin/project/";
	
	@Autowired
	private ExpertCommentService expertCommentServiceImpl;
	@Autowired
	private ProjectService projectServiceImpl;
	@Autowired
	private UserCommentService userCommentServiceImpl;
	
	/**
	 * 
	 * finaList:(项目专家评论列表). <br/> 
	 * @author dfz
	 * @param pageNum
	 * @param modelMap
	 * @param projectId
	 * @return 
	 * Date:2017年2月20日上午11:51:07 
	 * @since JDK 1.8
	 */
	@RequestMapping("/listExpertComment")
	public String finaList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,ModelMap modelMap,String projectId){
		log.info("专家评论列表开始----传入参数:projectId={}",projectId);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pageNum",pageNum);
		map.put("pageSize",PAGESIZE);
		map.put("projectId",projectId==null?null:Long.parseLong(projectId));
		PageInfo<ExpertComment> pageInfo=null;
		try {
			pageInfo = expertCommentServiceImpl.findList(map);
		} catch (Exception e) {
			log.error("查询专家列表失败。传入参数:projectId={},错误信息={}",projectId,e.getMessage());
		}
		modelMap.put("pageInfo",pageInfo);
		modelMap.put("projectId",projectId);
		return PATH_ROAD+"project-expert-list";
	}
	/**
	 * 
	 * deleteExpertComment:(ajax删除项目下的专家评论). <br/> 
	 * @author dfz
	 * @param id
	 * @return 
	 * Date:2017年2月20日下午3:55:28 
	 * @since JDK 1.8
	 */
	@ResponseBody
	@RequestMapping("/deleteExpertComment")
	public ModelMap deleteExpertComment(String id){
		log.info("需要删除的评论id={}",id);
		ModelMap map=new ModelMap();
		if(StringUtils.isBlank(id)||!StringUtils.isNumeric(id)){
			map.put("status",FAILURE);
            map.put("message","删除失败!");	
		}else{
			int flag=0;
			try {
				flag=expertCommentServiceImpl.deleteById(Long.parseLong(id));
			} catch (NumberFormatException e) {
				log.error("删除评论错误! e = {}，评论id={}", e.getMessage(),id);
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
	 * addComment:(跳转评论新增页面). <br/> 
	 * @author dfz
	 * @param projectId  项目id
	 * @param modelMap
	 * @return 
	 * Date:2017年2月20日下午6:12:10 
	 * @since JDK 1.8
	 */
	@RequestMapping("/addComment")
	public String addComment(String projectId,ModelMap modelMap){
		log.info("新增项目专家评论开始,该项目的id={}",projectId);
		modelMap.put("projectId",projectId);
		return PATH_ROAD+"project-expert-add";
	}
	/**
	 * 
	 * ajaxAddComment:(ajax保存评论新增). <br/> 
	 * @author dfz
	 * @param expertComment
	 * @return 
	 * Date:2017年2月20日下午6:13:53 
	 * @since JDK 1.8
	 */
	@ResponseBody
	@RequestMapping("/ajaxAddComment")
	public ModelMap ajaxAddComment(ExpertComment expertComment){
		log.info("新增专家评论，评论传入参数:{}",expertComment);
		ModelMap modelMap=new ModelMap();
		Project project=null;
		try {
			project=projectServiceImpl.findById(expertComment.getProjectId());
		} catch (Exception e) {
			log.error("新增专家评论：关联查询出项目失败，输入参数={}，原因：{}",expertComment.toString(),e.getMessage());
		}
		if(project!=null){
			expertComment.setProjectNumber(project.getProjectNumber());
			expertComment.setProjectName(project.getProjectName());
		}
		expertComment.setCreateTime(new Date());
		int flag=0;
		try {
			flag=expertCommentServiceImpl.save(expertComment);
		} catch (Exception e) {
			log.error("新增专家评论：保存失败，输入参数={}，原因：{}",expertComment.toString(),e.getMessage());
		}		
		if(flag>0){			
			modelMap.put("status",SUCCESS);
			modelMap.put("message","新增成功!");
		}else{
			modelMap.put("status",FAILURE);
			modelMap.put("message","新增失败!");
		}
		return modelMap;
	}
	/**
	 * 
	 * updateExpertComment:(跳转编辑页面). <br/> 
	 * @author dfz
	 * @param id
	 * @param modelMap
	 * @return 
	 * Date:2017年2月21日上午11:09:53 
	 * @since JDK 1.8
	 */
	@RequestMapping("/updateExpertComment/{id}")
	public String updateExpertComment(@PathVariable String id,ModelMap modelMap){
		log.info("跳转编辑评论页面，评论传入参数:id={}",id);
		if(StringUtils.isNotBlank(id)||StringUtils.isNumeric(id)){
			ExpertComment expertComment=null;
			try {
				expertComment=expertCommentServiceImpl.findById(Long.parseLong(id));
			} catch (NumberFormatException e) {
				log.info("跳转编辑评论页面失败，评论传入参数:id={}，失败原因：{}",id,e.getMessage());
			}
			modelMap.put("expertComment", expertComment);
		}
		return PATH_ROAD+"project-expert-update";
	}
	/**
	 * 
	 * ajaxProjectUpdate:(ajax保存编辑专家评论内容). <br/> 
	 * @author dfz
	 * @param expertComment
	 * @return 
	 * Date:2017年2月21日上午11:21:32 
	 * @since JDK 1.8
	 */
	@RequestMapping("/ajaxUpdateComment")
	@ResponseBody
	public ModelMap ajaxProjectUpdate(ExpertComment expertComment){
		log.info("编辑专家评论页面,传入参数：{}",expertComment);
		ModelMap modelMap=new ModelMap();
		int flag=0;
		try {
			expertComment.setModifyTime(new Date());
			flag=expertCommentServiceImpl.updateByExpertComment(expertComment);
		} catch (Exception e) {
			log.info("编辑评论失败，评论传入参数对象={}，失败原因：{}",expertComment.toString(),e.getMessage());
		}
		if(flag>0){			
			modelMap.put("status",SUCCESS);
			modelMap.put("message","编辑成功!");
		}else{
			modelMap.put("status",FAILURE);
			modelMap.put("message","编辑失败!");
		}
		return modelMap;
	}
	
	/**
	 * 会员评论列表
	 */
	@RequestMapping("/userCommentList")
	public  String  userCommentList(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,ModelMap modelMap,String projectId ){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pageNum",pageNum);
		map.put("pageSize",PAGESIZE);
		map.put("projectId",projectId==null?null:Long.parseLong(projectId));		
		PageInfo<UserComment> pageInfo=null;
		try {
			pageInfo =userCommentServiceImpl.selectAllByProjectId(map);
		} catch (Exception e) {
			log.error("查询会员评论列表失败。传入参数:projectId={},错误信息={}",projectId,e.getMessage());
		}		
		modelMap.put("pageInfo",pageInfo);		
		modelMap.put("projectId",projectId);
		return PATH_ROAD+"project-user-list";
	}
	@ResponseBody
	@RequestMapping("/deleteUserComment")
	public ModelMap deleteUserComment(String id){
		log.info("需要删除的评论id={}",id);
		ModelMap map=new ModelMap();
		if(StringUtils.isBlank(id)||!StringUtils.isNumeric(id)){
			map.put("status",FAILURE);
            map.put("message","删除失败!");	
		}else{
			int flag=0;
			try {
				flag=userCommentServiceImpl.deleteById(Long.parseLong(id));
			} catch (NumberFormatException e) {
				log.error("删除评论错误! e = {}，评论id={}", e.getMessage(),id);
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
	
	
}
  