package com.jk.common.base.controller;

import com.jk.common.DataResult;
import com.jk.common.ExecStatus;
import com.jk.common.exception.BaseException;
import com.jk.common.exception.RepeatedSubmitFormException;
import com.jk.common.exception.ValidateException;
import com.jk.common.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.UUID;

import static com.jk.common.Constant.*;

/**
 * 全局异常处理
 * @author cuiP
 * Created by JK on 2017/5/26.
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 统一处理公共的 runtime异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<DataResult> handleBaseException(BaseException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResult<>(ex.getCode(), ex.getMessage(), null));
    }

    /**
     * 统一处理其他异常，非RuntimeException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResult> handleAllException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResult(ExecStatus.FAIL.getCode(), "系统繁忙，请稍后重试！"));
    }

    /**
     * 缺少请求参数
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DataResult> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("缺少请求参数", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult(ExecStatus.INVALID_PARAM.getCode(), ExecStatus.INVALID_PARAM.getMsg()));
    }

    /**
     * 处理表单重复提交异常
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(RepeatedSubmitFormException.class)
    public ResponseEntity<DataResult> handleBindExceptionException(RepeatedSubmitFormException ex,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult(ex.getCode(), ex.getMsg()));
    }

    /**
     * 手动校验校验结果处理
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<DataResult> handleConstraintViolationException(ValidateException ex,
                                                                         HttpServletRequest request,
                                                                         HttpServletResponse response) {
        //判断提交表单的请求是否为Ajax请求,若是则生成refresh_token,以替换表单页面的formToken,解决Ajax提交后,验证不通过无法再次提交的问题
        if(WebUtil.isAjaxRequest(request)){
            String uuid = UUID.randomUUID().toString();
            //往session重新set个值
            request.getSession(false).setAttribute(TOKEN_FORM, uuid);
            //refresh_token放在header中
            response.setHeader(HEAD_REFRESH_TOKEN_FORM, uuid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>(ex.getCode(), ex.getMsg(), ex.getErrors()));
    }

    /**
     * spring validator实体对象参数校验结果处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<DataResult> handleBindExceptionException(BindException ex,
                                                                   HttpServletRequest request,
                                                                   HttpServletResponse response) {

        //判断提交表单的请求是否为Ajax请求,若是则生成refresh_token,以替换表单页面的formToken,解决Ajax提交后,验证不通过无法再次提交的问题
        if(WebUtil.isAjaxRequest(request)){
            String uuid = UUID.randomUUID().toString();
            //往session重新set个值
            request.getSession(false).setAttribute(TOKEN_FORM, uuid);
            //refresh_token放在header中
            response.setHeader(HEAD_REFRESH_TOKEN_FORM, uuid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(extractMsg(ex.getBindingResult()));
    }

    /**
     * spring validator实体对象参数校验结果处理
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataResult> handleMethodArgumentException(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request,
                                                                    HttpServletResponse response) {
        //判断提交表单的请求是否为Ajax请求,若是则生成refresh_token,以替换表单页面的formToken,解决Ajax提交后,验证不通过无法再次提交的问题
        if(WebUtil.isAjaxRequest(request)){
            String uuid = UUID.randomUUID().toString();
            //往session重新set个值
            request.getSession(false).setAttribute(TOKEN_FORM, uuid);
            //refresh_token放在header中
            response.setHeader(HEAD_REFRESH_TOKEN_FORM, uuid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(extractMsg(ex.getBindingResult()));
    }

    /**
     * spring validator方法参数校验结果处理
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DataResult> handleConstraintViolationException(ConstraintViolationException ex,
                                                                    HttpServletRequest request,
                                                                    HttpServletResponse response) {
        ValidateException vex = new ValidateException(ex);
        //判断提交表单的请求是否为Ajax请求,若是则生成refresh_token,以替换表单页面的formToken,解决Ajax提交后,验证不通过无法再次提交的问题
        if(WebUtil.isAjaxRequest(request)){
            String uuid = UUID.randomUUID().toString();
            //往session重新set个值
            request.getSession(false).setAttribute(TOKEN_FORM, uuid);
            //refresh_token放在header中
            response.setHeader(HEAD_REFRESH_TOKEN_FORM, uuid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>(vex.getCode(), vex.getMsg(), vex.getErrors()));
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
            response.setContentType("text/html;charset=utf-8");
        }else {
            response.sendRedirect("/admin/403");
        }
    }

    /**
     * 封装spring validator校验结果
     * @param bindingResult
     * @return
     */
    private DataResult extractMsg(BindingResult bindingResult) {
        ValidateException vex = new ValidateException();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError err : errors) {
                vex.addError(err.getField(), err.getDefaultMessage());
            }
        }
        log.debug("数据验证失败：{}", vex.getMessage());
        return new DataResult<>(vex.getCode(), vex.getMsg(), vex.getErrors());
    }
}
