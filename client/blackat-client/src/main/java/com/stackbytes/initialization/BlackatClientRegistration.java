package com.stackbytes.initialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackbytes.config.BlackatClientRegistryRestTemplate;
import com.stackbytes.model.BlackatContext;
import com.stackbytes.service.BlackatAlertLevel;
import com.stackbytes.service.BlackatAlertSystem;
import com.stackbytes.service.BlackatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BlackatClientRegistration {


    private final RestTemplate restTemplate;
    private final BlackatAlertSystem blackatAlertSystem;
    private final BlackatClientService blackatClientService;
    private final ObjectMapper objectMapper;

    @Value("${blackat.server.host}")
    private String dashboardConnectionString;

    @Value("${blackat.server.port}")
    private Integer dashboardPort;



    @Autowired
    public BlackatClientRegistration(RestTemplate restTemplate, BlackatAlertSystem blackatAlertSystem, ObjectMapper objectMapper, BlackatClientService blackatClientService) {
        this.restTemplate = restTemplate;
        this.blackatAlertSystem = blackatAlertSystem;
        this.objectMapper = objectMapper;
        this.blackatClientService = blackatClientService;
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    private void registerClientWithDashboard(){
        //TODO: Enum indexing of dashboard endpoints
        String registerClientUrl = String.format("http://%s:%d/register", dashboardConnectionString, dashboardPort);


        //TODO:Tidy up
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            BlackatContext serviceContext = blackatClientService.getRunningContext();
            System.out.println(objectMapper.writeValueAsString(serviceContext));

            String jsonPayload = objectMapper.writeValueAsString("");

            HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);
            restTemplate.postForObject(registerClientUrl, entity, String.class);
        } catch (Exception e) {
            blackatAlertSystem.run(BlackatAlertLevel.LOW, String.format("Could not register client <%s> to dashboard due to connection issues: %s", applicationName, e.getMessage()));
        }


    }
}
