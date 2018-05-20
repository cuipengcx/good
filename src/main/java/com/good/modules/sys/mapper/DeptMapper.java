package com.good.modules.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.good.modules.sys.model.Dept;
import com.good.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @package: com.jk.modules.sys.mapper
 * @description:
 * @author: cuiP
 * @date: 2018/1/30 14:57
 * @version: V1.0.0
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
