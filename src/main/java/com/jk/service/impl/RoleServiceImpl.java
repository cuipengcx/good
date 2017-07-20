package com.jk.service.impl;

import com.jk.mapper.RoleMapper;
import com.jk.mapper.RolePermissionMapper;
import com.jk.model.Role;
import com.jk.model.RolePermission;
import com.jk.service.RoleService;
import com.jk.shiro.AuthenticationRealm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cuiP on 2017/2/8.
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;


    @Transactional(readOnly=true)
    @Override
    public Role findByName(String name) {
        Role role = new Role();
        role.setName(name);
        return this.findOne(role);
    }

    @Override
    public Role findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public Boolean deleteRoleAndRolePermissionByRoleId(Long roleId) {
        //删除角色
        int count1 = this.deleteById(roleId);

        //级联删除该角色所关联的权限
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermissionMapper.delete(rolePermission);
        return count1 == 1;
    }
}
