package com.jk.aop;

import com.jk.annotation.OperationLog;
import com.jk.model.Log;
import com.jk.service.LogService;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 使用aop实现系统操作日志
 * author : cuiP
 */
@Aspect
@Service
public class OperationLogAspect {

    /**
     * 注入soaClient用于把日志保存数据库
     */
    @Resource
    private LogService logService;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);

    /**
     * 定义日志切入点
     */
    @Pointcut("@annotation(com.jk.annotation.OperationLog)")
    public void serviceAspect(){
    }

    /**
     * 后置通知 用于拦截service层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("serviceAspect()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            //*========控制台输出=========*//
            // System.out.println("=====后置通知开始=====");
            // System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            // System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));

            // 获取注入点方法中的参数(HttpSaasRequest request, HttpSaasResponse response)
            HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[0];
            String uri = request.getRequestURI();
            String path = request.getContextPath();
            // 获取客户端IP
            String ip = request.getRemoteAddr();

            // 从请求中获取参数列表
//            Set<String> parameterNames = request.getParameterNames();
            Map<String, String[]> requestParams = request.getParameterMap();

            // 读取session，获取用户信息
            // String user = (String) session.getAttribute(WebConstants.CURRENT_USER);
            String user = "admin";
            // System.out.println("请求参数:" + GsonUtil.toJson(requestParams));
            // System.out.println("请求uri:" + uri);
            // System.out.println("请求path:" + path);
            // System.out.println("请求IP:" + ip);

            // 获取response
            HttpServletResponse saasResponse = (HttpServletResponse)joinPoint.getArgs()[1];
            String status = saasResponse.getStatus()+"";
            //TODO 响应内容
            String content = "";
            // System.out.println("请求返回状态:"+status);
            // System.out.println("请求返回值:"+content);

            //*========数据库日志=========*//
            Log log = new Log();
            log.setAppName("");
            log.setUser(user);
            log.setLogType(0);
            log.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setRequestParams(JSONUtil.toJsonStr(requestParams));
            log.setMethodDescription(getServiceMethodDescription(joinPoint));

            log.setRequestIp(ip);
            log.setRequestUri(uri);
            log.setRequestPath(path);

            log.setExceptionCode(null);
            log.setExceptionDetail(null);

            log.setStatus(status);
            log.setContent(content);

            log.setCreateTime(Calendar.getInstance().getTime());
            // 保存数据库
            logService.save(log);
//            String result = soaClient.requestPlatform("/logService/save",GsonUtil.toJson(log));
            // System.out.println("=====后置通知结束=====");
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
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Map<String,String[]> requestParams = new HashMap<>();
        try {
            /*========控制台输出=========*/

            // System.out.println("=====异常通知开始=====");
            // System.out.println("异常代码:" + e.getClass().getName());
            // System.out.println("异常信息:" + e.getMessage());
            // System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            // System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));

            // 获取注入点方法中的参数(HttpSaasRequest request, HttpSaasResponse response)
            HttpServletRequest saasRequest = (HttpServletRequest)joinPoint.getArgs()[0];
            String uri = saasRequest.getRequestURI();
            String path = saasRequest.getContextPath();
            // 获取客户端IP
            String ip = saasRequest.getRemoteAddr();

            // 从请求中获取参数列表
            requestParams = saasRequest.getParameterMap();
            // System.out.println("请求参数:" + GsonUtil.toJson(requestParams));

            // 读取session，获取用户信息
            // String user = (String) session.getAttribute(WebConstants.CURRENT_USER);
            String user = "admin";
            // System.out.println("请求参数:" + GsonUtil.toJson(requestParams));
            // System.out.println("请求uri:" + uri);
            // System.out.println("请求path:" + path);
            // System.out.println("请求IP:" + ip);

            // 获取response
            HttpServletResponse saasResponse = (HttpServletResponse)joinPoint.getArgs()[1];
            String status = saasResponse.getStatus()+"";
            //TODO 响应内容
            String content = "";
            // System.out.println("请求返回状态:"+status);
            // System.out.println("请求返回值:"+content);

            /*==========数据库日志=========*/
            Log log = new Log();
            log.setAppName("");
            log.setUser(user);
            log.setLogType(1);
            log.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setRequestParams(JSONUtil.toJsonStr(requestParams));
            log.setMethodDescription(getServiceMethodDescription(joinPoint));

            log.setRequestIp(ip);
            log.setRequestUri(uri);
            log.setRequestPath(path);

            log.setExceptionCode(e.getClass().getName());
            log.setExceptionDetail(e.getMessage());

            log.setStatus(status);
            log.setContent(content);

            log.setCreateTime(Calendar.getInstance().getTime());
            //保存数据库
            logService.save(log);
//            String result = soaClient.requestPlatform("/logService/save",GsonUtil.toJson(log));
            // System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), GsonUtil.toJson(requestParams));

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getServiceMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(OperationLog.class).value();
                    break;
                }
            }
        }
        return description;
    }

}