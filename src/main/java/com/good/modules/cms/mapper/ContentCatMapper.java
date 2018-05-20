package com.good.modules.cms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.good.modules.cms.model.ContentCat;
import com.good.modules.sys.vo.TreeNode;

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
