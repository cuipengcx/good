package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.Article;

/**
 * Created by JK on 2017/1/19.
 */
public interface ArticleService extends BaseService<Article>{
    /**
     *
     * @param pageNum  当前页码
     * @param pageSize  每页显示条数
     * @param username 用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws Exception
     */
    public PageInfo<Article> findPage(Integer pageNum ,Integer pageSize ,String findtext,String ordertype,String ordervalue) throws Exception;

    /**
     * 根据类别/标题查询资讯
     * @param keywords
     * @return
     */
    public Article findByOption(String keywords);

    /**
    *
    * 前台根据类别检索咨询
    * 
    * @param pageNum  当前页码
    * @param pageSize  每页显示条数
    * @param articletype 类别，数组形式
    * @param order 排序种类 0:create_time, 1:order_number
    * @return
    * @throws Exception
    */
    public PageInfo<Article> findPageFront(Integer pageNum, Integer pageSize, Integer[] articletype, String order);
    
}
