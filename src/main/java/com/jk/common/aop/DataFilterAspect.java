package com.jk.common.aop;

import com.jk.common.annotation.DataFilter;
import com.jk.common.annotation.DataScope;
import com.jk.common.util.ShiroUtils;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.service.DeptService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 数据过滤，切面处理类
 * 动态查询当前用户的部门及子部门，并拼装成sql
 */
@Aspect
@Component
public class DataFilterAspect {

    @Autowired
    private DeptService deptService;

    @Pointcut("@annotation(com.jk.common.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];
        if(params != null ){
            User userEntity = ShiroUtils.getUserEntity();
            if(params instanceof DataScope){
                DataScope dataScope = (DataScope) params;

                //如果不是超级管理员，则只能查询本部门及子部门数据
                if(!userEntity.getIsAdmin()){
                    String filterSql = getFilterSql(userEntity, point);
                    dataScope.setFilterSql(filterSql);
                }
            }else if(params instanceof Map){

            }
        }
    }

    /**
     * 封装过滤sql片段
     * @return
     */
    private static String getFilterSql(User userEntity, JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);

        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        //获取列名
        String column = dataFilter.column();

        //and temp.dept_id in(3,4);
        String deptIds = "3,4";
        StringBuilder filterSql = new StringBuilder(" and ");
        filterSql.append(tableAlias);
        filterSql.append(".");
        filterSql.append(column);
        filterSql.append(" in(");
        filterSql.append(deptIds);
        filterSql.append(")");
        return filterSql.toString();
    }
}
