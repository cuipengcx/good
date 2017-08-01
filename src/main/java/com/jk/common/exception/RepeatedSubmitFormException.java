package com.jk.common.exception;

import com.jk.common.ExecStatus;

/**
 * 表单重复提交异常类
 * @author cuiP
 * Created by JK on 2017/5/27.
 */
public class RepeatedSubmitFormException extends BaseException{

    private static final long serialVersionUID = 5341547962077784610L;

    public RepeatedSubmitFormException(){
        this(ExecStatus.FORM_REP_REPEATED_SUBMIT);
    }

    public RepeatedSubmitFormException(String msg) {
        super(msg);
    }

    public RepeatedSubmitFormException(int code, String msg) {
        super(code, msg);
    }

    public RepeatedSubmitFormException(int code, String msg, Throwable e) {
        super(code, msg, e);
    }

    public RepeatedSubmitFormException(ExecStatus status){
        super(status.getCode(), status.getMsg());
    }
}
