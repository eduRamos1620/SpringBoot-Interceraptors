package com.ramos.springboot.interceptor.springboot_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("timeInterceptors")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //EL interceptor se aplica a todas las rutas
        //registry.addInterceptor(timeInterceptor);
        
        //Seleccionando rutas donde se applicra el Interceptor
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/baz");

        //Exluyendo rutas donde no queremos que se aplique el Interceptor
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/foo", "/app/bar");
    }
    
}
