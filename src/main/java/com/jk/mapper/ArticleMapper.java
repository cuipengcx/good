package com.jk.mapper;

import java.util.List;
import java.util.Map;

import com.jk.model.Article;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface ArticleMapper extends MyMapper<Article>{
    public List<Article> articleList(Map map);
}
