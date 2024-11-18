package com.stackbytes.backend.service;

import com.stackbytes.backend.components.ServiceRegistry;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.model.dto.RegisterClientContextResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private final ServiceRegistry serviceRegistry;

    @Autowired
    public ClientsService(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    public RegisterClientContextResponseDto registerClientContext(RegisterClientContextRequestDto registerClientContextRequestDto) {

        RegisterClientContextResponseDto registerClientContextResponseDto = RegisterClientContextResponseDto.builder()
                        .id(serviceRegistry.registerClient(registerClientContextRequestDto))
                        .build();


        return registerClientContextResponseDto;
    }
}
