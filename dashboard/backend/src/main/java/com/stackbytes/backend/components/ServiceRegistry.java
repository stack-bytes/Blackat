package com.stackbytes.backend.components;

import com.stackbytes.backend.model.Client;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class ServiceRegistry {
    private HashMap<String, Client> registeredClients = new HashMap<>();

    public String registerClient(RegisterClientContextRequestDto registerClientContextRequestDto) {

        //TODO: validation logic

        String key = UUID.randomUUID().toString();
        Client client = Client.builder()
                        .context(registerClientContextRequestDto)
                        .build();

        registeredClients.put(key, client);

        return key;
    }
}
