package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统日志
 * author : cuiP
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Log extends BaseEntity{

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 日志类型 0操作日志，1异常日志
     */
    private Integer logType;
    /**
     * 操作人
     */
    private String user;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 请求方式(GET,POST,DELETE,PUT)
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 方法描述
     */
    private String methodDescription;
    /**
     * 访问者ip
     */
    private String requestIp;
    /**
     * 请求uri
     */
    private String requestUri;
    /**
     * 异常编码
     */
    private String exceptionCode;
    /**
     * 异常详情
     */
    private String exceptionDetail;
    /**
     * 请求响应状态
     */
    private String status;
    /**
     * 请求响应内容
     */
    private String respContent;
    /**
     * 耗时
     */
    private Long timeConsuming;
    /**
     * 客户端信息
     */
    private String userAgent;

    //TODO 是否异常
}