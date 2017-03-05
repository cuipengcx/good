/** 
 * Project Name:good-manage 
 * File Name:AreaMapper.java 
 * Package Name:com.jk.mapper 
 * Date:2017年2月22日上午9:48:53 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.mapper;

import java.util.List;
import com.jk.model.Area;
import com.jk.util.MyMapper;

/** 
 * ClassName:AreaMapper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月22日 上午9:48:53 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface AreaMapper extends MyMapper<Area>{
	/*
	 * 父类城市
	 */
	public List<Area> findParentAreaList();
	/*
	 * 子类城市
	 */
	public List<Area> findChildAreaList();
	

}
  