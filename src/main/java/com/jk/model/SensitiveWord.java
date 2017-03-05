package com.jk.model;

import javax.persistence.Table;

/**
 * Created by JK on 2017/1/19.
 */
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

    /**
     * version
     *
     * @return the version
     */
    
    public Long getVersion() {
        return version;
    }

    /**
     * version
     *
     * @param version the version to set
     */
    
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * word
     *
     * @return the word
     */
    
    public String getWord() {
        return word;
    }

    /**
     * word
     *
     * @param word the word to set
     */
    
    public void setWord(String word) {
        this.word = word;
    }
    
}
