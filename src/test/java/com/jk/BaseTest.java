package com.jk;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Created by JK on 2017/1/17.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {
    protected final transient Log log = LogFactory.get(this.getClass());
}
