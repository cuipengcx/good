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

}
