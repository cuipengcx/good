package com.jk.modules.sys.shiro.filter;

import com.feilong.core.util.CollectionsUtil;
import com.jk.common.DataResult;
import com.jk.common.ExecStatus;
import com.jk.common.util.ShiroUtils;
import com.jk.common.util.WebUtil;
import com.jk.modules.sys.vo.LoginSession;
import com.xiaoleilu.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @className KickoutSessionControlFilter
 * @packageName: com.jk.shiro
 * @description 并发登录人数控制，限制一个账号只能一处登录，踢出前者
 * 思路：
 * 1.读取当前登录用户名，获取在缓存中的sessionId队列
 * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则将之前的sessionId中的session域中存入kickout：true，并更新队列缓存
 * 3.判断当前登录的session域中的kickout如果为true，想将其做退出登录处理，然后再重定向到踢出登录提示页面
 * @author cuiP 
 * @date 2017/7/24 22:59
 * @version V1.0.0
 */
@Slf4j
public class KickoutSessionControlFilter extends AccessControlFilter {

    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<String, Deque<LoginSession>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    //设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    /**
     * 是否允许访问，返回true表示允许
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);

        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        String username = ShiroUtils.getUserName();
        Serializable sessionId = session.getId();

        //读取缓存   没有就存入
        Deque<LoginSession> deque = cache.get(username);
        if(deque == null) {
            deque = new LinkedList<LoginSession>();
            cache.put(username, deque);
        }


        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(CollectionsUtil.find(deque, "sessionId", sessionId) == null && session.getAttribute("kickout") == null) {
            //将sessionId存入队列
            LoginSession loginSession = new LoginSession();
            loginSession.setSessionId(sessionId);
            loginSession.setLoginTime(new Date());
            loginSession.setLoginIP(HttpUtil.getClientIP(request));
            deque.push(loginSession);
            //将用户的sessionId队列缓存
            cache.put(username, deque);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            //要踢出的登录用户信息
            LoginSession kickoutLoginSession = null;
            if(kickoutAfter) { //如果踢出后者
                kickoutLoginSession = deque.removeFirst();
            } else { //否则踢出前者
                kickoutLoginSession = deque.removeLast();
            }

            //踢出后再更新下缓存队列
            cache.put(username, deque);

            try {
                //获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutLoginSession.getSessionId()));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
                log.error("踢出用户:{},失败,失败信息:{}", username, e);
            }
        }

        //如果是一般请求,直接执行退出并重定向到踢出后的地址,然后提示被踢出了
        //如果是Ajax请求,前端给出选择框，选择重新登录后执行退出并跳转到登录页面
        if (Boolean.valueOf(true).equals(session.getAttribute("kickout"))) {
            //Ajax请求
            if(WebUtil.isAjaxRequest(request)){
                DataResult result = new DataResult(ExecStatus.KICK_OUT_TIPS.getCode(), ExecStatus.KICK_OUT_TIPS.getMsg());

                WebUtil.writeJson(response, result, HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }else {
                //退出登录
                ShiroUtils.logout();
                //保存上次请求的地址
                saveRequest(request);
                //重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
                return false;
            }
        }

        return true;
    }
}
