package com.jk.modules.sys.mapper;

import com.jk.modules.sys.model.User;
import com.jk.common.base.mapper.BaseMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);
}
