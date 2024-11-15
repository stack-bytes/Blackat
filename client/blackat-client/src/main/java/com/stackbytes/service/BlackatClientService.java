package com.stackbytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //Explicit singleton instancing
public class BlackatClientService {

    private final ApplicationContext applicationContext;

    @Autowired
    BlackatClientService(ApplicationContext applicationContext) {
        this.applicationContext =  applicationContext;
    }


    public List<String> scanEndpoints(){
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        List<String> enpoints = new ArrayList<>();
        handlerMethods.forEach((key, value) -> enpoints.add(value.getMethod().getName()));

        return enpoints;
    }

    public void getRunningContext(){

    }



}
