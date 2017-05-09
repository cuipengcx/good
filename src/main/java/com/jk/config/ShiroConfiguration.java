package com.jk.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jk.shiro.AuthenticationRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置
 * @author cuiP
 * Created by JK on 2017/2/9.
 */
@Configuration
public class ShiroConfiguration {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //未授权界面，注:注解方式验证权限不好使;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/403");

        //自定义登出过滤器，设置登出后跳转地址
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/admin/login");
        filters.put("myLogout", logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);

        //过虑器链定义，从上向下顺序执行，一般将/**放在最下边
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/admin/logout", "myLogout");
        //登录请求匿名访问
        filterChainDefinitionMap.put("/admin/login", "anon");

        //过滤链定义，从上向下顺序执行，一般将放在最为下边:这是一个坑呢，一不小心代码就不好使了;
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/admin/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * securityManager安全管理器
     * Shiro的核心安全接口,这个属性是必须的
     *
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义Realm
        securityManager.setRealm(getAuthenticationRealm(ehCacheManagerFactoryBean));
        //注入缓存管理器
        securityManager.setCacheManager(getCacheShiroManage(ehCacheManagerFactoryBean));
        //注入session管理器
        securityManager.setSessionManager(getSessionManage(ehCacheManagerFactoryBean));
        return securityManager;
    }

    /**
     * 自定义身份认证realm;
     *
     * @return
     */
    @Bean
    public AuthenticationRealm getAuthenticationRealm(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        AuthenticationRealm authenticationRealm = new AuthenticationRealm();
        //将凭证匹配器设置到realm中，realm按照凭证匹配器的要求进行散列
        authenticationRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        authenticationRealm.setCacheManager(getCacheShiroManage(ehCacheManagerFactoryBean));
        //开启授权信息缓存
        authenticationRealm.setAuthorizationCachingEnabled(true);
        //设置授权缓存对应ehcache中配置
        authenticationRealm.setAuthorizationCacheName("authorizationCache");
        return authenticationRealm;
    }

    /**
     * 凭证匹配器 指定密码匹配规则
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        credentialsMatcher.setHashIterations(1); //散列的次数，比如散列两次，相当于 md5(md5(""));
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * Shiro生命周期处理器
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getSessionManage(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //session的失效时长，单位毫秒
        sessionManager.setGlobalSessionTimeout(3600000);  //60分钟失效
        sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(getSessionIdCookie());
        sessionManager.setSessionDAO(getSessionDao());
        sessionManager.setCacheManager(getCacheShiroManage(ehCacheManagerFactoryBean));
        // -----可以添加session 创建、删除的监听器

        return sessionManager;
    }

    /**
     * 会话DAO
     * @return
     */
    @Bean(name = "sessionDao")
    public EnterpriseCacheSessionDAO getSessionDao(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(getSessionIdGenerator());
        return sessionDAO;
    }

    /**
     * 会话ID生成器
     * @return
     */
    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator getSessionIdGenerator(){
        JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    /**
     * 缓存管理器 使用Ehcache实现
     * @return
     */
    @Bean(name = "cacheShiroManager")
    public CacheManager getCacheShiroManage(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheManager;
    }

    /**
     * 会话Cookie模板
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        return cookie;
    }

    /**
     * 会话验证调度器
     * @return
     */
    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(3600000);//每隔60分钟验证清理失效的session
        return scheduler;
    }

    /**
     * 相当于调用SecurityUtils.setSecurityManager(securityManager)
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{getSecurityManager(ehCacheManagerFactoryBean)});
        return factoryBean;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean即可实现此功能
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getSecurityManager(ehCacheManagerFactoryBean));
        return advisor;
    }

//    @Bean(name = "rememberMeCookie")
//    public SimpleCookie getRememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(2592000);
//        return simpleCookie;
//    }
//
//    @Bean
//    public CookieRememberMeManager getRememberManager(){
//        CookieRememberMeManager meManager = new CookieRememberMeManager();
//        meManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        meManager.setCookie(getRememberMeCookie());
//        return meManager;
//    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
