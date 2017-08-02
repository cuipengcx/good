package com.jk.config.filter;

import com.jk.common.security.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter配置
 * @author cuiP
 */
@Configuration
public class FilterConfig {

    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        //filter
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        //如果filter-name 和Spring中配置的bean的name相同不需要配置这个
//        proxy.setTargetBeanName("shiroFilter");
        //该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理,保留Filter原有的init，destroy方法的调用
        proxy.setTargetFilterLifecycle(true);
        filterRegistrationBean.setFilter(proxy);

        //filter-mapping
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/admin/*");//拦截路径，可以添加多个
        // 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        //filter-name
        filterRegistrationBean.setName("shiroFilter");
        filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
        return filterRegistrationBean;
    }

    /**
     * 注入Xss过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
}
