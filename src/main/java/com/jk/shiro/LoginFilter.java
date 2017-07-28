package com.jk.shiro;

import com.jk.common.ExecStatus;
import com.jk.util.EhCacheUtils;
import com.jk.util.WebUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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

//        boolean isLogin = isLoginRequest(request, response);


        String username = request.getParameter("username");
        Deque<Serializable> deque = (Deque<Serializable>) EhCacheUtils.get("shiro-kickout-session", username);

        if(deque != null) {
            //Ajax请求
            if(WebUtil.isAjaxRequest(request)) {
                Map<String, Object> resultMap = new HashMap<String, Object>();

                resultMap.put("code", ExecStatus.KICK_OUT_SESSION.getCode());
                resultMap.put("msg", "");

                WebUtil.writeJson(response, resultMap, HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }
}
