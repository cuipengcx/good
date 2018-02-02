package com.jk.modules.sys.model;

import com.jk.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @package: com.jk.modules.sys.model
 * @description: 组织机构
 * @author: cuiP
 * @date: 2018/1/30 14:52
 * @version: V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dept extends BaseEntity{

    private static final long serialVersionUID = 7898278922679296948L;

    /**
     * 简称
     */
    private String simpleName;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 是否是父节点 true 是  false 否
     */
    private Boolean isParent;

    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;

    /**
     * 排序号
     */
    private Long sort;

    /**
     * 备注
     */
    private String remark;
}
