/** 
 * Project Name:good-manage 
 * File Name:ExpertComment.java 
 * Package Name:com.jk.model 
 * Date:2017年2月17日下午4:35:45 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import javax.persistence.Table;

/** 
 * ClassName:ExpertComment <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月17日 下午4:35:45 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Table(name="t_expert_comment")
public class ExpertComment  extends BaseEntity{
			
	private long projectId;      //项目id
	private String projectNumber;//项目编号	
	private String projectName;//项目名称
	private String imgCode;//头像
	private String contents;//评论内容	
	private String expertName;//专家名称
	private String expertTitle;//专家职称
	
	
	public String getExpertTitle() {
		return expertTitle;
	}
	public void setExpertTitle(String expertTitle) {
		this.expertTitle = expertTitle;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	@Override
	public String toString() {
		return "ExpertComment [projectId=" + projectId + ", projectNumber=" + projectNumber + ", projectName="
				+ projectName + ", imgCode=" + imgCode + ", contents=" + contents + ", expertName=" + expertName + "]";
	}
	
	
	
}
  