package com.jk.model;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户认证表
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
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

    public Long getGoodUserId() {
        return goodUserId;
    }

    public void setGoodUserId(Long goodUserId) {
        this.goodUserId = goodUserId;
    }

    public String getAuthUserName() {
        return authUserName;
    }

    public void setAuthUserName(String authUserName) {
        this.authUserName = authUserName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardFace() {
        return idCardFace;
    }

    public void setIdCardFace(String idCardFace) {
        this.idCardFace = idCardFace;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAuthLegalUserName() {
        return authLegalUserName;
    }

    public void setAuthLegalUserName(String authLegalUserName) {
        this.authLegalUserName = authLegalUserName;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AuthenticationInfo{" +
                "goodUserId=" + goodUserId +
                ", authUserName='" + authUserName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", idCardFace='" + idCardFace + '\'' +
                ", idCardBack='" + idCardBack + '\'' +
                ", type=" + type +
                ", authLegalUserName='" + authLegalUserName + '\'' +
                ", businessLicence='" + businessLicence + '\'' +
                ", companyName='" + companyName + '\'' +
                ", registerTime=" + registerTime +
                ", registerAddress='" + registerAddress + '\'' +
                ", companyIntroduce='" + companyIntroduce + '\'' +
                ", checkTime=" + checkTime +
                ", checkStatus=" + checkStatus +
                ", adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
