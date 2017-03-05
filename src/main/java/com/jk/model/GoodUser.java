package com.jk.model;

import javax.persistence.Table;

/**
 * @author 前台用户表
 * Created by JK on 2017/2/21.
 */
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAcceptEmail() {
        return isAcceptEmail;
    }

    public void setAcceptEmail(Boolean acceptEmail) {
        isAcceptEmail = acceptEmail;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getKeepNumber() {
        return keepNumber;
    }

    public void setKeepNumber(Integer keepNumber) {
        this.keepNumber = keepNumber;
    }

    public Integer getSupportNumber() {
        return supportNumber;
    }

    public void setSupportNumber(Integer supportNumber) {
        this.supportNumber = supportNumber;
    }

    public Long getGetPrice() {
        return getPrice;
    }

    public void setGetPrice(Long getPrice) {
        this.getPrice = getPrice;
    }

    public Long getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Long payPrice) {
        this.payPrice = payPrice;
    }

    public Boolean getPersonAuthed() {
        return isPersonAuthed;
    }

    public void setPersonAuthed(Boolean personAuthed) {
        isPersonAuthed = personAuthed;
    }

    public Boolean getTeamAuthed() {
        return isTeamAuthed;
    }

    public void setTeamAuthed(Boolean teamAuthed) {
        isTeamAuthed = teamAuthed;
    }

    public Boolean getEmailAuthed() {
        return isEmailAuthed;
    }

    public void setEmailAuthed(Boolean emailAuthed) {
        isEmailAuthed = emailAuthed;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    @Override
    public String toString() {
        return "GoodUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", isAcceptEmail=" + isAcceptEmail +
                ", startNumber=" + startNumber +
                ", keepNumber=" + keepNumber +
                ", supportNumber=" + supportNumber +
                ", getPrice=" + getPrice +
                ", payPrice=" + payPrice +
                ", isPersonAuthed=" + isPersonAuthed +
                ", isTeamAuthed=" + isTeamAuthed +
                ", isEmailAuthed=" + isEmailAuthed +
                ", isLock=" + isLock +
                ", isDel=" + isDel +
                '}';
    }
}
