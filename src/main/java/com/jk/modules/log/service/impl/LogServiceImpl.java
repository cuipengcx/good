package com.jk.modules.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.common.base.service.impl.BaseServiceImpl;
import com.jk.modules.log.model.Log;
import com.jk.modules.log.service.LogService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Transactional
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{

    @Transactional(readOnly = true)
    @Override
    public PageInfo<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime) {
        Example example = new Example(Log.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(username)){
            criteria.andLike("username", "%"+username+"%");
        }if(StrUtil.isNotEmpty(startTime)){
            criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)));
        }if(StrUtil.isNotEmpty(endTime)){
            criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        //倒序
        example.orderBy("createTime").desc();

        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<Log> logList = this.selectByExample(example);

        return new PageInfo<Log>(logList);
    }
}
