package com.jk.common.exception;

import com.jk.common.ExecStatus;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 数据校验异常类
 * @author cuiP
 * Created by JK on 2017/5/26.
 */
@Getter
public class ValidateException extends BaseException {

    private static final long serialVersionUID = 2239780575292471688L;
    private final Map<String, String> errors = new HashMap<>();

    public ValidateException() {
        this(ExecStatus.VALIDATION_FAIL);
    }

    public ValidateException(ExecStatus execStatus) {
        this(execStatus.getCode(), execStatus.getMsg());
    }

    public ValidateException(String msg) {
        super(ExecStatus.VALIDATION_FAIL.getCode(), msg);
    }

    public ValidateException(int code, String msg) {
        super(code, msg);
    }

    public ValidateException(ConstraintViolationException ex) {
        super(ExecStatus.VALIDATION_FAIL.getCode(), ExecStatus.VALIDATION_FAIL.getMsg());

        this.errors.putAll(toMap(ex.getConstraintViolations()));

    }

    public ValidateException addError(String name, String message) {
        this.errors.put(name, message);
        return this;
    }

    public <T> ValidateException(Set<ConstraintViolation<T>> violations) {
        super(ExecStatus.VALIDATION_FAIL.getCode(), ExecStatus.VALIDATION_FAIL.getMsg());
        this.errors.putAll(toErrorMap(violations));
    }

    private <T> Map<String, String> toErrorMap(Set<ConstraintViolation<T>> violations) {
        Map<String, String> errs = new HashMap<>();
        if (!violations.isEmpty()) {
            Iterator<ConstraintViolation<T>> iter = violations.iterator();
            while (iter.hasNext()) {
                ConstraintViolation<T> it = iter.next();
                errs.put(it.getPropertyPath().toString(), it.getMessage());
            }
        }
        return errs;
    }

    private Map<String, String> toMap(Set<ConstraintViolation<?>> violations) {

        Map<String, String> errs = new HashMap<>();
        if (!violations.isEmpty()) {
            Iterator<ConstraintViolation<?>> iter = violations.iterator();
            while (iter.hasNext()) {

                ConstraintViolation<?> it = iter.next();
                errs.put(it.getPropertyPath().toString(), it.getMessage());
            }
            this.errors.putAll(errors);
        }
        return errs;
    }
}
