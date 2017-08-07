package com.jk.modules.sys.shiro;

import com.jk.common.util.EhCacheUtils;
import com.jk.modules.sys.mapper.PermissionMapper;
import com.jk.modules.sys.mapper.RoleMapper;
import com.jk.modules.sys.mapper.UserMapper;
import com.jk.modules.sys.model.Permission;
import com.jk.modules.sys.model.Role;
import com.jk.modules.sys.model.User;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 身份校验核心类，包括认证和授权
 * @author cuiP
 * Created by JK on 2017/2/9.
 */
public class AuthenticationRealm extends AuthorizingRealm {

    protected final static Log log = LogFactory.get(AuthenticationRealm.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleMapper roleMapper;


    /**
     * 认证  校验用户身份是否合法
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("##################执行Shiro权限认证##################");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User record = null;
        try {
            record = userMapper.findByUserName(token.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(record == null){
            throw new UnknownAccountException();// 没找到帐号
        }
        if (Boolean.TRUE.equals(record.getIsLock())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        Role role = roleMapper.findByUserId(record.getId());

        //设置角色名称
        record.setRoleName(role.getName());

        //将此用户存放到登录认证info中，无需自己做密码对比，Shiro使用CredentialsMatcher会为我们进行密码对比校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                record, record.getPassword(), getName());
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                record, // 用户名
//                record.getPassword(), // 密码
//                ByteSource.Util.bytes(record.getSalt()),// salt=username+salt
//                getName() // realm name
//        );
        return authenticationInfo;
    }

    /**
     * 授权  查询当前用户具有哪些资源访问权限并进行资源授权
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。（需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=========执行授权===========");
        //获取主身份信息
        User user = (User) principalCollection.getPrimaryPrincipal();

        List<Permission> permissionList = permissionMapper.findListPermissionByUserId(user.getId());

        //单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        if(permissionList!=null){
            for(Permission permission:permissionList){
                //将数据库中的权限标签 符放入集合
                permissions.add(permission.getPerms());
            }
        }

        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }


    /**
     * 清除当前用户认证缓存信息
     */
  	public void clearCachedAuthenticationInfo() {
  		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
  		super.clearCachedAuthenticationInfo(principals);
  	}

    /**
     * 清除当前用户授权缓存信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除当前用户认证和授权缓存信息
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

    /**
     * 清除所有用户授权缓存信息
     */
    public void clearCachedAuthorizationInfoAll(){
        String cacheName = super.getAuthorizationCacheName();
        EhCacheUtils.removeAll(cacheName);
    }
}
