package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.model.Log;
import com.jk.service.LogService;
import com.xiaoleilu.hutool.date.DateUtil;
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
        }if(startTime != null && endTime != null){
            criteria.andBetween("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)), DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        PageHelper.startPage(pageNum,pageSize);
        List<Log> logList = this.selectByExample(example);

        return new PageInfo<Log>(logList);
    }
}
