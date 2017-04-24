package com.jk.service.impl;

import com.github.pagehelper.PageInfo;
import com.jk.model.Content;
import com.jk.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
@Transactional
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Transactional(readOnly = true)
    @Override
    public PageInfo<Content> findPageNews(Integer pageNum, Integer pageSize, Long catId) {
        Content content = new Content();
        content.setContentCatId(catId);
        return super.findPageListByWhere(pageNum, pageSize, content);
    }
}
