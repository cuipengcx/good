package com.jk.mapper;

import com.jk.model.ContentCat;
import com.jk.util.MyMapper;
import com.jk.vo.TreeNode;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentCatMapper extends MyMapper<ContentCat> {
    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
