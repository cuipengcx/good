package com.jk.exception;

/**
 * 数据校验异常类
 * @author cuiP
 * Created by JK on 2017/5/26.
 */
public class ValidateException extends GoodException{

    public ValidateException(String msg) {
        super(msg);
    }

    public ValidateException(String msg, Throwable e) {
        super(msg, e);
    }

    public ValidateException(int code, String msg) {
        super(code, msg);
    }

    public ValidateException(int code, String msg, Throwable e) {
        super(code, msg, e);
    }
}
