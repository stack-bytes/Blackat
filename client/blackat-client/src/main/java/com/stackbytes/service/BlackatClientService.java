package com.stackbytes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackbytes.annotations.BlackatOmmitEndpoint;
import com.stackbytes.config.BlackatClientVariables;
import com.stackbytes.model.BlackatContext;
import com.stackbytes.model.BlackatEndpoint;
import com.stackbytes.model.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //Explicit singleton instancing
public class BlackatClientService{

    private final ApplicationContext applicationContext;
    private final BlackatAlertSystem alertSystem;
    private final BlackatClientVariables blackatClientVariables;
    private final ObjectMapper objectMapper;



    @Autowired
    BlackatClientService(ApplicationContext applicationContext, ServletWebServerApplicationContext servletWebServerApplicationContext, BlackatAlertSystem alertSystem, Environment environment, BlackatClientVariables blackatClientVariables, ObjectMapper objectMapper) {
        this.applicationContext =  applicationContext;
        this.alertSystem = alertSystem;
        this.blackatClientVariables = blackatClientVariables;
        this.objectMapper = objectMapper;
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

            BlackatOmmitEndpoint blackatOmmitEndpoint = handlerMethod.getMethodAnnotation(BlackatOmmitEndpoint.class);
            System.out.println(blackatOmmitEndpoint + " " + name);
            if(blackatOmmitEndpoint != null){
                continue;
            }


            List<Param> params = Arrays.stream(handlerMethod.getMethod().getParameters())
                    .filter(p -> Arrays.stream(p.getAnnotations())
                            .noneMatch(a -> a.annotationType().equals(RequestBody.class))
                    )
                    .map(p -> new Param(p.getName(), p.getType().getSimpleName()))
                    .toList();

            //TODO: Create json parsing to frontend
            List<String> bodies = Arrays.stream(handlerMethod.getMethod().getParameterTypes())
                    .filter(p->Arrays.stream(p.getAnnotations())
                            .allMatch(a->a.annotationType().equals(RequestBody.class)
                            )).map(a-> {
                        try {
                            return objectMapper.writeValueAsString(a.getClass());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();



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
