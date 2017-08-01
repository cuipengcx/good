package com.jk.modules.cms.service;

import com.jk.common.base.service.BaseService;
import com.jk.modules.cms.model.ContentCat;
import com.jk.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentCatService extends BaseService<ContentCat> {
    /**
     * 查询新闻分类
     * @return
     */
    List<ContentCat> findListNewsCat();

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
