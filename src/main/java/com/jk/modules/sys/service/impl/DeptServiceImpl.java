package com.jk.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.modules.sys.mapper.DeptMapper;
import com.jk.modules.sys.model.Dept;
import com.jk.modules.sys.service.DeptService;
import com.jk.modules.sys.vo.TreeNode;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @package: com.jk.modules.sys.service.impl
 * @description:
 * @author: cuiP
 * @date: 2018/1/30 14:58
 * @version: V1.0.0
 */
@Transactional
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return deptMapper.findTreeList();
    }

    @Transactional(readOnly=true)
    @Override
    public PageInfo<Dept> findPage(Integer pageNum, Integer pageSize, Long deptId, String name, String startTime, String endTime) {
        Example example = new Example(Dept.class);
        Example.Criteria criteria = example.createCriteria();

        if(null != deptId){
            criteria.andEqualTo("parentId", deptId);
        }if(StrUtil.isNotEmpty(name)){
            criteria.andLike("name", "%" + name + "%");
        }if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        //排序
        example.orderBy("sort").desc();

        //分页
        PageHelper.startPage(pageNum,pageSize);

        List<Dept> deptList = this.selectByExample(example);
        return new PageInfo<Dept>(deptList);
    }

    @Transactional(readOnly = true)
    @Override
    public String getDeptIdList(Long deptId) {

        return null;
    }
}
