package com.good.modules.cms.service;

import com.baomidou.mybatisplus.service.IService;
import com.good.modules.cms.model.ContentCat;
import com.good.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentCatService extends IService<ContentCat> {
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
