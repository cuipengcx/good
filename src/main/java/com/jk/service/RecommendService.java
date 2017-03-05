package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.Recommend;

/**
 * Created by JK on 2017/1/19.
 */
public interface RecommendService extends BaseService<Recommend>{
      
    /**
     * 取得所有推荐
     * @param 
     * @return
     */
    public PageInfo<Recommend> queryAllRecommend(Integer pageNum, Integer pageSize, String findtype);
    
    /**
     * 检查该推荐id
     * @param 
     * @return
     */
    public boolean checkRecommendId(Integer type, Long id);
}
