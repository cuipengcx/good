package com.jk.mapper;

import com.jk.model.User;
import com.jk.util.MyMapper;

/**
 * Created by JK on 2017/1/19.
 */
public interface UserMapper extends MyMapper<User>{
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
