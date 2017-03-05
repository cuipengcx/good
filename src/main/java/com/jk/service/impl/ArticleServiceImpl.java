package com.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.ArticleMapper;
import com.jk.model.Article;
import com.jk.service.ArticleService;

/**
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;
    
    @Transactional(readOnly=true)
    @Override
    public PageInfo<Article> findPage(Integer pageNum, Integer pageSize, String findtext, String ordertype,String ordervalue) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("findtext", findtext);
        if(ordertype!=null){
            if("0".equals(ordertype)){
                map.put("ordertype" , " ta.id ");
            }else if("1".equals(ordertype)){
                map.put("ordertype" , " tatype.name ");
            }else if("2".equals(ordertype)){
                map.put("ordertype" , " ta.create_time ");
            }
            
            if("0".equals(ordervalue)){
                map.put("ordervalue" , " asc ");
            }else if("1".equals(ordervalue)){
                map.put("ordervalue" , " desc ");
            }
        }
        
        PageHelper.startPage(pageNum, pageSize,true);
        List<Article> listArticle=articleMapper.articleList(map);
        return new PageInfo<Article>(listArticle);
    }

    @Transactional(readOnly=true)
    @Override
    public Article findByOption(String keywords) {
        Article article = new Article();

        return this.findOne(article);
    }

    
    @Transactional(readOnly=true)
    @Override
    public PageInfo<Article> findPageFront(Integer pageNum, Integer pageSize, Integer[] articletype, String order) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("articletype", articletype);
        map.put("is_use", 1);
        if("0".equals(order)){
            map.put("ordertype", "create_time");
            map.put("ordervalue", "desc");
        } else {
            map.put("ordertype", "order_number");
            map.put("ordervalue", "asc");
        }

        
        PageHelper.startPage(pageNum, pageSize,true);
        List<Article> listArticle=articleMapper.articleList(map);
        return new PageInfo<Article>(listArticle);
    }
}
