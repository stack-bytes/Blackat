package com.stackbytes.service;

import com.stackbytes.model.BlackatContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //Explicit singleton instancing
public class BlackatClientService {

    private final ApplicationContext applicationContext;
    private final BlackatAlertSystem alertSystem;



    @Autowired
    BlackatClientService(ApplicationContext applicationContext, ServletWebServerApplicationContext servletWebServerApplicationContext, BlackatAlertSystem alertSystem, Environment environment) {
        this.applicationContext =  applicationContext;
        this.alertSystem = alertSystem;
    }


    public List<String> scanEndpoints(){
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        List<String> enpoints = new ArrayList<>();
        handlerMethods.forEach((key, value) -> enpoints.add(value.getMethod().getName()));

        return enpoints;
    }

    /*
        NOTE: When being imported in the project, if it doesn't find a name it will fallback on its own application.properties
     */
    @Value("${spring.application.name}")
    private String applicationName = null;

    @Value("${server.port}")
    private String clientPort;

    public BlackatContext getRunningContext(){
        if(clientPort == null || clientPort.isEmpty()){
            clientPort = "8080";
        }

        BlackatContext blackatContext = BlackatContext.builder()
                .serviceName(Optional.ofNullable(applicationName).orElse("blackat-service"))
                .port(Integer.parseInt(clientPort))
                .host(this.getHostOrFallback())
                .build();


        return blackatContext;
    }

    // TODO: Report to monitoring system!

    private String getHostOrFallback(){
        try{
            alertSystem.run(BlackatAlertLevel.HIGH, "IP address Ok");
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            alertSystem.run(BlackatAlertLevel.MEDIUM, "No ip address found");
            return "No IP";
        }
    }




}
