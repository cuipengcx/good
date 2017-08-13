package com.jk.modules.sys.shiro.filter;

import com.feilong.core.util.CollectionsUtil;
import com.jk.common.util.EhCacheUtils;
import com.jk.common.util.ShiroUtils;
import com.jk.modules.sys.vo.LoginSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @packageName: com.jk.shiro.filter
 * @description: 自定义退出过滤器，实现清除缓存
 * @author: cuiP
 * @date: 2017/7/31 23:24
 * @version: V1.0.0
 */
@Slf4j
public class SystemLogoutFilter extends LogoutFilter{

    /**
     * 在这里执行退出系统前需要清空的数据
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = this.getSubject(request, response);
        String redirectUrl = this.getRedirectUrl(request, response, subject);

        //清除账号登陆限制缓存
        if(ShiroUtils.isLogin()){
            String username = ShiroUtils.getUserName();
            Serializable sessionId = ShiroUtils.getSession().getId();

            Deque<LoginSession> deque = (Deque<LoginSession>) EhCacheUtils.get("shiro-kickout-session", username);

            if(deque == null) {
                deque = new LinkedList<LoginSession>();
                EhCacheUtils.put("shiro-kickout-session", username, deque);
            }

            LoginSession loginSession = CollectionsUtil.find(deque, "sessionId", sessionId);
            deque.remove(loginSession);
            //更新缓存
            EhCacheUtils.put("shiro-kickout-session", username, deque);
        }


        try {
            ShiroUtils.logout();
        } catch (SessionException var6) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
        }

        this.issueRedirect(request, response, redirectUrl);
        //返回false表示不执行后续的过滤器，直接返回跳转到登录页面
        return false;
    }
}
