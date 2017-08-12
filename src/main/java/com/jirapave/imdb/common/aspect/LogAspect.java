package com.jirapave.imdb.common.aspect;

import com.jirapave.imdb.common.constant.SpringProfiles;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Aspect to be able to properly log information about execution of controller in DEV profile only
 */
@Aspect
@Component
@Slf4j
@Profile(SpringProfiles.DEV)
public class LogAspect {


    @AfterReturning(pointcut = "isAnnotatedController()", returning = "result")
    public void logReturningDto(JoinPoint joinPoint, Object result) {
        if (result != null) {
            log.debug("Executed joinPoint: " + joinPoint.toShortString());
            log.debug("Returned object: " + result.toString());
        }
    }

    @Before("isAnnotatedController()")
    public void logCalledEndPoint(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("Called endPoint: " + UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toString() + " - " + request.getMethod());
    }

    /**
     * Annotated Controller with {@link com.jirapave.imdb.common.annotation.LogController} and only for public methods and package com.jirapave.imdb.rest.controller.*.
     *
     * NOTE: For DEV profile ONLY
     */
    @Pointcut("execution(public * com.jirapave.imdb.rest.controller.*.*(..)) && within(@com.jirapave.imdb.common.annotation.LogController *) && args(..)")
    void isAnnotatedController() {}
}
