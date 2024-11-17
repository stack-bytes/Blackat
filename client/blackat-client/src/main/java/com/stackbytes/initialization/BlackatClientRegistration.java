package com.stackbytes.initialization;

import com.stackbytes.config.BlackatClientRegistryRestTemplate;
import com.stackbytes.service.BlackatAlertLevel;
import com.stackbytes.service.BlackatAlertSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
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

    @Value("${blackat.server.host}")
    private String dashboardConnectionString;

    @Value("${blackat.server.port}")
    private Integer dashboardPort;

    @Autowired
    public BlackatClientRegistration(RestTemplate restTemplate, BlackatAlertSystem blackatAlertSystem) {
        this.restTemplate = restTemplate;
        this.blackatAlertSystem = blackatAlertSystem;
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    private void registerClientWithDashboard(){
        //TODO: Enum indexing of dashboard endpoints
        String registerClientUrl = String.format("http://%s:%d/register", dashboardConnectionString, dashboardPort);


        //TODO:Tidy up
        try {
            restTemplate.postForObject(registerClientUrl, "Hello world", String.class);
        } catch (Exception e) {
            blackatAlertSystem.run(BlackatAlertLevel.LOW, String.format("Could not register client <%s> to dashboard due to connection issues: %s", applicationName, e.getMessage()));
        }


    }
}
