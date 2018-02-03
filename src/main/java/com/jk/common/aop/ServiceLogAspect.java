package com.jk.common.aop;

import com.jk.common.exception.BaseException;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ScheduleLogAspect <br/>
 * Function: 切点类. <br/>
 *
 * @author luoxin
 * @version
 */
@Aspect
@Component
public class ServiceLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private ThreadLocal<Map<String, Object>> localMap = new ThreadLocal<>();

	/**
	 * 拦截service包及其子包所有的public方法
	 * serviceAspect:(Service层切点). <br/>
	 * @author luoxin
	 * @author cuiP
	 */
	@Pointcut(value = "execution(public * com.jkinvest.service..*.*(..))")
	public void serviceAspect() {
	}

	/**
	 * 
	 * doBefore:(前置通知 用于service层). <br/>
	 *
	 * @author luoxin
	 * @param joinPoint
	 */
	@Before(value = "serviceAspect()")
	public void doBefore(JoinPoint joinPoint) {
		// 记录请求开始时间
		startTime.set(System.currentTimeMillis());
		Map<String, Object> logMap = new ConcurrentHashMap<>();
		try {
			logMap.put("fullClassName", getFullMethodName(joinPoint));
			logMap.put("methodName", joinPoint.getSignature().getName());
			logMap.put("paramsJson", JSONUtil.toJsonStr(joinPoint.getArgs()));
			localMap.set(logMap);
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	 * @param ret
	 * @throws Throwable
	 */
	@AfterReturning(returning = "ret", pointcut = "serviceAspect()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		long timeConsuming = System.currentTimeMillis() - startTime.get();
		Map<String, Object> logMap = localMap.get();

		logger.info("请求方法全路径:{},请求方法:{},请求参数:{},请求时间: {}", logMap.get("fullClassName"), logMap.get("methodName"),
				logMap.get("paramsJson"), timeConsuming);
	}

	/**
	 * doAfterThrowing:(异常通知 用于拦截service层记录异常日志). <br/>
	 * @author luoxin
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(value = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		//参数验证这些不需要记录
		if (!(e instanceof BaseException)) {
			long timeConsuming = System.currentTimeMillis() - startTime.get();
			logger.error("异常方法全路径:{},异常信息:{},请求参数:{},请求时间: {}", getFullMethodName(joinPoint), e.getMessage(),
					JSONUtil.toJsonStr(args), timeConsuming);
		}
	}

	private static String getFullMethodName(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		// 请求的方法名全路径
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();

		return className + "." + methodName + "()";
	}
}
