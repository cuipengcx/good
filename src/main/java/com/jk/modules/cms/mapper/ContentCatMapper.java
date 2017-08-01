package com.jk.modules.cms.mapper;

import com.jk.modules.cms.model.ContentCat;
import com.jk.common.base.mapper.BaseMapper;
import com.jk.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentCatMapper extends BaseMapper<ContentCat> {
    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
