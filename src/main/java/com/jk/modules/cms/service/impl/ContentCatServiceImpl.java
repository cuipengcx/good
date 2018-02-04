package com.jk.modules.cms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.cms.mapper.ContentCatMapper;
import com.jk.modules.cms.model.ContentCat;
import com.jk.modules.cms.service.ContentCatService;
import com.jk.modules.sys.vo.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ContentCatServiceImpl extends ServiceImpl<ContentCatMapper, ContentCat> implements ContentCatService {

    @Resource
    private ContentCatMapper contentCatMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ContentCat> findListNewsCat() {
        return this.selectByMap(Collections.singletonMap("parent_name", "新闻中心"));
    }

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return contentCatMapper.findTreeList();
    }
}
