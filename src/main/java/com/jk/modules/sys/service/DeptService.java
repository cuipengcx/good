package com.jk.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
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
public interface DeptService extends IService<Dept> {

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
    Page<Dept> findPage(Integer pageNum, Integer pageSize, Long deptId, String name, String startTime, String endTime);

    /**
     * 获取部门及其子部门ID集合，返回值用逗号拼接
     * @param deptId 当前部门ID
     * @return
     */
    String getDeptIdAndSubDeptIdsAsStr(Long deptId);

    /**
     * 根据父部门Id查询子部门id集合
     * @param parentId 父部门ID
     * @return
     */
    List<Long> findSubDeptIdListByParentId(Long parentId);

    /**
     * 根据父部门获取当前部门ID集合
     * @param parentId
     * @return
     */
    List<Long> findDeptIdListByParentId(Long parentId);
}
