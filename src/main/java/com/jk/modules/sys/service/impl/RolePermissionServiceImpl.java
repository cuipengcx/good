package com.jk.modules.sys.service.impl;

import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.modules.sys.model.RolePermission;
import com.jk.modules.sys.service.RolePermissionService;
import com.jk.modules.sys.shiro.AuthenticationRealm;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
@Transactional
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService{

    @Resource
    private AuthenticationRealm authenticationRealm;


    @Transactional(readOnly = true)
    @Override
    public List<Long> findListPermissionIdsByRoleId(Long roleId) throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        List<RolePermission> rolePermissionList = this.findListByWhere(rolePermission);
        List<Long> permissionIds = new ArrayList<Long>();
        for (RolePermission permission : rolePermissionList) {
            permissionIds.add(permission.getPermissionId());
        }
        return permissionIds;
    }

    @Transactional(readOnly = true)
    @Override
    public String findPermissionIdsByRoleId(Long roleId) throws Exception {
        List<Long> permissionIds = this.findListPermissionIdsByRoleId(roleId);
//        StringUtils.join(permissionIds, ",");
        return  StrUtil.join(",", permissionIds);
    }

    @CacheEvict(value = "menuListCache", allEntries = true)
    @Override
    public void saveOrUpdate(Long roleId, Long[] permissionIds) {
        //先删除当前角色所拥有的权限再重现插入
        RolePermission delRolePermission = new RolePermission();
        delRolePermission.setRoleId(roleId);
        super.deleteByWhere(delRolePermission);

        List<RolePermission> rolePermissionList = this.getRolePermissionList(roleId, permissionIds);
        if(rolePermissionList.size() > 0){
            super.saveList(rolePermissionList);
        }

        //清除所有用户授权缓存信息，使其重新加载
        authenticationRealm.clearCachedAuthorizationInfoAll();
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
            return Collections.emptyList();
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
