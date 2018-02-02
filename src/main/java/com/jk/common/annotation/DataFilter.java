package com.jk.common.annotation;

import java.lang.annotation.*;

/**
 * 数据过滤
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {

    /**
     * 表的别名
     * @return
     */
    String tableAlias() default "temp_table_data_scope";

    /**
     * 列名
     * @return
     */
    String column() default "dept_id";

    /**
     * true：没有本部门数据权限，也能查询本人数据
     * @return
     */
//    boolean user() default true;
}
