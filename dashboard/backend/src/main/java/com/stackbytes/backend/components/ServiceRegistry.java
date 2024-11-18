package com.stackbytes.backend.components;

import com.stackbytes.backend.model.Client;
import com.stackbytes.backend.model.ClientContext;
import com.stackbytes.backend.model.Endpoint;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class ServiceRegistry {
    private HashMap<String, Client> registeredClients = new HashMap<>();

    public String registerClient(RegisterClientContextRequestDto registerClientContextRequestDto) throws UnknownHostException {

        //TODO: validation logic

        String key = UUID.randomUUID().toString();
        Client client = Client.builder()
                .context(new ClientContext(
                        registerClientContextRequestDto.getServiceName(),
                        registerClientContextRequestDto.getPort(),
                        registerClientContextRequestDto.getHost(),
                        Inet4Address.getLocalHost().getHostAddress().equals(registerClientContextRequestDto.getHost())
                ))
                .build();

        registeredClients.put(key, client);

        printRegistry();
        return key;
    }

    public boolean addClientInfo(String clientId,  List<Endpoint> endpoints){
        if(!registeredClients.containsKey(clientId))
            return false;

        registeredClients.get(clientId).getEndpoints().addAll(endpoints);

        System.out.println(registeredClients.get(clientId).getEndpoints());

        return true;
    }

    public void printRegistry(){
        for(Client client : registeredClients.values()) {
            System.out.println(client.getContext().toString());
        }
    }


}
