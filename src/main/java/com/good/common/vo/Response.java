package com.good.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.good.common.constant.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @packageName: com.good.common.vo
 * @description: Restful统一Json响应对象封装
 * @author: cuiP
 * @date: 2018/5/21 23:31
 * @version: V1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 2282359292976155747L;

    /**
     * 操作执行情况代码，0-表示成功，其他表示失败
     */
    private int code;

    /**
     * 执行情况描述
     */
    private String msg;

    /**
     * 返回结果数据
     */
    private T data;


    public Response(int code, String msg){
        this(code, msg, null);
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T>Response success(){
        return new Response<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(),null);
    }

    public static <T>Response success(T data){
        return new Response<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }



    public static <T>Response fail(){
        return new Response<T>(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg(), null);
    }

    public static <T>Response fail(ResultEnum resultEnum){
        return new Response<T>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T>Response fail(ResultEnum resultEnum, T data){
        return new Response<T>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static <T>Response fail(int code, String msg){
        return new Response<T>(code, msg, null);
    }

    public static <T>Response fail(int code, String msg, T data){
        return new Response<T>(code, msg, data);
    }
}
