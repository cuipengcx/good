package com.jk.common.base.interceptor;

import com.jk.common.exception.RepeatedSubmitFormException;
import com.jk.common.security.token.FormToken;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

import static com.jk.common.Constant.TOKEN_FORM;

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
                        throw new RepeatedSubmitFormException();
                    }
                    //移除session中保存的旧token
                    request.getSession(false).removeAttribute(TOKEN_FORM);
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
