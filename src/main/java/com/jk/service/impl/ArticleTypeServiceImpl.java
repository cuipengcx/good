package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.mapper.ArticleTypesMapper;
import com.jk.model.ArticleTypes;
import com.jk.service.ArticleTypeService;

/**
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class ArticleTypeServiceImpl extends BaseServiceImpl<ArticleTypes> implements ArticleTypeService{

    @Autowired
    private ArticleTypesMapper articletypesMapper;
    
    
    @Transactional(readOnly=true)
    @Override
    public List<ArticleTypes> queryArticleTypes(Long parentid){

        return articletypesMapper.articletypesList(parentid);
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<ArticleTypes> queryChildTypes(Long id){
        
        return articletypesMapper.childtypesList(id);
    }

}
