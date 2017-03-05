/** 
 * Project Name:good-manage 
 * File Name:Industry.java 
 * Package Name:com.jk.model 
 * Date:2017年2月9日下午2:15:26 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;  
/** 
 * ClassName:Industry <br/> 
 * Function: 项目类别表 <br/> 
 * Date:     2017年2月9日 下午2:15:26 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Industry  extends BaseEntity{

	private String industryName;

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
}
  