package com.jk.controller;

import com.jk.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jk.common.Constant.HEAD_NO_PERMISSION_KEY;
import static com.jk.common.Constant.HEAD_NO_PERMISSION_VALUE;

/**
 * 全局异常处理
 * @author cuiP
 * Created by JK on 2017/5/26.
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 数据验证异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
//    @ExceptionHandler({ValidateException.class})
//    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws IOException {
//        return getResponseEntity(ex,request,ReturnStatusCode.DataNotFoundException);
//    }

    /**
     * TODO 回头更换为
     * @ResponseStatus(HttpStatus.UNAUTHORIZED)
     * @ResponseBody
     * 统一处理未授权异常
     * @param exception
     * @param request
     * @param response
     * @throws Exception
     */
    @ExceptionHandler({UnauthorizedException.class})
    public void exceptionHandler(Exception exception,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        if(WebUtil.isAjaxRequest(request)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//无权限异常  主要用于ajax请求返回
            response.setHeader(HEAD_NO_PERMISSION_KEY, HEAD_NO_PERMISSION_VALUE);
//				response.setHeader("No-Permission", "{\"code\":403,\"msg\":'No Permission'}");
            response.setContentType("text/html;charset=utf-8");
        }else {
            response.sendRedirect("/admin/403");
        }
    }
}
