package com.jk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodException extends RuntimeException{

    private int code = 500;
    private String msg;

    public GoodException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GoodException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GoodException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GoodException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }
}
