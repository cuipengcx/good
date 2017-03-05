package com.jk.model;

import java.util.Date;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Table(name = "t_article")
public class Article extends BaseEntity{
    
    /**
     * 类别
     */
    private Integer type_id;
    /**
     * 标题
     */
    private String title;
    /**
     * 标题2
     */
    private String title2;
    /**
     * 作者
     */
    private String author;
    /**
     * 内容
     */
    private String content;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 阅读次数
     */
    private Integer read_count;
    /**
     * 图片路径
     */
    private String image_filename;
    /**
     * 推荐效果
     */
    private Integer location_pc;
    /**
     * 开始显示时间
     */
    private Date start_show_time;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 是否显示
     */
    private Integer is_use;
    /**
     * 编号
     */
    private Integer order_number;
    
    /**
     * 添加时间
     */
    private Date time;
    
    /**
     * 类别
     */
    private String name;
    
    /**
     * 描述
     */
    private String description;
    
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
     * time
     *
     * @return the time
     */
    
    public Date getTime() {
        return time;
    }
    /**
     * time
     *
     * @param time the time to set
     */
    
    public void setTime(Date time) {
        this.time = time;
    }
    /**
     * type_id
     *
     * @return the type_id
     */
    
    public Integer getType_id() {
        return type_id;
    }
    /**
     * type_id
     *
     * @param type_id the type_id to set
     */
    
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }
    /**
     * title
     *
     * @return the title
     */
    
    public String getTitle() {
        return title;
    }
    /**
     * title
     *
     * @param title the title to set
     */
    
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * author
     *
     * @return the author
     */
    
    public String getAuthor() {
        return author;
    }
    /**
     * author
     *
     * @param author the author to set
     */
    
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * content
     *
     * @return the content
     */
    
    public String getContent() {
        return content;
    }
    /**
     * content
     *
     * @param content the content to set
     */
    
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * keywords
     *
     * @return the keywords
     */
    
    public String getKeywords() {
        return keywords;
    }
    /**
     * keywords
     *
     * @param keywords the keywords to set
     */
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    /**
     * read_count
     *
     * @return the read_count
     */
    
    public Integer getRead_count() {
        return read_count;
    }
    /**
     * read_count
     *
     * @param read_count the read_count to set
     */
    
    public void setRead_count(Integer read_count) {
        this.read_count = read_count;
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
     * location_pc
     *
     * @return the location_pc
     */
    
    public Integer getLocation_pc() {
        return location_pc;
    }
    /**
     * location_pc
     *
     * @param location_pc the location_pc to set
     */
    
    public void setLocation_pc(Integer location_pc) {
        this.location_pc = location_pc;
    }
    /**
     * start_show_time
     *
     * @return the start_show_time
     */
    
    public Date getStart_show_time() {
        return start_show_time;
    }
    /**
     * start_show_time
     *
     * @param start_show_time the start_show_time to set
     */
    
    public void setStart_show_time(Date start_show_time) {
        this.start_show_time = start_show_time;
    }
    /**
     * summary
     *
     * @return the summary
     */
    
    public String getSummary() {
        return summary;
    }
    /**
     * summary
     *
     * @param summary the summary to set
     */
    
    public void setSummary(String summary) {
        this.summary = summary;
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
     * title2
     *
     * @return the title2
     */
    
    public String getTitle2() {
        return title2;
    }
    /**
     * title2
     *
     * @param title2 the title2 to set
     */
    
    public void setTitle2(String title2) {
        this.title2 = title2;
    }
}
