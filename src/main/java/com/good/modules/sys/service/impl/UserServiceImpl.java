package com.good.modules.sys.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.good.common.annotation.DataFilter;
import com.good.common.annotation.DataScope;
import com.good.modules.sys.mapper.RoleMapper;
import com.good.modules.sys.mapper.UserMapper;
import com.good.modules.sys.model.Role;
import com.good.modules.sys.model.User;
import com.good.modules.sys.model.UserRole;
import com.good.modules.sys.service.UserRoleService;
import com.good.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 *
 * Created by JK on 2017/1/19.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleService userRoleService;

    @DataFilter(tableAlias = "u")
    @Transactional(readOnly=true)
    @Override
    public Page<User> findPage(DataScope dataScope, Integer pageNum , Integer pageSize , String username, String startTime, String endTime) throws Exception {

        Page<User> page = this.selectPage(
                new Page<User>(pageNum, pageSize),
                new EntityWrapper<User>()
                        .like(StringUtils.isNotBlank(username), "username", username)
                        .ge(StringUtils.isNotBlank(startTime), "create_time", startTime + "00:00:00")
                        .le(StringUtils.isNotBlank(endTime), "create_time", endTime + "23:59:59")
                        .orderBy("create_time", false)
        );

        for (User user : page.getRecords()) {
            Role role = roleMapper.findByUserId(user.getId());
            if (null != role){
                user.setRoleName(role.getName());
            }
        }
        return page;
    }

    @Transactional(readOnly=true)
    @Override
    public User findByUserName(String username) {
        return this.selectOne(
                new EntityWrapper<User>()
                        .eq(StringUtils.isNotBlank(username), "username", username)
        );
    }


    @Override
    public void saveUserAndUserRole(User user, Long roleId) throws Exception{
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        user.setIsLock(false);
        user.setIsDel(false);

        Role role = roleMapper.selectById(roleId);
        if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
            user.setIsAdmin(true);
        }else {
            user.setIsAdmin(false);
        }

        this.insert(user);

        //关联用户和角色信息
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getId());
        userRole.setCreateTime(user.getCreateTime());
        userRole.setModifyTime(user.getCreateTime());
        userRoleService.insert(userRole);
    }

    @CacheEvict(value = "goodAuthenticationCache", key = "#user.username")
    @Override
    public void updateUserAndUserRole(User user, Long oldRoleId, Long roleId) throws Exception {
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        if(!oldRoleId.equals(roleId)){
            Role role = roleMapper.selectById(roleId);
            if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
                user.setIsAdmin(true);
            }else {
                user.setIsAdmin(false);
            }
        }
        this.updateById(user);

        //更新用户与角色关系
        userRoleService.saveOrUpdate(user.getId(), Collections.singletonList(roleId));
    }
}
