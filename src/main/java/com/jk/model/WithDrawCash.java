package com.jk.model;

import java.math.BigDecimal;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Table(name = "t_withdrawcash")
public class WithDrawCash extends BaseEntity{
    
    /**
     * 项目ID
     */
    private Long project_id;
    
    /**
     * 项目名称
     */
    private String project_name;
    
    /**
     * 用户ID
     */
    private Long user_id;
    
    /**
     * 用户名称
     */
    private String user_name;
    
    /**
     * 提现金额
     */
    private BigDecimal withdraw_amount;

    /**
     * 状态 1:申请提现 2:正在提现 3:提现成功 4:提现拒绝 5:提现取消
     */
    private Integer status;

    /**
     * 拒绝原因
     */
    private String refuse_reason;
    
    /**
     * project_id
     *
     * @return the project_id
     */
    
    public Long getProject_id() {
        return project_id;
    }

    /**
     * project_id
     *
     * @param project_id the project_id to set
     */
    
    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    /**
     * project_name
     *
     * @return the project_name
     */
    
    public String getProject_name() {
        return project_name;
    }

    /**
     * project_name
     *
     * @param project_name the project_name to set
     */
    
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    /**
     * user_id
     *
     * @return the user_id
     */
    
    public Long getUser_id() {
        return user_id;
    }

    /**
     * user_id
     *
     * @param user_id the user_id to set
     */
    
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * user_name
     *
     * @return the user_name
     */
    
    public String getUser_name() {
        return user_name;
    }

    /**
     * user_name
     *
     * @param user_name the user_name to set
     */
    
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * withdraw_amount
     *
     * @return the withdraw_amount
     */
    
    public BigDecimal getWithdraw_amount() {
        return withdraw_amount;
    }

    /**
     * withdraw_amount
     *
     * @param withdraw_amount the withdraw_amount to set
     */
    
    public void setWithdraw_amount(BigDecimal withdraw_amount) {
        this.withdraw_amount = withdraw_amount;
    }

    /**
     * status
     *
     * @return the status
     */
    
    public Integer getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param status the status to set
     */
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * refuse_reason
     *
     * @return the refuse_reason
     */
    
    public String getRefuse_reason() {
        return refuse_reason;
    }

    /**
     * refuse_reason
     *
     * @param refuse_reason the refuse_reason to set
     */
    
    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }
    
    
}
