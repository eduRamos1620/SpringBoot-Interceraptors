package com.ramos.springboot.interceptor.springboot_interceptor.interceptors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptors")
public class LoandingTimeInterceptors implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoandingTimeInterceptors.class);
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        HandlerMethod controller = ((HandlerMethod) handler);
        logger.info("LoandingTimeInterceptor: preHandle() entrando......" + controller.getMethod().getName());

        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        //Generando un numero random para tener diferente tiempo de carga al calcularlo en el postHandler
        Random random = new Random();
        int delay = random.nextInt(500);
        //Aplicando un hilo para dormir maximo 500 milisegundos el proceso
        Thread.sleep(delay);

        //Se personaliza la respuesta en caso de que se devuleva un false en el return
        /*Map<String, String> json = new HashMap<>();
        json.put("error", "No tienes acceso a la pagina");
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(jsonString);

        return false;*/
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {
        
        //Calculando el tiempo que demora desde su inicio hasta su fin
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");    
        long result = end - start;
        logger.info("Tiempo transcurrido: " + result + " milisegundos");

        logger.info("LoandingTimeInterceptor: postHandle() saliendo......" + ((HandlerMethod) handler).getMethod().getName());
    }
}
