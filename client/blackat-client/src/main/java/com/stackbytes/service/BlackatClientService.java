package com.stackbytes.service;

import com.stackbytes.config.BlackatClientVariables;
import com.stackbytes.model.BlackatContext;
import com.stackbytes.model.BlackatEndpoint;
import com.stackbytes.model.Param;
import jakarta.websocket.Endpoint;
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

import java.lang.reflect.Parameter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;
import java.util.prefs.BackingStoreException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //Explicit singleton instancing
public class BlackatClientService {

    private final ApplicationContext applicationContext;
    private final BlackatAlertSystem alertSystem;
    private final BlackatClientVariables blackatClientVariables;



    @Autowired
    BlackatClientService(ApplicationContext applicationContext, ServletWebServerApplicationContext servletWebServerApplicationContext, BlackatAlertSystem alertSystem, Environment environment, BlackatClientVariables blackatClientVariables) {
        this.applicationContext =  applicationContext;
        this.alertSystem = alertSystem;
        this.blackatClientVariables = blackatClientVariables;
    }


    /*
        NOTE: When being imported in the project, if it doesn't find a name it will fallback on its own application.properties
     */
    @Value("${spring.application.name}")
    private String applicationName = null;

    @Value("${server.port}")
    private String clientPort;

    public BlackatContext getRunningContext(){



        BlackatContext blackatContext = BlackatContext.builder()
                .serviceName(Optional.of(applicationName).orElse("blackat-service"))
                .port(Optional.of(Integer.parseInt(clientPort)).orElse(Integer.parseInt(clientPort)))
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


    public List<BlackatEndpoint> getEndpoints(String clientId) {

        if(!blackatClientVariables.getId().equals(clientId)){
            return null;
        }

        List<BlackatEndpoint> endpoints = new ArrayList<>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();


        for(Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()){

            RequestMappingInfo requestMappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();

            String name = handlerMethod.getMethod().getName();


            List<Param> params = Arrays.stream(handlerMethod.getMethod().getParameters())
                    .map(p -> new Param(p.getName(), p.getType().getSimpleName()))
                    .toList();



            String path = requestMappingInfo.getDirectPaths().toString().substring(1, entry.getKey().getDirectPaths().toString().length() - 1);
            String method = requestMappingInfo.getMethodsCondition().getMethods().toString().substring(1, entry.getKey().getMethodsCondition().getMethods().toString().length() - 1);



            assert name.isEmpty() || path.isEmpty() || method.isEmpty();

            BlackatEndpoint endpoint = BlackatEndpoint.builder()
                    .url(path)
                    .method(method)
                    .name(name)
                    .parameters(params)
                    .build();

            endpoints.add(endpoint);

        }


        return endpoints;

    }
}
