package com.apark.config.MsConfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <!-- 配置数据库注解aop -->
 *
 * @author lubin
 */
@Aspect
@Component
@Slf4j
public class DataSourceAopAspect {

    /**
     * 常量查询方法过滤
     */
    public static final String FIND = "find";

    public static final char SPLIT = 31;


    @Before("execution(* com.apark.dao..*(..))")
    public void before(JoinPoint point) throws NoSuchMethodException, SecurityException {
        String method = point.getSignature().getName();
        Class<?> targetClass = point.getTarget().getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        Object[] arguments = point.getArgs();
        // ? 为何不要find
        if (!method.contains(FIND)) {

            log.info("start sql 执行 ==> {}:{},args:{} ", targetClass.toString(), method, Arrays
                    .deepToString(arguments));
//			StringBuffer sb = new StringBuffer();
//			sb.append(WebUtils.getLoginUsername()).append(SPLIT);
//			sb.append(WebUtils.getHost()).append(SPLIT);
//			sb.append(Arrays.deepToString(arguments)).append(SPLIT);
//			sb.append(System.currentTimeMillis());
//			logger.info(sb);
        }
        // 方法注解可以覆盖类注解
        Method m = targetClass.getMethod(method, parameterTypes);
        if (m != null && m.isAnnotationPresent(DataSource.class)) {
            DataSource data = m.getAnnotation(DataSource.class);
            MultiDataSource.setDataSourceKey(data.value());
        }
    }

    @After("execution(* com.apark.dao..*(..))")
    public void after(JoinPoint point) {
        MultiDataSource.clearDataSource();
    }
}
