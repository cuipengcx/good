package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * 角色权限中间表
 * @author cuiP
 * Created by JK on 2017/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_role_permission")
public class RolePermission extends BaseEntity{
    private Long roleId;
    private Long permissionId;
}
