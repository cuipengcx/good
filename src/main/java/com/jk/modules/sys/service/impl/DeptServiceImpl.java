package com.jk.modules.sys.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.sys.mapper.DeptMapper;
import com.jk.modules.sys.model.Dept;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.service.DeptService;
import com.jk.modules.sys.vo.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public String getDeptIdList(Long deptId) {

        return null;
    }
}
