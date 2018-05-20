package com.good.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.good.modules.sys.mapper.RoleMapper;
import com.good.modules.sys.model.Role;
import com.good.modules.sys.service.RolePermissionService;
import com.good.modules.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 *
 * Created by cuiP on 2017/2/8.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionService rolePermissionService;


    @Transactional(readOnly=true)
    @Override
    public Role findByName(String name) {
        return this.selectOne(
                new EntityWrapper<Role>()
                        .eq(StrUtil.isNotEmpty(name), "name", name)
        );
    }

    @Override
    public Role findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public void deleteRoleAndRolePermissionByRoleId(Long roleId) {
        //删除角色
        this.deleteById(roleId);

        //级联删除该角色所关联的权限
        rolePermissionService.deleteByMap(Collections.singletonMap("role_id", roleId));
    }
}
