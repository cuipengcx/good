package com.jk.modules.log.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jk.modules.log.model.Log;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
public interface LogService extends IService<Log> {

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    Page<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime);
}
