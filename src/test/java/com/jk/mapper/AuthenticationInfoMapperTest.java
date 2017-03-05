package com.jk.mapper;

import com.jk.BaseTest;
import com.jk.model.AuthenticationInfo;
import com.xiaoleilu.hutool.date.DateUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by JK on 2017/2/22.
 */
public class AuthenticationInfoMapperTest extends BaseTest {

    @Resource
    private AuthenticationInfoMapper authenticationInfoMapper;

    /**
     * 权益审核列表
     * @throws Exception
     */
    @Test
    public void testFindPage() throws Exception {
        Date startTime = DateUtil.beginOfDay(DateUtil.parseDate("2017-2-22"));
        Date endTime =  DateUtil.endOfDay(DateUtil.parseDate("2017-2-22"));
        System.out.println(startTime.toString());
        System.out.println(endTime.toString());
        List<AuthenticationInfo> authenticationInfos = authenticationInfoMapper.findList(1, "dd", 1, startTime, endTime);
        System.out.println(authenticationInfos.size());
    }

    @Test
    public void testDate(){
        Date date = DateUtil.parseDate("2017-2-22");
        System.out.println(date.toString());
    }
}
