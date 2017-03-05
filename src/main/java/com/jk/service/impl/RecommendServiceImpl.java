package com.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ProjectMapper;
import com.jk.mapper.RecommendMapper;
import com.jk.model.Project;
import com.jk.model.Recommend;
import com.jk.service.RecommendService;

/**
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class RecommendServiceImpl extends BaseServiceImpl<Recommend> implements RecommendService{

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private ProjectMapper projectMapper;
    
    @Transactional(readOnly=true)
    @Override
    public PageInfo<Recommend> queryAllRecommend(Integer pageNum, Integer pageSize, String findtype){
        
        PageHelper.startPage(pageNum, pageSize,true);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("findtype", findtype);
        
        List<Recommend> listRecommend = recommendMapper.recommendList(map);
        
        return new PageInfo<Recommend>(listRecommend);
    }
    
    
    /**
     * 检查该推荐id
     * @param 
     * @return
     */
    public boolean checkRecommendId(Integer type, Long id){
        
        if(type == 0 || type == 1){
            
            Project project = projectMapper.findById(id);

            if(project!=null && project.getAdminStatus() == 0 && project.getStatus() == 2){
                return true;
            } else {
                return false;
            }

        } else if(type == 2){
            
            return true;
        } else {
            return false;
        }
    }
}
