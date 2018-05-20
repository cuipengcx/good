package com.good.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.good.modules.sys.model.UserRole;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/16.
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户ID和角色ID查询用户和角色绑定信息
     * @param userId
     * @param roleId
     * @return
     */
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * 保存用户与角色的关系
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
