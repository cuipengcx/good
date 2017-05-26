package com.jk.controller;

import com.jk.common.GoodResult;
import com.jk.exception.ValidateException;
import com.jk.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
     * spring validator校驗結果處理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<GoodResult<Map<String, String>>> handleBindExceptionException(BindException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(extractMsg(ex.getBindingResult()));
    }

    /**
     * spring validator校驗結果處理
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GoodResult<Map<String, String>>> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(extractMsg(ex.getBindingResult()));
    }

    /**
     * 处理未认证异常(未登录)
     * @param ex
     * @param request
     * @param response
     * @return
     */
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<GoodResult<Void>> handleUnauthorizedException(AuthenticationException ex,
//                                                                        HttpServletRequest request,
//                                                                        HttpServletResponse response){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GoodResult<Void>(ex.getCode(), ex.getMessage(), null));
//    }

    /**
     * 处理未许可异常(资源无访问权限)
     * @param ex
     * @param request
     * @param response
     * @throws Exception
     */
    @ExceptionHandler({UnauthorizedException.class})
    public void handlerUnauthenticatedException(UnauthorizedException ex,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        if(WebUtil.isAjaxRequest(request)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);//无权限异常  主要用于ajax请求返回
            response.setHeader(HEAD_NO_PERMISSION_KEY, HEAD_NO_PERMISSION_VALUE);
//				response.setHeader("No-Permission", "{\"code\":403,\"msg\":'No Permission'}");
            response.setContentType("text/html;charset=utf-8");
        }else {
            response.sendRedirect("/admin/403");
        }
    }

    /**
     * 封裝校驗結果
     * @param bindingResult
     * @return
     */
    private GoodResult<Map<String, String>> extractMsg(BindingResult bindingResult) {
        ValidateException vex = new ValidateException();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError err : errors) {
                vex.addError(err.getField(), err.getDefaultMessage());
            }
        }
        log.debug("数据验证失败：{}", vex.getMessage());
        return new GoodResult<>(vex.getCode(), vex.getMsg(), vex.getErrors());
    }
}
