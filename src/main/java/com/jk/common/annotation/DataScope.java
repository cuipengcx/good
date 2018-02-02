package com.jk.common.annotation;

import lombok.Data;

/**
 * @package: com.jkinvest.backweb.aop
 * @description
 * @author: cuiP
 * @date: 2018/2/1 16:08
 * @version: V1.0.0
 */
@Data
public class DataScope {

    /**
     * 过滤sql片段
     * 比如 select * from
     * SELECT * from ({}) temp WHERE temp.dept_id in(3,4);
     */
    private String filterSql;
}
