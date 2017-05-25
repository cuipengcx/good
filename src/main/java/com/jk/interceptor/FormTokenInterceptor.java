package com.jk.interceptor;

import com.jk.util.WebUtil;
import com.jk.util.security.token.FormToken;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

import static com.jk.common.Constant.*;

/**
 * 防止表单重复提交拦截器
 * @author cuiP
 * Created by JK on 2017/5/23.
 */
@Slf4j
public class FormTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {

                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    //跳转到页面前将token设置到session中
                    request.getSession(false).setAttribute(TOKEN_FORM, UUID.randomUUID().toString());
                }

                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        //不允许重复提交
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //请求参数不合法 ajax请求时前台提示，增强客户体验
                        response.setHeader(HEAD_TOKEN_FORM_KEY, HEAD_TOKEN_FORM_VALUE);
//                        response.setHeader(TOKEN_FORM_KEY, "{\"code\":400,\"msg\":'Repeat-Submit'}");
                        response.setContentType("text/html;charset=utf-8");
                        return false;
                    }

                    //移除session中保存的旧token
                    request.getSession(false).removeAttribute(TOKEN_FORM);

                    //判断提交表单的请求是否为Ajax请求,若是则生成refresh_token,以替换表单页面的formToken,解决Ajax提交后,验证不通过无法再次提交的问题
                    if(WebUtil.isAjaxRequest(request)){
                        String uuid = UUID.randomUUID().toString();
                        //往session重新set个值
                        request.getSession(false).setAttribute(TOKEN_FORM, uuid);

                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setHeader(HEAD_REFRESH_TOKEN_FORM, uuid);
                        response.setContentType("text/html;charset=utf-8");
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(TOKEN_FORM);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter(TOKEN_FORM);

        if(StrUtil.isEmpty(clientToken)){
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        log.debug("校验是否重复提交：表单页面Token值为："+clientToken + ",Session中的Token值为:"+serverToken);
        return false;
    }
}
