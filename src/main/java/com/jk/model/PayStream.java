package com.jk.model;

import java.math.BigDecimal;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Table(name = "t_pay_stream")
public class PayStream extends BaseEntity{
    
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
     * 支付金额：
     */
    private BigDecimal pay_amount;

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
     * pay_amount
     *
     * @return the pay_amount
     */
    
    public BigDecimal getPay_amount() {
        return pay_amount;
    }

    /**
     * pay_amount
     *
     * @param pay_amount the pay_amount to set
     */
    
    public void setPay_amount(BigDecimal pay_amount) {
        this.pay_amount = pay_amount;
    }
    
    
}
