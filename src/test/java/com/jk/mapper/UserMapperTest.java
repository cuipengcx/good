package com.jk.mapper;

import com.jk.RootApplicationTests;
import com.jk.modules.sys.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JK on 2017/1/22.
 */
public class UserMapperTest extends RootApplicationTests {

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
