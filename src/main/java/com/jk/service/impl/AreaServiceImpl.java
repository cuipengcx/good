/** 
 * Project Name:good-manage 
 * File Name:AreaServiceImpl.java 
 * Package Name:com.jk.service.impl 
 * Date:2017年2月22日上午9:50:01 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jk.mapper.AreaMapper;
import com.jk.model.Area;
import com.jk.service.AreaService;

/** 
 * ClassName:AreaServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月22日 上午9:50:01 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Service
@Transactional
public class AreaServiceImpl  extends BaseServiceImpl<Area> implements AreaService{

	@Resource
	private AreaMapper areaMapper;
		
	@Override
	public List<Area> findParentAreaList() {
		return areaMapper.findParentAreaList();
	}
	@Override
	public List<Area> findChildAreaList() {
		return areaMapper.findChildAreaList();
	}
	
	

}
  