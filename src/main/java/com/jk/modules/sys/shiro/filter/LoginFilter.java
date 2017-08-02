package com.jk.modules.sys.shiro.filter;

import com.feilong.core.DatePattern;
import com.jk.common.Constant;
import com.jk.common.DataResult;
import com.jk.common.ExecStatus;
import com.jk.common.util.EhCacheUtils;
import com.jk.common.util.WebUtil;
import com.jk.modules.sys.vo.LoginSession;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Deque;

/**
 * @package: com.jk.shiro
 * @description: 登录过滤器
 * 1. 判断如果已经登录了，还访问login登录地址，做重定向到原来的地址
 * 2. 判断登录账号是否已经在其他地方登录，并进行踢出询问，并展示已经登录用户的信息包括IP和登录时间
 * @author: cuiP
 * @date: 2017/7/28 16:08
 * @version: V1.0.0
 */
public class LoginFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);

        //判断如果已经登录了，还访问login登录地址，做重定向到原来的地址
        Subject subject = getSubject(request, response);
        if(subject.isAuthenticated()){
            //重定向
            WebUtils.issueRedirect(request, response, "/admin/index");
            return false;
        }

        //如果登录账号已经在其他地方登录，进行登录询问,是否踢出
        String tc = request.getParameter("tc");
        String username = request.getParameter("username");
        Deque<LoginSession> deque = (Deque<LoginSession>) EhCacheUtils.get("shiro-kickout-session", username);

        if(deque != null && deque.size() >= Constant.MAX_SESSION){
            //当tc为空时，询问是否踢出，否则放行KickoutSessionControlFilter会进行拦截进行踢出操作
            if(WebUtil.isAjaxRequest(request) &&  StrUtil.isEmpty(tc)){
                LoginSession loginSession = deque.peekLast();
                DataResult result = new DataResult(ExecStatus.KICK_OUT_ASK.getCode(), StrUtil.format(ExecStatus.KICK_OUT_ASK.getMsg(), loginSession.getLoginIP(), DateUtil.format(loginSession.getLoginTime(), DatePattern.CHINESE_COMMON_DATE_AND_TIME)));
                WebUtil.writeJson(response, result, HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        return true;
    }
}
