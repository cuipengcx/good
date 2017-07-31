package com.jk.shiro;

import com.jk.common.DataResult;
import com.jk.common.ExecStatus;
import com.jk.util.EhCacheUtils;
import com.jk.util.WebUtil;
import com.jk.vo.LoginSession;
import com.xiaoleilu.hutool.util.StrUtil;
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

        String tc = request.getParameter("tc");
        String username = request.getParameter("username");
        Deque<LoginSession> deque = (Deque<LoginSession>) EhCacheUtils.get("shiro-kickout-session", username);

        if(deque != null && StrUtil.isEmpty(tc)){
            if(WebUtil.isAjaxRequest(request)){
                DataResult result = new DataResult(ExecStatus.KICK_OUT_SESSION.getCode(), ExecStatus.KICK_OUT_SESSION.getMsg());

                WebUtil.writeJson(response, result, HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }
}
