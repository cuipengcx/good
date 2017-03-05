package com.jk.interceptor;

import com.jk.model.GoodUser;
import com.jk.util.SessionUtil;
import com.jk.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台--校验用户登陆
 * Created by JK on 2017/1/12.
 */
public class FrontInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        System.out.println(">>>MyInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
//        System.out.println(request.getSession().getMaxInactiveInterval());
        GoodUser goodUser = (GoodUser) request.getSession().getAttribute(SessionUtil.SESSION_FRONT_USER);
        if(null == goodUser){
            if(WebUtil.isAjaxRequest(request)){
                response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
                response.setHeader("Session-Status","{\"code\":408,\"msg\":'Session Timeout'}");
                response.setContentType("text/html;charset=utf-8");
            }
            response.sendRedirect(request.getContextPath() + "/front/login");
            return false;
        }
        return true; // 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>MyInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
//        System.out.println(">>>MyInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
