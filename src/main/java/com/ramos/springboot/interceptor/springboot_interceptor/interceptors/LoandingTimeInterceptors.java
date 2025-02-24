package com.ramos.springboot.interceptor.springboot_interceptor.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptors")
public class LoandingTimeInterceptors implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoandingTimeInterceptors.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("LoandingTimeInterceptor: postHandle() saliendo......" + ((HandlerMethod) handler).getMethod().getName());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod controller = ((HandlerMethod) handler);
        logger.info("LoandingTimeInterceptor: preHandle() entrando......" + controller.getMethod().getName());
        return true;
    }
    
}
