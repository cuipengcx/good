package com.jk.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.sys.mapper.PermissionMapper;
import com.jk.modules.sys.model.Permission;
import com.jk.modules.sys.service.PermissionService;
import com.jk.modules.sys.service.RolePermissionService;
import com.jk.modules.sys.vo.TreeNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created by cuiP on 2017/2/8.
 */
//@CacheConfig(cacheNames = "permission")
@Transactional(rollbackFor = Exception.class)
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionService rolePermissionService;

    @Transactional(readOnly=true)
    @Override
    public Page<Permission> findPage(Integer pageNum, Integer pageSize, String name) {
        return this.selectPage(
                new Page<>(pageNum, pageNum),
                new EntityWrapper<Permission>()
                        .like(StrUtil.isNotEmpty(name), "name", name)
        );
    }

    @Transactional(readOnly=true)
    @Override
    public List<Permission> findListPermissionByUserId(Long userId) {
        return permissionMapper.findListPermissionByUserId(userId);
    }

    @Cacheable(value = "menuListCache", key = "#userId")
    @Transactional(readOnly=true)
    @Override
    public List<Permission> findMenuListByUserId(Long userId) {
        return permissionMapper.findMenuListByUserId(userId);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Permission> findListByType(String type) {
        return this.selectByMap(Collections.singletonMap("type", type));
    }

    @Transactional(readOnly=true)
    @Override
    public List<TreeNode> findTreeList() {
        return permissionMapper.findTreeList();
    }

    @Override
    public void deletePermissionAndRolePermissionByPermissionId(Long permissionId) {
        //删除权限
        this.deleteById(permissionId);

        //删除该权限和角色的关联信息
        rolePermissionService.deleteByMap(Collections.singletonMap("permission_id", permissionId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Permission> findListByMenuName(String menuName) {
        return this.selectList(
                new EntityWrapper<Permission>()
                        .like(StrUtil.isNotEmpty(menuName), "name", menuName)
        );
    }
}
