package com.jk.mapper;

import com.jk.model.Role;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/2/8.
 */
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);
}
