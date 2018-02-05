package com.jk.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.common.base.model.BaseEntity;
import com.jk.modules.sys.mapper.DeptMapper;
import com.jk.modules.sys.model.Dept;
import com.jk.modules.sys.service.DeptService;
import com.jk.modules.sys.vo.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @package: com.jk.modules.sys.service.impl
 * @description:
 * @author: cuiP
 * @date: 2018/1/30 14:58
 * @version: V1.0.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return deptMapper.findTreeList();
    }

    @Transactional(readOnly=true)
    @Override
    public Page<Dept> findPage(Integer pageNum, Integer pageSize, Long deptId, String name, String startTime, String endTime) {
        return this.selectPage(
                new Page<Dept>(pageNum, pageSize),
                new EntityWrapper<Dept>()
                        .eq(null != deptId, "parent_id", deptId)
                        .like(StringUtils.isNotBlank(name), "name", name)
                        .ge(StringUtils.isNotBlank(startTime), "create_time", startTime + "00:00:00")
                        .le(StringUtils.isNotBlank(endTime), "create_time", endTime + "23:59:59")
                        .orderBy("sort", false)
        );

    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> findDeptIdListByParentId(Long parentId) {
        List<Dept> subDeptList = this.selectByMap(Collections.singletonMap("parent_id", parentId));
        return subDeptList.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public String getDeptIdAndSubDeptIdsAsStr(Long deptId) {
        List<Long> deptIds = this.findSubDeptIdListByParentId(deptId);
        return StringUtils.join(deptIds, ",");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> findSubDeptIdListByParentId(Long parentId) {
        //部门及子部门ID列表
        List<Long> deptIdAndSubDeptIdList = new ArrayList<>();
        //添加本部门
        deptIdAndSubDeptIdList.add(parentId);

        List<Long> subDeptIdList = this.findDeptIdListByParentId(parentId);

        getDeptIdTreeList(subDeptIdList, deptIdAndSubDeptIdList);
        return deptIdAndSubDeptIdList;
    }

    /**
     * 递归取部门及子部门ID集合
     * @param subDeptIdList
     * @param deptIdAndSubDeptIdList
     */
    private void getDeptIdTreeList(List<Long> subDeptIdList, List<Long> deptIdAndSubDeptIdList){
        if(CollectionUtil.isNotEmpty(subDeptIdList)){
            for (Long deptId : subDeptIdList) {
                List<Long> list = this.findDeptIdListByParentId(deptId);
                getDeptIdTreeList(list, deptIdAndSubDeptIdList);

                deptIdAndSubDeptIdList.add(deptId);
            }
        }
    }
}
