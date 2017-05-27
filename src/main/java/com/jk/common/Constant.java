package com.jk.common;

/**
 *
 * Created by JK on 2017/5/18.
 */
public class Constant {

    /**
     * 定时任务状态
     *
     */
    public enum JobStatus {

        /**
         * 暂停
         */
        PAUSE(0),
        /**
         * 正常
         */
        NORMAL(1);


        private int value;

        private JobStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 表单token
     */
    public static final String TOKEN_FORM = "tokenForm";

    /**
     * 刷新表单token
     */
    public static final String HEAD_REFRESH_TOKEN_FORM = "X-Refresh-Token-Form";

    /**
     * Ajax操作没有权限的响应头key
     */
    public static final String HEAD_NO_PERMISSION_KEY = "X-No-Permission";

    /**
     * Ajax操作没有权限的响应头value
     */
    public static final String HEAD_NO_PERMISSION_VALUE = "No-Permission";

    /**
     * Ajax操作登陆超时的响应头key
     */
    public static final String HEAD_SESSION_STATUS_KEY = "X-Session-Status";

    /**
     * Ajax操作登陆超时的响应头value
     */
    public static final String HEAD_SESSION_STATUS_VALUE = "Session-Timeout";
}
