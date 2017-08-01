package com.jk.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证异常类
 * @author cuiP
 * Created by cuip on 2017/5/26.
 */
public class AuthenticationException extends BaseException {

    public AuthenticationException(){
        super(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(int code, String msg) {
        super(code, msg);
    }

    public AuthenticationException(int code, String msg, Throwable e) {
        super(code, msg, e);
    }
}
