package com.jk.model;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Table(name = "t_article_types")
public class ArticleTypes extends BaseEntity{
    
    /**
     * 标题
     */
    private String name;
    /**
     * 作者
     */
    private String description;
    /**
     * 内容
     */
    private Integer order_number;

    /**
     * 关键字
     */
    private Integer status;
    
    /**
     * 类别
     */
    private Long parent_id;
    /**
     * parent_id
     *
     * @return the parent_id
     */
    
    public Long getParent_id() {
        return parent_id;
    }
    /**
     * parent_id
     *
     * @param parent_id the parent_id to set
     */
    
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
    /**
     * name
     *
     * @return the name
     */
    
    public String getName() {
        return name;
    }
    /**
     * name
     *
     * @param name the name to set
     */
    
    public void setName(String name) {
        this.name = name;
    }
    /**
     * description
     *
     * @return the description
     */
    
    public String getDescription() {
        return description;
    }
    /**
     * description
     *
     * @param description the description to set
     */
    
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * order_number
     *
     * @return the order_number
     */
    
    public Integer getOrder_number() {
        return order_number;
    }
    /**
     * order_number
     *
     * @param order_number the order_number to set
     */
    
    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
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
}
