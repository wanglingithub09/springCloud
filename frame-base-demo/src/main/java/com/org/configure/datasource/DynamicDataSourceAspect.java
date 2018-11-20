package com.org.configure.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
* @Author: WangLin
* @Description: aop来实现根据注解来动态切换数据源的动作,@Order是spring容器启动时加载该配置的顺序
* @Date: 2018/11/16 17:01
*/
@Aspect
@Order(0)
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 切面com包下到mapper包下所有切入点
     */
    @Pointcut(value = "execution(* com..mapper..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        DataSourceContextHolder.clearDatabaseType();
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class<?>[] classz = target.getClass().getInterfaces();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        //设置默认数据源
        DatabaseType dataSource = DataSourceContextHolder.DEFAULT_DS;
        String methodName = "";
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DS.class)) {
                DS annotation = m
                        .getAnnotation(DS.class);
                dataSource = annotation.value();
                methodName = m.getName();
            }
        } catch (Exception e) {
            logger.error("DataSource switch error:{}", e.getMessage(), e);
        } finally {
            logger.info("{} | method  {}  | datasource  {}  | begin",
                    ((MethodSignature) point.getSignature()).getMethod().getDeclaringClass(), methodName,
                    dataSource);
        }
        DataSourceContextHolder.setDatabaseType(dataSource);
        Object obj = point.proceed();
        DataSourceContextHolder.clearDatabaseType();
        logger.info("{} | method  {}  | datasource  {}  | end",
                ((MethodSignature) point.getSignature()).getMethod().getDeclaringClass(), methodName,
                dataSource);
        return obj;
    }

}
