package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.BaseTest;
import com.jk.modules.sys.model.Permission;
import com.jk.modules.sys.service.PermissionService;
import com.jk.modules.sys.vo.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JK on 2017/2/13.
 */
public class PermissionServiceTest extends BaseTest {

    @Resource
    private PermissionService permissionService;

    /**
     * 分页查询权限列表
     */
    @Test
    public void testFindPage(){
        PageInfo<Permission> pageInfo = permissionService.findPage(1, 10, "系统管理");
        log.info("总条数:"+pageInfo.getList().size());
        Assert.assertTrue(pageInfo.getList().size() > 0);
    }

    /**
     * 根据用户ID查询该用户所拥有的权限
     */
    @Test
    public void testFindByUserId(){
        List<Permission> permission = permissionService.findListPermissionByUserId(83L);
        Assert.assertTrue(permission.size() > 0);
    }

    /**
     * 根据资源类型查询权限列表
     */
    @Test
    public void testFindListByType(){
        List<Permission> permissionList = permissionService.findListByType("0");
        Assert.assertTrue(permissionList.size() > 0);
    }

    /**
     * 测试树
     */
    @Test
    public void testFindTreeList(){
        List<TreeNode> treeList = permissionService.findTreeList();
        System.out.println(treeList.size());
        for (TreeNode treeNode : treeList) {
            System.out.println(treeNode.getId());
            System.out.println(treeNode.getParentId());
            System.out.println(treeNode.getName());
            System.out.println(treeNode.getOpen());
            System.out.println("===========================");
        }
    }

}
