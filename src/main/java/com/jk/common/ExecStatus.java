package com.jk.common;

import lombok.Getter;

/**
 * 执行状态定义
 * @author cuiP
 * Created by JK on 2017/5/27.
 */
@Getter
public enum ExecStatus {

    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),
    //-----参数问题
    INVALID_PARAM(1000, "参数不合法"),
    //-----数据验证问题

    //－－－－－－用户组件

    VALIDATION_FAIL(2000, "数据验证失败，请检查数据是否满足约束条件。"),
    USER_EXITS(2001, "用户已注册"),
    USER_NOT_EXITS(2002, "用户未注册"),
    INVALID_USER(2003, "密码错误，三次以后用户将被锁定。"),
    USER_INVALID(2004, "用户错误"),
    USER_LOGOUT(2005, "已注销"),
    GROUP_NOT_EXITS(2011, "组不存在"),
    GROUP_CODE_DUP(2012, "组编号重复"),
    GROUP_INVALID(2013, "组错误"),
    ROLE_NOT_EXITS(2021, "角色不存在"),
    ROLE_CODE_DUP(2022, "角色编号重复"),
    ORGANIZATION_NOT_EXITS(2031, "机构不存在"),
    SECRET_KEY_INVALID(2041, "非法密钥"),
    SECRET_KEY_NOT_EXITS(2042, "密钥不存在"),
    INVALID_EMAIL(2051, "邮箱不合法"),
    INVALID_MOBILE(2052, "手机不合法"),
    PASSWORD_INVALID(2053, "旧密码错误"),
    CODE_NOT_EXITS(2061, "验证码不存在"),
    EXPIRED_CODE(2062, "验证码过期"),
    CODE_USED(2063, "验证码已使用"),
    CODE_INVALID(2064, "验证码错误"),
    INVALID_TOKEN(2071, "令牌不合法"),
    EXPIRED_TOKEN(2072, "令牌过期"),
    TOKEN_NOT_EXITS(2073, "令牌不存在"),
    INVALID_SIGNATURE(2074, "签名错误"),
    //表单重复提交等
    FORM_REP_REPEATED_SUBMIT(2075, "当前页面已过期，请刷新页面重试"),
    //强制踢出提示
    KICK_OUT_TIPS(2076, "账号已经在其他地方登录，请重新登录！"),
    //强制踢出询问
    KICK_OUT_ASK(2077, "账号已在其他地方登录，IP: {} <br/>登录时间: {} "),
    //---------

    CHECKINFO_FAIL(2081, "无审核信息"),

    API_NOT_ENABLED(3000, "API 未启用"),
    ACCOUNT_FAIL(5000, "账户不存在"),
    ACCOUNT_ZX(5001, "账户已被注销"),
    ACCOUNT_FROZEN(5002, "账户已被冻结"),
    ACCOUNT_LOCK(5003, "账户已被锁定"),


    DAO_ERR(6000, "数据访问失败！"),

    //-----系统异常
    SYSTEM_ERR(10000, "系统异常，请联系管理员");

    private final int code;
    private final String msg;

    ExecStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
