package com.jk.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jk.modules.sys.mapper.UserRoleMapper;
import com.jk.modules.sys.model.UserRole;
import com.jk.modules.sys.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

    @Transactional(readOnly = true)
    @Override
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId) {
        return this.selectOne(
                new EntityWrapper<UserRole>()
                        .eq("user_id", userId)
                        .eq("role_id", roleId)
        );
    }


    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色的关系
        this.deleteByMap(Collections.singletonMap("user_id", userId));

        List<UserRole> userRoleList = new ArrayList<>(roleIdList.size());

        //TODO 使用lambda表达式改造
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        for (Long aLong : roleIdList) {
            userRole.setRoleId(aLong);
            userRoleList.add(userRole);
        }
        //再保存
        this.insertBatch(userRoleList);
    }
}
