package com.jk.mapper;

import java.util.List;

import com.jk.model.ArticleTypes;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface ArticleTypesMapper extends MyMapper<ArticleTypes>{
    public List<ArticleTypes> articletypesList(Long parentid);
    
    public List<ArticleTypes> childtypesList(Long id);
}
