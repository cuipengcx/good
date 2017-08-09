package com.jk.common.aop;


import com.jk.common.annotation.OperationLog;
import com.jk.common.util.ShiroUtils;
import com.jk.modules.log.model.Log;
import com.jk.modules.log.service.LogService;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用aop实现系统操作日志
 * author : cuiP
 */
@Aspect
@Component
public class OperationLogAspect {


    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    private ThreadLocal<Log> localLog = new ThreadLocal<Log>();

    /**
     * 注入soaClient用于把日志保存数据库
     */
    @Resource
    private LogService logService;
    @Resource
    private HttpServletRequest request; //这里可以获取到request


    /**
     * 定义日志切入点
     */
    @Pointcut("@annotation(com.jk.common.annotation.OperationLog)")
    public void logPointCut(){
    }

    /**
     * 后置通知 用于拦截service层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            //记录请求开始时间
            startTime.set(System.currentTimeMillis());

            //请求的参数
            Object[] args = joinPoint.getArgs();

            //*========数据库日志=========*//
            Log log = new Log();
            log.setAppName("");
            log.setUsername(ShiroUtils.getUserName());
            log.setLogType(0);
            log.setMethodName(getFullMethodName(joinPoint));
            log.setRequestMethod(request.getMethod());
            log.setParams(request.getParameterMap());
            log.setOperation(getMethodDescription(joinPoint));
            log.setRequestIp(HttpUtil.getClientIP(request));
            log.setRequestUri(request.getRequestURI());
            log.setUserAgent(request.getHeader("User-Agent"));

            log.setExceptionCode(null);
            log.setExceptionDetail(null);

            localLog.set(log);
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容

        Log log = localLog.get();
        log.setTimeConsuming(System.currentTimeMillis() - startTime.get());

        // 保存数据库
        logService.save(log);
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Map<String,String[]> requestParams = new HashMap<>();
        //请求的参数
        Object[] args = joinPoint.getArgs();

        try {
            /*==========数据库日志=========*/
            Log log = localLog.get();

            log.setTimeConsuming(System.currentTimeMillis() - startTime.get());
            log.setLogType(1);

            log.setExceptionCode(e.getClass().getName());
            log.setExceptionDetail(e.getMessage());

            //保存数据库
            logService.save(log);
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("异常方法全路径:{},异常信息:{},请求参数:{}", getFullMethodName(joinPoint), e.getMessage(), JSONUtil.toJsonStr(args));
        }
    }

//    /**
//     * 环绕通知实现
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("@annotation(com.jk.annotation.OperationLog)")
//    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
//        // 获取切入的 Method
//        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
//        Method method = joinPointObject.getMethod();
//        OperationLog annotation = method.getAnnotation(OperationLog.class);
//        //获得日志标识
//        String logCode = annotation.value();
//
//        //执行实际方法
//        Object resBody =  pjp.proceed();
//
//        //获得返回结果
//        String resStr=String.valueOf(resBody);
//        //异步保存请求日志
//
//        return resStr;
//    }



    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String description = "";
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        if(null != operationLog){
            description = operationLog.value();
        }
        return description;
    }

    /**
     * 获取请求的方法名全路径
     * @param joinPoint
     * @return
     */
    private static String getFullMethodName(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //请求的方法名全路径
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        return className + "." + methodName + "()";
    }
}