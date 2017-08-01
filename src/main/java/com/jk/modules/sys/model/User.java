package com.jk.modules.sys.model;

import com.jk.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.Date;

/**
 * 用户
 * Created by cuiP on 2017/1/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 性别  true 男  false 女
     */
    private Boolean sex;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否禁用  true禁用  false 启用
     */
    private Boolean isLock;
    /**
     * 是否删除 true 删除 false 未删除
     */
    private Boolean isDel;

    /**
     * 是否是超级管理员
     */
    private Boolean isAdmin;
    /**
     * 最近一次登录时间
     */
    private Date loginTime;

    @Transient
    private String roleName;
}
