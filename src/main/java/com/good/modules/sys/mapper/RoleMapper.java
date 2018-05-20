package com.good.modules.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.good.modules.sys.model.Role;


/**
 * Created by JK on 2017/2/8.
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户ID查询角色对象信息
     * @param userId
     * @return
     */
    Role findByUserId(Long userId);
}
