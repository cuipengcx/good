package com.jk.model;

import javax.persistence.Table;

/**
 * 权限
 * Created by cuiP on 2017/2/8.
 */
@Table(name = "t_permission")
public class Permission extends BaseEntity{
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型：0,1,2(目录,菜单,按钮)
     */
    private String type;
    /**
     * 访问url地址
     */
    private String url;
    /**
     * 权限代码,多个用逗号分隔
     * menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String perms;
    /**
     * 父节点id,一级节点为0
     */
    private Long parentId;
    /**
     * 父节点名称
     */
    private String parentName;
    /**
     * 父节点id列表串，用/分割
     */
    private String parentIds;
    /**
     * 排序号
     */
    private Long sort;
    /**
     * 是否禁用  true禁用  false 启用
     */
    private Boolean isLock;

    /**
     * 菜单图标
     */
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", sort=" + sort +
                ", isLock=" + isLock +
                ", icon='" + icon + '\'' +
                '}';
    }
}
