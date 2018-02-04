package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * Created by JK on 2017/1/17.
 */
@SpringBootApplication
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RootApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
