package com.jk.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.annotation.DataFilter;
import com.jk.common.annotation.DataScope;
import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.modules.sys.mapper.RoleMapper;
import com.jk.modules.sys.mapper.UserMapper;
import com.jk.modules.sys.mapper.UserRoleMapper;
import com.jk.modules.sys.model.Role;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.model.UserRole;
import com.jk.modules.sys.service.UserService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @DataFilter(tableAlias = "u")
    @Transactional(readOnly=true)
    @Override
    public PageInfo<User> findPage(DataScope dataScope, Integer pageNum , Integer pageSize , String username, String startTime, String endTime) throws Exception {
//        /        Example example = new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        if(StringUtils.isNotEmpty(username)){
//            criteria.andLike("username", "%"+username+"%");
//        }if(startTime != null && endTime != null){
//            criteria.andBetween("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)), DateUtil.endOfDay(DateUtil.parse(endTime)));
//        }

        PageHelper.startPage(pageNum,pageSize);

        List<User> userList = userMapper.findListDataFilter(dataScope, username, startTime, endTime);


        //倒序
//        example.orderBy("createTime").desc();

//        PageHelper.startPage(pageNum,pageSize);
//        List<User> userList = this.selectByExample(example);

        for (User user : userList) {
            Role role = roleMapper.findByUserId(user.getId());
            if (null != role){
                user.setRoleName(role.getName());
            }
        }
        return new PageInfo<User>(userList);
    }

    @Transactional(readOnly=true)
    @Override
    public User findByUserName(String username) {
        User user = new User();
        user.setUsername(username);
        return this.findOne(user);
    }

    @Override
    public Boolean saveUserAndUserRole(User user, Long roleId) throws Exception{
        int count = 0;
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        user.setIsLock(false);
        user.setIsDel(false);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
            user.setIsAdmin(true);
        }else {
            user.setIsAdmin(false);
        }
        count = this.save(user);

        //关联用户和角色信息
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getId());
        userRole.setCreateTime(user.getCreateTime());
        userRole.setModifyTime(user.getCreateTime());
        count = userRoleMapper.insert(userRole);

        return count == 1;
    }

    @CacheEvict(value = "goodAuthenticationCache", key = "#user.username")
    @Override
    public Boolean updateUserAndUserRole(User user, Long oldRoleId, Long roleId) throws Exception {
        int count = 0;
        //加密
        user.setPassword(SecureUtil.md5().digestHex(user.getPassword()));
        if(!oldRoleId.equals(roleId)){
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if(Role.ROLE_TYPE.equalsIgnoreCase(role.getPerms())){
                user.setIsAdmin(true);
            }else {
                user.setIsAdmin(false);
            }
        }
        count = this.updateSelective(user);

        //更新用户角色信息
        if(!oldRoleId.equals(roleId)){
            UserRole userRole = new UserRole();
            userRole.setRoleId(oldRoleId);
            userRole.setUserId(user.getId());
            UserRole ur = userRoleMapper.selectOne(userRole);
            ur.setRoleId(roleId);
            ur.setModifyTime(user.getModifyTime());
            count = userRoleMapper.updateByPrimaryKeySelective(ur);
        }
        return count == 1;
    }
}
