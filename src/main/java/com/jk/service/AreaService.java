/** 
 * Project Name:good-manage 
 * File Name:AreaService.java 
 * Package Name:com.jk.service 
 * Date:2017年2月22日上午9:49:34 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service;

import java.util.List;
import com.jk.model.Area;

/** 
 * ClassName:AreaService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月22日 上午9:49:34 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface AreaService  extends BaseService<Area>{

	public List<Area> findParentAreaList();
	public List<Area> findChildAreaList();
}
  