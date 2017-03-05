package com.jk.mapper;

import com.jk.model.Permission;
import com.jk.util.MyMapper;
import com.jk.vo.TreeNode;

import java.util.List;

/**
 * Created by JK on 2017/2/8.
 */
public interface PermissionMapper extends MyMapper<Permission>{
    /**
     * 根据用户ID查询该用户所拥有的权限列表
     * @param UserId
     * @return
     */
    List<Permission> findListPermissionByUserId(Long UserId);

    /**
     * 根据用户ID查询用户菜单列表
     * @param UserId
     * @return
     */
    List<Permission> findMenuListByUserId(Long UserId);

    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
