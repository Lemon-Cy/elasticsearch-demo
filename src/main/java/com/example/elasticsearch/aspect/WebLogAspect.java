package com.example.elasticsearch.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.StringJoiner;

/**
 * <p>
 * 接口调用日志
 * </p>
 *
 * @author Joshua
 * @since 2021/4/29 9:55
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
@Order(2)
public class WebLogAspect {

    /**
     * 以自定义 @LogPrint 注解为切点
     */
    @Pointcut("execution(public * com.example.elasticsearch.controller.*Controller.*(..))")
    public void logPrint() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint 切入点
     */
    @Before("logPrint()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null : "请求属性为空";
        HttpServletRequest request = attributes.getRequest();

        // 打印请求的 IP
        log.info("IP 地址\t: {}", request.getRemoteAddr());
        // 打印请求 url
        log.info("请求地址\t: {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("请求方式\t: {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("请求方法\t: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求入参
        log.info("请求参数\t: {}", getParams(joinPoint));

    }

    /**
     * 环绕
     *
     * @return Object
     */
    @Around("logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            result = e.getMessage();
            throw e;
        } finally {
            // 打印出参
//            log.info("执行结果\t: {}", JSONObject.toJSONString(result));
            // 执行耗时
            log.info("执行耗时\t: {} ms\n", System.currentTimeMillis() - startTime);
        }
        return result;
    }

    /**
     * 获取参数
     *
     * @param joinPoint 切入点
     * @return java.lang.String
     * @since 2021/3/11 11:31
     */
    private String getParams(JoinPoint joinPoint) {
        StringJoiner params = new StringJoiner(",");
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                Object arg = joinPoint.getArgs()[i];
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest)
                        || (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                try {
                    params.add(JSONObject.toJSONString(joinPoint.getArgs()[i]));
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }
        return params.toString();
    }
}