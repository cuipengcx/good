package com.jk.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.jk.common.base.service.BaseService;
import com.jk.modules.sys.model.Dept;
import com.jk.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @package: com.jk.modules.sys.service
 * @description:
 * @author: cuiP
 * @date: 2018/1/30 14:58
 * @version: V1.0.0
 */
public interface DeptService extends BaseService<Dept> {

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();

    /**
     * 分页查询组织机构列表
     * @param pageNum
     * @param pageSize
     * @param deptId
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<Dept> findPage(Integer pageNum, Integer pageSize, Long deptId, String name, String startTime, String endTime);

    /**
     * 获取部门及其子部门ID集合
     * @param deptId
     * @return
     */
    String getDeptIdList(Long deptId);
}
