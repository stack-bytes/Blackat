package com.stackbytes.initialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackbytes.config.BlackatClientRegistryRestTemplate;
import com.stackbytes.config.BlackatClientVariables;
import com.stackbytes.model.BlackatContext;
import com.stackbytes.model.BlackatEndpoint;
import com.stackbytes.model.dto.RegisterClientContextResponseDto;
import com.stackbytes.service.BlackatAlertLevel;
import com.stackbytes.service.BlackatAlertSystem;
import com.stackbytes.service.BlackatClientService;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BlackatClientRegistration {


    private final RestTemplate restTemplate;
    private final BlackatAlertSystem blackatAlertSystem;
    private final BlackatClientService blackatClientService;
    private final ObjectMapper objectMapper;
    private final BlackatClientVariables blackatClientVariables;

    @Value("${blackat.server.host}")
    private String dashboardConnectionString;

    @Value("${blackat.server.port}")
    private Integer dashboardPort;





    @Autowired
    public BlackatClientRegistration(RestTemplate restTemplate, BlackatAlertSystem blackatAlertSystem, ObjectMapper objectMapper, BlackatClientService blackatClientService, BlackatClientVariables blackatClientVariables) {
        this.restTemplate = restTemplate;
        this.blackatAlertSystem = blackatAlertSystem;
        this.objectMapper = objectMapper;
        this.blackatClientService = blackatClientService;
        this.blackatClientVariables = blackatClientVariables;
    }

    @Value("${spring.application.name}")
    private String applicationName;

    private void sendClientMethods(String clientId) throws JsonProcessingException {
        String postClientMethodsUrl = String.format("http://%s:%d/clients/context?clientId=%s", dashboardConnectionString, dashboardPort, clientId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BlackatEndpoint> endpoints = blackatClientService.getEndpoints(clientId);

        String jsonPayload = objectMapper.writeValueAsString(endpoints);



        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> jsonStringResponseEntity = restTemplate.postForEntity(
                postClientMethodsUrl,
                entity,
                String.class
        );
    }

    @Bean
    private void registerClientWithDashboard() {
        //TODO: Enum indexing of dashboard endpoints

        if(blackatClientVariables.getId() != null) return;

        String registerClientUrl = String.format("http://%s:%d/clients/register", dashboardConnectionString, dashboardPort);


        //TODO:Tidy up


        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            BlackatContext serviceContext = blackatClientService.getRunningContext();

            String jsonPayload = objectMapper.writeValueAsString(serviceContext);


            HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

            ResponseEntity<String> jsonStringResponseEntity = restTemplate.postForEntity(
                    registerClientUrl,
                    entity,
                    String.class
            );



            RegisterClientContextResponseDto  registerClientContextResponseDto = objectMapper.readValue(jsonStringResponseEntity.getBody(), RegisterClientContextResponseDto.class);

            blackatClientVariables.setId(registerClientContextResponseDto.getId());

            sendClientMethods(blackatClientVariables.getId());
        } catch (Exception e) {
            blackatAlertSystem.run(BlackatAlertLevel.LOW, String.format("Could not register client <%s> to dashboard due error: %s", applicationName, e.getMessage()));
        }


    }

    @PreDestroy
    private void closeService(){
        restTemplate.delete(String.format("http://%s:%d/clients/unregister?clientId=%s", dashboardConnectionString, dashboardPort, blackatClientVariables.getId()));
    }
}
