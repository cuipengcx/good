package com.jk.aop;

import com.jk.annotation.OperationLog;
import com.jk.model.Log;
import com.jk.service.LogService;
import com.jk.util.ShiroUtils;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 注入soaClient用于把日志保存数据库
     */
    @Resource
    private LogService logService;
    @Resource
    private HttpServletRequest request; //这里可以获取到request
    @Resource
    private HttpServletResponse response;//这里可以获取到response


    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);

    /**
     * 定义日志切入点
     */
    @Pointcut("@annotation(com.jk.annotation.OperationLog)")
    public void logPointCut(){
    }

    /**
     * 后置通知 用于拦截service层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();

            //*========数据库日志=========*//
            Log log = new Log();
            log.setAppName("");
            log.setUser(ShiroUtils.getUserEntity().getUsername());
            log.setLogType(0);
            log.setMethodName(getFullMethodName(joinPoint));
            log.setRequestMethod(request.getMethod());
            //TODO
            log.setRequestParams(JSONUtil.toJsonStr(args));

            log.setMethodDescription(getMethodDescription(joinPoint));

            log.setRequestIp(HttpUtil.getClientIP(request));
            log.setRequestUri(request.getRequestURI());
            //TODO
            log.setRequestPath(request.getContextPath());

            log.setExceptionCode(null);
            log.setExceptionDetail(null);

            log.setStatus(response.getStatus()+"");
            //TODO
            log.setContent("");

            // 保存数据库
            logService.save(log);
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
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
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();

            /*==========数据库日志=========*/
            Log log = new Log();
            log.setAppName("");
            log.setUser(ShiroUtils.getUserEntity().getUsername());
            log.setLogType(1);
            log.setMethodName(getFullMethodName(joinPoint));
            log.setRequestMethod(request.getMethod());
            //TODO
            log.setRequestParams(JSONUtil.toJsonStr(args));

            log.setMethodDescription(getMethodDescription(joinPoint));

            log.setRequestIp(HttpUtil.getClientIP(request));
            log.setRequestUri(request.getRequestURI());
            //TODO
            log.setRequestPath(request.getContextPath());

            log.setExceptionCode(e.getClass().getName());
            log.setExceptionDetail(e.getMessage());

            log.setStatus(response.getStatus()+"");
            //TODO
            log.setContent("");

            //保存数据库
            logService.save(log);
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), JSONUtil.toJsonStr(requestParams));
    }



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