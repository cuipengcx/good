/** 
 * Project Name:good-manage 
 * File Name:Area.java 
 * Package Name:com.jk.model 
 * Date:2017年2月22日上午9:45:35 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import javax.persistence.Table;

/** 
 * ClassName:Area <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月22日 上午9:45:35 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Table(name="t_area")
public class Area  extends BaseEntity{
	
	private long parentId;  //父类id
	private String areaName;//城市名称
	private Boolean isHot;//是否热门
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	

}
  