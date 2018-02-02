package com.jk.common.annotation;//package com.jkinvest.backweb.aop;
//
//import com.jkinvest.filter.DataScope;
//import com.xiaoleilu.hutool.util.StrUtil;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//
//import java.sql.Connection;
//import java.util.Properties;
//
///**
// * @package: com.jkinvest.backweb.aop
// * @description: Mybatis数据级别权限拦截器
// * @author: cuiP
// * @date: 2018/1/31 16:34
// * @version: V1.0.0
// */
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//public class DataAuthInterceptor implements Interceptor{
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Object target = invocation.getTarget();
//        if(target instanceof StatementHandler){
//            StatementHandler statementHandler = (StatementHandler)target;
//            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
//            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
//
//            if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
//                return invocation.proceed();
//            }
//
//            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
//            String originalSql = boundSql.getSql();
//            Object parameterObject = boundSql.getParameterObject();
//
//            if(parameterObject instanceof DataScope){
//                DataScope dataScope = (DataScope) parameterObject;
//                String filterSql = dataScope.getFilterSql();
//                if(StrUtil.isNotEmpty(filterSql)){
//                    originalSql = originalSql + filterSql;
//                }
//                System.out.println(originalSql);
//                metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);
//            }
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
