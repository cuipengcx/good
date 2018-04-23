package com.jk.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.sys.mapper.RolePermissionMapper;
import com.jk.modules.sys.model.RolePermission;
import com.jk.modules.sys.service.RolePermissionService;
import com.jk.modules.sys.shiro.AuthenticationRealm;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService{

    @Resource
    private AuthenticationRealm authenticationRealm;


    @Transactional(readOnly = true)
    @Override
    public List<Long> findListPermissionIdsByRoleId(Long roleId) throws Exception {

        List<RolePermission> rolePermissionList = this.selectList(
                new EntityWrapper<RolePermission>()
                        .eq("role_id", roleId)
        );

        //TODO Lambda改造
        List<Long> permissionIds = new ArrayList<Long>(rolePermissionList.size());
        for (RolePermission permission : rolePermissionList) {
            permissionIds.add(permission.getPermissionId());
        }
        return permissionIds;
    }

    @Transactional(readOnly = true)
    @Override
    public String findPermissionIdsByRoleId(Long roleId) throws Exception {
        List<Long> permissionIds = this.findListPermissionIdsByRoleId(roleId);
        return StrUtil.join(",", permissionIds);
    }

    @CacheEvict(value = "menuListCache", allEntries = true)
    @Override
    public void saveOrUpdate(Long roleId, Long[] permissionIds) {
        //先删除当前角色所拥有的权限再重现插入
        this.deleteByMap(Collections.singletonMap("role_id", roleId));

        //TODO Lambda改造
        List<RolePermission> rolePermissionList = this.getRolePermissionList(roleId, permissionIds);
        this.insertBatch(rolePermissionList);

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
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>(permissionIds.length);
        RolePermission rolePermission = null;
        for (Long permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermission.setCreateTime(LocalDateTime.now());
            rolePermission.setModifyTime(rolePermission.getCreateTime());
            rolePermissionList.add(rolePermission);
        }
        return rolePermissionList;
    }
}
