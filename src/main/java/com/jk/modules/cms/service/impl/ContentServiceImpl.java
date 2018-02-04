package com.jk.modules.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.cms.mapper.ContentMapper;
import com.jk.modules.cms.model.Content;
import com.jk.modules.cms.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public Page<Content> findPage(Integer pageNum, Integer pageSize, Long catId, String title, String startTime, String endTime) {
        return this.selectPage(
                new Page<>(pageNum, pageSize),
                new EntityWrapper<Content>()
                        .eq(null != catId, "content_cat_id", catId)
                        .like(StringUtils.isNotBlank(title), "title", title)
                        .ge(StringUtils.isNotBlank(startTime), "create_time", startTime + "00:00:00")
                        .le(StringUtils.isNotBlank(endTime), "create_time", endTime + "23:59:59")
                        .orderBy("create_time", false)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Content> findPageNews(Integer pageNum, Integer pageSize, Long catId) {
        return this.findPage(pageNum, pageSize, catId, null, null, null);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Content> findTop3ByCatId(Long catId) {
        return this.findPageNews(1, 3, catId);
    }
}
