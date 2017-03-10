package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * 用户角色中间表
 * @author cuiP
 * Created by JK on 2017/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_user_role")
public class UserRole extends BaseEntity{
    private Long userId;
    private Long roleId;
}
