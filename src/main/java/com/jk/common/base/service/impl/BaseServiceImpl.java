package com.jk.common.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.base.model.BaseEntity;
import com.jk.common.base.service.BaseService;
import com.jk.common.base.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> mapper;

	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public T findById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		return this.mapper.select(null);
	}

	/**
	 * 根据条件查询一条数据，如果有多条数据会抛出异常
	 * 
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public T findOne(T record) {
		return this.mapper.selectOne(record);
	}

	/**
	 * 根据条件查询数据列表
	 * 
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<T> findListByWhere(T record) {
		return this.mapper.select(record);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public PageInfo<T> findPageListByWhere(Integer pageNum, Integer pageSize, T record) {
		// 设置分页条件
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = this.findListByWhere(record);
		return new PageInfo<T>(list);
	}

	/**
	 * 新增数据，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer save(T record) {
		record.setCreateTime(new Date());
		record.setModifyTime(record.getCreateTime());
		return this.mapper.insert(record);
	}

	/**
	 * 新增数据，使用不为null的字段，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer saveSelective(T record) {
		record.setCreateTime(new Date());
		record.setModifyTime(record.getCreateTime());
		return this.mapper.insertSelective(record);
	}

	@Override
	public int saveList(List<T> list) {
		return this.mapper.insertList(list);
	}

	/**
	 * 修改数据，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer update(T record) {
		record.setModifyTime(new Date());
		return this.mapper.updateByPrimaryKey(record);
	}

	/**
	 * 修改数据，使用不为null的字段，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer updateSelective(T record) {
		record.setModifyTime(new Date());
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteById(Long id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer deleteByIds(String ids) {
		return mapper.deleteByIds(ids);
	}

	/**
	 * 批量删除
	 * 
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	@Override
	public Integer deleteByCondition(Class<T> clazz, String property,
			List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return this.mapper.deleteByExample(example);
	}

	/**
	 * 根据条件做删除
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Integer deleteByWhere(T record) {
		return this.mapper.delete(record);
	}
	
	/**
     * 自定义查询
     * @param example
     * @return
     */
	@Transactional(readOnly = true)
	@Override
	public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

}
