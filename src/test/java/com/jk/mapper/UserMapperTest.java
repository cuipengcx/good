package com.jk.mapper;

import com.jk.BaseTest;
import com.jk.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JK on 2017/1/22.
 */
public class UserMapperTest extends BaseTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindById(){
//        User user = userMapper.findById(73L);
//        if(user != null){
//            System.out.println(user.getUsername());
//        }
//        Assert.assertNotNull(user);
    }
}
