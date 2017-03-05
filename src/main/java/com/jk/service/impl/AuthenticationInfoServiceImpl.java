package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.AuthenticationInfoMapper;
import com.jk.model.AuthenticationInfo;
import com.jk.service.AuthenticationInfoService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
@Transactional
@Service
public class AuthenticationInfoServiceImpl extends BaseServiceImpl<AuthenticationInfo> implements AuthenticationInfoService{

    @Resource
    private AuthenticationInfoMapper authenticationInfoMapper;

    @Transactional(readOnly = true)
    @Override
    public PageInfo<AuthenticationInfo> findPage(Integer pageNum ,Integer pageSize,
                                                 Integer type, String username, Integer checkStatus,
                                                 String startTime, String endTime) throws Exception {
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        Date staTime = null;
        Date enTime = null;
        if(StrUtil.isNotEmpty(startTime)){
            staTime = DateUtil.beginOfDay(DateUtil.parseDate(startTime));
        }
        if(StrUtil.isNotEmpty(endTime)){
            enTime = DateUtil.endOfDay(DateUtil.parseDate(endTime));
        }
        List<AuthenticationInfo> authenticationInfoList = authenticationInfoMapper.findList(type, username, checkStatus, staTime, enTime);
        return new PageInfo<AuthenticationInfo>(authenticationInfoList);
    }
}
