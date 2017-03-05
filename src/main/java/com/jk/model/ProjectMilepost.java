/** 
 * Project Name:good-manage 
 * File Name:ProjectMilepost.java 
 * Package Name:com.jk.model 
 * Date:2017年2月22日下午2:06:41 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import javax.persistence.Table;

/** 
 * ClassName:ProjectMilepost <br/> 
 * Function: 项目里程碑 <br/> 
 * Date:     2017年2月22日 下午2:06:41 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Table(name="t_project_milepost")
public class ProjectMilepost  extends BaseEntity{
	
	
	private long projectId;//项目id
	private String projectNumber;//项目编号
	private String projectName;	//项目名称
	private String imgCodes;  //图片组
	private String contents;  //里程碑内容介绍
	private String title;//标题
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String img;  //存放第一张图片
		
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getImgCodes() {
		return imgCodes;
	}
	public void setImgCodes(String imgCodes) {
		this.imgCodes = imgCodes;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
  