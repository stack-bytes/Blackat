package com.stackbytes.backend.service;

import com.stackbytes.backend.components.ServiceRegistry;
import com.stackbytes.backend.model.Client;
import com.stackbytes.backend.model.ClientContext;
import com.stackbytes.backend.model.Endpoint;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.model.dto.RegisterClientContextResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class ClientsService {

    private final ServiceRegistry serviceRegistry;



    @Autowired
    public ClientsService(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    public RegisterClientContextResponseDto registerClientContext(RegisterClientContextRequestDto registerClientContextRequestDto) throws UnknownHostException {

        RegisterClientContextResponseDto registerClientContextResponseDto = RegisterClientContextResponseDto.builder()
                        .id(serviceRegistry.registerClient(registerClientContextRequestDto))
                        .build();


        return registerClientContextResponseDto;
    }

    public List<ClientContext> getClientsContext() {
        return serviceRegistry.getClientContexts();
    }

    public Boolean addMethodsToClient(String clientId, List<Endpoint> endpoints) {
        return serviceRegistry.addClientInfo(clientId, endpoints);
    }

    public List<Endpoint> getClientEndpoints(String clientId) {
        return serviceRegistry.getEndpointsForClient(clientId);
    }

    public List<Client> getClients() {
        return serviceRegistry.getAllClients();
    }
}
