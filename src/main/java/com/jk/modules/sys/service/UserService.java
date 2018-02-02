package com.jk.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.jk.common.annotation.DataScope;
import com.jk.common.base.service.BaseService;
import com.jk.modules.sys.model.User;

/**
 * Created by JK on 2017/1/19.
 */
public interface UserService extends BaseService<User> {
    /**
     *
     * @param pageNum  当前页码
     * @param pageSize  每页显示条数
     * @param username 用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws Exception
     */
    PageInfo<User> findPage(DataScope dataScope, Integer pageNum , Integer pageSize , String username, String startTime, String endTime) throws Exception;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username) throws Exception;

    /**
     * 保存用户信息和关联用户和角色
     * @param user    用户对象
     * @param roleId  角色ID
     */
    Boolean saveUserAndUserRole(User user, Long roleId) throws Exception;

    /**
     * 更新用户信息和关联用户和角色
     * @param user      用户对象
     * @param oldRoleId 旧角色ID
     * @param roleId    角色ID
     * @return
     * @throws Exception
     */
    Boolean updateUserAndUserRole(User user, Long oldRoleId, Long roleId) throws Exception;
}
