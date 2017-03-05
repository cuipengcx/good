package com.jk.service;

import com.jk.model.Role;

/**
 *
 * Created by cuiP on 2017/2/8.
 */
public interface RoleService extends BaseService<Role>{
    /**
     * 根据角色名称查询角色对象信息
     * @param name
     * @return
     */
    Role findByName(String name);
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);
    /**
     * 保存角色信息并级联关联所拥有的权限
     * @param role  角色信息
     * @param permissionIds 权限集合
     * @return
     */
    Boolean saveRoleAndRolePermission(Role role, Long[] permissionIds);
    /**
     * 更新角色信息并级联关联所拥有的权限
     * @param role
     * @param permissionIds
     * @return
     */
    Boolean updateRoleAndRolePermission(Role role, Long[] permissionIds);
    /**
     * 根据角色ID删除角色并级联删除该角色和权限的关联信息
     * @param roleId
     * @return
     */
    Boolean deleteRoleAndRolePermissionByRoleId(Long roleId);
}
