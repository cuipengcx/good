package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_sensitive_word")
public class SensitiveWord extends BaseEntity{
    
    /**
     * 版本号
     */
    private Long version;
    
    /**
     * 敏感词
     */
    private String word;
}
