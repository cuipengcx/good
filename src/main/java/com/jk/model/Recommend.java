package com.jk.model;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Table(name = "t_recommend")
public class Recommend extends BaseEntity{

    /**
     * 推荐位类别：
     * 0：首页-项目推荐位，1：子页-项目推荐位，2：榜单-企业排行
     */
    private Integer type;
    
    /**
     * 推荐内容id
     */
    private Long recommend_id;
    
    /**
     * 图片路径
     */
    private String image_filename;

    /**
     * 企业LOGO
     */
    private String company_logo;
    
    /**
     * 企业名称
     */
    private String company_name;
    
    /**
     * 企业支持项目数
     */
    private String company_p_number;
    
    /**
     * 企业支持金额
     */
    private String company_p_amount;
    
    /**
     * 排序
     */
    private Integer order_number;
    
    /**
     * 是否使用
     */
    private Integer is_use;

    /**
     * type
     *
     * @return the type
     */
    
    public Integer getType() {
        return type;
    }

    /**
     * type
     *
     * @param type the type to set
     */
    
    public void setType(Integer type) {
        this.type = type;
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
     * recommend_id
     *
     * @return the recommend_id
     */
    
    public Long getRecommend_id() {
        return recommend_id;
    }

    /**
     * recommend_id
     *
     * @param recommend_id the recommend_id to set
     */
    
    public void setRecommend_id(Long recommend_id) {
        this.recommend_id = recommend_id;
    }
    
    /**
     * is_use
     *
     * @return the is_use
     */
    public Integer getIs_use() {
        return is_use;
    }

    /**
     * is_use
     *
     * @param is_use the is_use to set
     */
    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }
    
    
    /**
     * image_filename
     *
     * @return the image_filename
     */
    
    public String getImage_filename() {
        return image_filename;
    }

    /**
     * image_filename
     *
     * @param image_filename the image_filename to set
     */
    
    public void setImage_filename(String image_filename) {
        this.image_filename = image_filename;
    }

    /**
     * company_logo
     *
     * @return the company_logo
     */
    
    public String getCompany_logo() {
        return company_logo;
    }

    /**
     * company_logo
     *
     * @param company_logo the company_logo to set
     */
    
    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    /**
     * company_name
     *
     * @return the company_name
     */
    
    public String getCompany_name() {
        return company_name;
    }

    /**
     * company_name
     *
     * @param company_name the company_name to set
     */
    
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * company_p_number
     *
     * @return the company_p_number
     */
    
    public String getCompany_p_number() {
        return company_p_number;
    }

    /**
     * company_p_number
     *
     * @param company_p_number the company_p_number to set
     */
    
    public void setCompany_p_number(String company_p_number) {
        this.company_p_number = company_p_number;
    }

    /**
     * company_p_amount
     *
     * @return the company_p_amount
     */
    
    public String getCompany_p_amount() {
        return company_p_amount;
    }

    /**
     * company_p_amount
     *
     * @param company_p_amount the company_p_amount to set
     */
    
    public void setCompany_p_amount(String company_p_amount) {
        this.company_p_amount = company_p_amount;
    }

}
