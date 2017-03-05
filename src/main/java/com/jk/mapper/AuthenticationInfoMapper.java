package com.jk.mapper;

import com.jk.model.AuthenticationInfo;
import com.jk.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
public interface AuthenticationInfoMapper extends MyMapper<AuthenticationInfo>{

    /**
     * 权益审核列表
     * @param type       认证类型
     * @param checkStatus 审核状态
     * @param startTime   申请开始时间
     * @param endTime     申请结束时间
     * @return
     * @throws Exception
     */
    List<AuthenticationInfo> findList(@Param("type") Integer type,
                                      @Param("username") String username,
                                      @Param("checkStatus") Integer checkStatus,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime) throws Exception;
}
