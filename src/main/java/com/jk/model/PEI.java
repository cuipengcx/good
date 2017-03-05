/** 
 * Project Name:good-manage 
 * File Name:PEI.java 
 * Package Name:com.jk.model 
 * Date:2017年2月16日上午9:37:34 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import javax.persistence.Table;

/** 
 * ClassName:PEI <br/> 
 * Function: TODO 项目打分pei表 <br/> 
 * Date:     2017年2月16日 上午9:37:34 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Table(name="t_pei")
public class PEI extends BaseEntity{
	
	private long projectId;//项目ID	
	private int number;//选项 	
	private String code;//分数
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
  