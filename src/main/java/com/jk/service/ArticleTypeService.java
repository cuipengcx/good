package com.jk.service;

import java.util.List;

import com.jk.model.ArticleTypes;

/**
 * Created by JK on 2017/1/19.
 */
public interface ArticleTypeService extends BaseService<ArticleTypes>{

    
    /**
     * 取得资讯类别列表
     * @param 
     * @return
     */
    public List<ArticleTypes> queryArticleTypes(Long parentid);
    
    /**
     * 根据子类别id取得资讯子类别列表
     * @param 
     * @return
     */
    public List<ArticleTypes> queryChildTypes(Long id);
  
    
}
