/** 
 * Project Name:good-manage 
 * File Name:UserComment.java 
 * Package Name:com.jk.model 
 * Date:2017年2月23日上午10:47:36 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import javax.persistence.Table;

/** 
 * ClassName:UserComment <br/> 
 * Function: TODO 会员对项目的评论,评论表              只有会员对该项目捐过钱，才可以进行评论<br/> 
 * Date:     2017年2月23日 上午10:47:36 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Table(name="t_user_comment")
public class UserComment  extends BaseEntity{
	
	private  long projectId;//项目id	
	private  String projectNumber; //项目编号
	private  String projectName;//项目名称
	private  long   userId;	  //会员id
	private  String username; //会员名称
	private  String contents; //评论内容
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
  