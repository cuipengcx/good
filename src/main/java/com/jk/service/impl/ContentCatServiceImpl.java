package com.jk.service.impl;

import com.jk.mapper.ContentCatMapper;
import com.jk.model.ContentCat;
import com.jk.service.ContentCatService;
import com.jk.vo.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
@Transactional
@Service
public class ContentCatServiceImpl extends BaseServiceImpl<ContentCat> implements ContentCatService {

    @Resource
    private ContentCatMapper contentCatMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ContentCat> findListNewsCat() {
        ContentCat contentCat = new ContentCat();
        contentCat.setParentName("新闻中心");
        return super.findListByWhere(contentCat);
    }

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return contentCatMapper.findTreeList();
    }
}
