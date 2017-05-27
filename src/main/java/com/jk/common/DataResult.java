package com.jk.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * 通用返回结果
 * @author cuiP
 * Created by cuip on 2017/5/26.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class DataResult<T> {

    /**
     * 操作执行情况代码，0-表示成功，其他表示失败
     */
    private final int code;

    /**
     * 执行情况描述
     */
    private final String msg;

    /**
     * 返回结果
     */
    private final T result;

    public DataResult(int code, String msg){
        this(code, msg, null);
    }

    public DataResult(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}