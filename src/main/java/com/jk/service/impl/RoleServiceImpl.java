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
    @Resource
    private AuthenticationRealm authenticationRealm;

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
    public Boolean saveRoleAndRolePermission(Role role, Long[] permissionIds) {
        int count  = 0;
        int countList = 0;
        //保存角色信息
        count = this.save(role);
        //保存该角色所拥有的权限
        List<RolePermission> rolePermissionList = this.getRolePermissionList(role.getId(), permissionIds);
        if(rolePermissionList.size() > 0){
            countList = rolePermissionMapper.insertList(rolePermissionList);
        }
        return count == 1;
    }

    @Override
    public Boolean updateRoleAndRolePermission(Role role, Long[] permissionIds) {
        int count  = 0;
        int countList = 0;
        //更新角色信息
        count = this.updateSelective(role);
        //更新该角色所拥有的权限
        //先删除当前角色所拥有的权限再重现插入
        RolePermission delRolePermission = new RolePermission();
        delRolePermission.setRoleId(role.getId());
        rolePermissionMapper.delete(delRolePermission);

        List<RolePermission> rolePermissionList = this.getRolePermissionList(role.getId(), permissionIds);
        if(rolePermissionList.size() > 0){
            countList = rolePermissionMapper.insertList(rolePermissionList);
        }

        //清空认证和授权信息，使其重新加载
        authenticationRealm.clearCache();
        return count == 1;
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

    /**
     * 封装角色和权限的关系并返回
     * @param roleId
     * @param permissionIds
     * @return
     */
    private List<RolePermission> getRolePermissionList(Long roleId, Long[] permissionIds){
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
        RolePermission rolePermission = null;
        if(permissionIds == null){
            return new ArrayList<RolePermission>();
        }
        for (Long permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermission.setCreateTime(new Date());
            rolePermission.setModifyTime(rolePermission.getCreateTime());
            rolePermissionList.add(rolePermission);
        }
        return rolePermissionList;
    }
}
