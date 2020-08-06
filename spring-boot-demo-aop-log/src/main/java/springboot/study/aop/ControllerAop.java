package springboot.study.aop;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Date;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class ControllerAop {
    //切入点
    @Pointcut("execution(public * springboot.study.controller.*Controller.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = Objects.requireNonNull(servletRequestAttributes).getRequest();
        StringBuffer requestUrl = servletRequest.getRequestURL();
        log.info("请求路径=" + requestUrl);
        log.info("请求ip地址=" + servletRequest.getRemoteAddr());
        log.info("请求的类名=" + joinPoint.getSignature().getDeclaringTypeName());
        log.info("请求的方法名=" + joinPoint.getSignature().getName());
        log.info("请求时间=" + new Date());
        servletRequest.setAttribute("begin", System.currentTimeMillis());
    }

    @Around("log()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //controller原先返回的值
        Object o
                = proceedingJoinPoint.proceed();
        log.info("返回值=" + JSONUtil.toJsonStr(o));

    }

    @After("log()")
    public void after() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String agent = httpServletRequest.getHeader("User-Agent");
        long begin = (long) httpServletRequest.getAttribute("begin");
        long end = System.currentTimeMillis();
        log.info("请求时间花费"+(end-begin)+"毫秒");

       UserAgent userAgent =  UserAgent.parseUserAgentString(agent);
         log.info("浏览器版本为="+userAgent.getBrowser()+"操作系统为="+userAgent.getOperatingSystem());
    }
}
