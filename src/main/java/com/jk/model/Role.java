package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 * Created by cuiP on 2017/2/8.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity{

    //超级管理员标识
    public static final String ROLE_TYPE = "ROEL_ADMIN";

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色标识
     */
    private String perms;

    /**
     * 备注
     */
    private String remark;
}
