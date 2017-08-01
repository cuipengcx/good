package com.jk.common.base.service;

import com.github.pagehelper.PageInfo;
import com.jk.common.base.model.BaseEntity;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/1/18.
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     *
     * @param record
     * @return
     */
    T findOne(T record);

    /**
     * 根据条件查询数据列表
     *
     * @param record
     * @return
     */
    List<T> findListByWhere(T record);
    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<T> findPageListByWhere(Integer pageNum, Integer pageSize, T record);

    /**
     * 新增数据，返回成功的条数
     *
     * @param record
     * @return
     */
    Integer save(T record);

    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    Integer saveSelective(T record);

    /**
     * 批量保存，返回保存的条数
     * @param list
     * @return
     */
    int saveList(List<T> list);

    /**
     * 修改数据，返回成功的条数
     *
     * @param record
     * @return
     */
    Integer update(T record);

    /**
     * 修改数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    Integer updateSelective(T record);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 根据主键ID批量删除
     * @param ids ids 如 “1,2,3” 这种形式的字符串，这个方法要求实体类中有且只有一个带有@Id注解的字段，否则会抛出异常。
     * @return
     */
    Integer deleteByIds(String ids);

    /**
     * 批量删除
     * @param clazz  类名
     * @param property 熟悉名
     * @param values  值的集合
     * @return
     */
    Integer deleteByCondition(Class<T> clazz, String property, List<Object> values);

    /**
     * 根据条件做删除
     *
     * @param record
     * @return
     */
    Integer deleteByWhere(T record);

    /**
     * 自定义查询
     * @param example
     * @return
     */
    List<T> selectByExample(Object example);
}
