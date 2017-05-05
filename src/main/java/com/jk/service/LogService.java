package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.Log;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
public interface LogService extends BaseService<Log> {

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<Log> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime);
}
