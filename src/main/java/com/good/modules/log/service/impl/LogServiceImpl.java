package com.good.modules.log.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.good.modules.log.mapper.LogMapper;
import com.good.modules.log.model.Log;
import com.good.modules.log.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService{

    @Transactional(readOnly = true)
    @Override
    public Page<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime) {
        return this.selectPage(
                new Page<>(pageNum, pageSize),
                new EntityWrapper<Log>()
                        .like(StringUtils.isNotBlank(username), "username", username)
                        .ge(StringUtils.isNotBlank(startTime), "create_time", startTime + "00:00:00")
                        .le(StringUtils.isNotBlank(endTime), "create_time", endTime + "23:59:59")
                        .orderBy("create_time", false)
        );
    }
}
