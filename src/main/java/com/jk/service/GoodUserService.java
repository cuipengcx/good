package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.GoodUser;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
public interface GoodUserService extends BaseService<GoodUser>{

    /**
     *
     * @param pageNum   当前页码
     * @param pageSize  每页显示条数
     * @param username  用户名
     * @param startTime 注册开始时间
     * @param endTime   注册结束时间
     * @param isDel     是否删除 true是  false 否
     * @return
     * @throws Exception
     */
    PageInfo<GoodUser> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime, Boolean isDel) throws Exception;

    /**
     * 根据条件查询用户信息，返回一个用户对象
     * @param id         用户ID
     * @param username   用户名
     * @param email      邮箱
     * @param mobilePhone 手机号
     * @param isLock      是否锁定
     * @param isDel       是否删除
     * @param isEmailAuthed  是否认证邮箱
     * @param isTeamAuthed   是否机构认证
     * @param isPersonAuthed 是否个人认证
     * @return
     */
    GoodUser findUser(Long id, String username, String email, String mobilePhone, Boolean isLock, Boolean isDel, Boolean isEmailAuthed, Boolean isTeamAuthed, Boolean isPersonAuthed) throws Exception;

    /**
     * 根据条件查询用户列表
     * @param id         用户ID
     * @param username   用户名
     * @param email      邮箱
     * @param mobilePhone 手机号
     * @param isLock      是否锁定
     * @param isDel       是否删除
     * @param isEmailAuthed  是否认证邮箱
     * @param isTeamAuthed   是否机构认证
     * @param isPersonAuthed 是否个人认证
     * @return
     * @throws Exception
     */
    List<GoodUser> findList(Long id, String username, String email, String mobilePhone, Boolean isLock, Boolean isDel, Boolean isEmailAuthed, Boolean isTeamAuthed, Boolean isPersonAuthed) throws Exception;;

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     * @throws Exception
     */
    GoodUser findUserByEmail(String email) throws Exception;
}
