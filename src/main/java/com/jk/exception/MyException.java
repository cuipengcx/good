package com.jk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MyException extends RuntimeException{

    private String msg;
    private int code = 500;

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
