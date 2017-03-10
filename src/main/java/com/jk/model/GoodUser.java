package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * @author 前台用户表
 * Created by JK on 2017/2/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_good_user")
public class GoodUser extends BaseEntity{

    /**
     * 个人/机构名
     */
    private String username;
    /**
     * 密码
     *
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否接受系统邮件
     */
    private Boolean isAcceptEmail;
    /**
     * 发起的项目数量
     */
    private Integer startNumber;
    /**
     * 收藏的项目数量
     */
    private Integer keepNumber;
    /**
     * 支持的项目数量
     */
    private Integer supportNumber;
    /**
     * 募集的金额  单位为：分，避免四舍五入换算精度问题
     */
    private Long getPrice;
    /**
     * 支持的金额  单位为：分，避免四舍五入换算精度问题
     */
    private Long payPrice;
    /**
     * 个人权益是否认证通过
     */
    private Boolean isPersonAuthed;
    /**
     * 机构权益是否认证通过
     */
    private Boolean isTeamAuthed;
    /**
     * 邮箱是否认证通过 默认false
     */
    private Boolean isEmailAuthed;
    /**
     * 是否禁用  true禁用  false 启用
     */
    private Boolean isLock;
    /**
     * 是否删除 true 删除 false 未删除
     */
    private Boolean isDel;
}
