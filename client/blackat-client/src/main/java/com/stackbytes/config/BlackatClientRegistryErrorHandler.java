package com.stackbytes.config;

import com.stackbytes.service.BlackatAlertLevel;
import com.stackbytes.service.BlackatAlertSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;


@Component
public class BlackatClientRegistryErrorHandler implements ResponseErrorHandler  {


    private final BlackatAlertSystem alertSystem;

    @Value("${spring.application.name}")
    private String applicationName;

    public BlackatClientRegistryErrorHandler(BlackatAlertSystem alertSystem) {
        this.alertSystem = alertSystem;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response){
        alertSystem.run(BlackatAlertLevel.LOW, String.format("Client %s could not connect to blackat dashboard", applicationName));
    }

}
