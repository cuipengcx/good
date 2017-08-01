package com.jk.service;

import com.jk.BaseTest;
import com.jk.modules.sys.model.Role;
import com.jk.modules.sys.service.RoleService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by JK on 2017/2/13.
 */
public class RoleServiceTest extends BaseTest {

    @Resource
    private RoleService roleService;

    /**
     * 根据角色名称查询角色对象信息
     */
    @Test
    public void testFindByName(){
        Role role = roleService.findByName("超级管理员");
        Assert.assertNotNull(role);
    }

    /**
     * 根据用户ID查询角色对象信息
     */
    @Test
    public void testFindByUserId(){
        Role role = roleService.findByUserId(83L);
        Assert.assertNotNull(role);
    }
}
