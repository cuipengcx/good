package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户认证表
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_authentication_info")
public class AuthenticationInfo extends BaseEntity{
    /**
     * 用户ID
     */
    private Long goodUserId;
    /**
     * 个人姓名
     */
    private String authUserName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 身份证正面
     */
    private String idCardFace;
    /**
     * 身份证背面
     */
    private String idCardBack;

    /**
     * 用户认证类型 1 个人 2 机构
     */
    private Integer type;

    /**
     * 法人代表姓名
     */
    private String authLegalUserName;
    /**
     * 三证合一的证件
     */
    private String businessLicence;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 注册登记地址
     */
    private String registerAddress;
    /**
     * 公司简介
     */
    private String companyIntroduce;
    /**
     * 审核时间
     */
    private Date checkTime;
    /**
     * 审核状态 0.待审核 1.通过 2.拒绝
     */
    private Integer checkStatus;
    /**
     * 审核人ID
     */
    private Long adminId;
    /**
     * 审核人姓名
     */
    private String adminName;
    /**
     * 审核理由
     */
    private String remark;
}
