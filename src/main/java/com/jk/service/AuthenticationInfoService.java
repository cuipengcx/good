package com.jk.service;

import com.github.pagehelper.PageInfo;
import com.jk.model.AuthenticationInfo;

/**
 * @author cuiP
 *         Created by JK on 2017/2/21.
 */
public interface AuthenticationInfoService extends BaseService<AuthenticationInfo> {
    /**
     * 分页查询待审核权益列表
     * @param pageNum    当前页码
     * @param pageSize   每页条数
     * @param type       认证类型
     * @param checkStatus 审核状态
     * @param startTime   申请开始时间
     * @param endTime     申请结束时间
     * @return
     * @throws Exception
     */
    PageInfo<AuthenticationInfo> findPage(Integer pageNum ,Integer pageSize,
                                          Integer type, String username, Integer checkStatus,
                                          String startTime, String endTime) throws Exception;
}
