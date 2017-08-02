package com.jk.common.annotation;

import java.lang.annotation.*;

/**
 * 定义aop annotation切入点凡是方法上标注了@OperationLog的则会自动记录操作日志和异常日志
 * author : cuiP
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    /**
     * 日志操作描述
     * @return
     */
    String value()  default "";
}