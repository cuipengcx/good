package com.jk.modules.cms.model;

import com.jk.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 内容分类
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContentCat extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类ID,ID=0时，代表的是一级的类目
     */
    private Long parentId;

    /**
     * 父分类的名称
     */
    private String parentName;

    /**
     * 排列序号
     * 表示同级分类的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sort;

    /**
     * 该分类是否为父类目，1为true，0为false
     */
    private Boolean isParent;
}
