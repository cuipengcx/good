package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.Content;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentService extends BaseService<Content> {

    /**
     * 分页根据新闻类型查询新闻列表
     * @param catId
     * @return
     */
    PageInfo<Content> findPageNews(Integer pageNum, Integer pageSize, Long catId);
}
