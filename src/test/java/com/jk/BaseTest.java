package com.jk;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by JK on 2017/1/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTest {
    protected final transient Log log = LogFactory.get(this.getClass());
}
