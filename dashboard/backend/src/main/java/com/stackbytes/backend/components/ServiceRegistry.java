package com.stackbytes.backend.components;

import com.stackbytes.backend.model.Client;
import com.stackbytes.backend.model.ClientContext;
import com.stackbytes.backend.model.Endpoint;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@Component
public class ServiceRegistry {
    private HashMap<String, Client> registeredClients = new HashMap<>();


    private final RestTemplate restTemplate;

    public ServiceRegistry(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String registerClient(RegisterClientContextRequestDto registerClientContextRequestDto) throws UnknownHostException {

        //TODO: validation logic
        //TODO: dockerized logic

        String key = UUID.randomUUID().toString();
        Client client = Client.builder()
                .context(new ClientContext(
                        key,
                        registerClientContextRequestDto.getServiceName(),
                        registerClientContextRequestDto.getPort(),
                        registerClientContextRequestDto.getHost(),
                        Inet4Address.getLocalHost().getHostAddress().equals(registerClientContextRequestDto.getHost()),
                        String.format("http://%s:%d", registerClientContextRequestDto.getHost(), registerClientContextRequestDto.getPort())
                        )
                )
                .clientId(key)
                .endpoints(new ArrayList<>())
                .build();


        registeredClients.put(key, client);

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

    public List<Endpoint> getEndpointsForClient(String clientId){
        return registeredClients.get(clientId).getEndpoints();
    }



    public void sanityEvict(){
        for (Iterator<Map.Entry<String, Client>> iterator = registeredClients.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Client> entry = iterator.next();
            Client client = entry.getValue();
            try {
                restTemplate.getForObject(String.format("%s/blackat/sanity", client.getContext().getUrl()), Boolean.class);
            } catch (Exception e) {
                iterator.remove();
            }
        }
    }


    public List<ClientContext> getClientContexts() {


        List<ClientContext> clientContexts = new ArrayList<>();
        for(Client client : registeredClients.values()) {
            clientContexts.add(client.getContext());
        }

        return clientContexts;
    }

    public List<Client> getAllClients() {
        sanityEvict();

        if(registeredClients.isEmpty()) {return null;}
        List<Client> clients = new ArrayList<>(registeredClients.values());

        return clients;
    }
}
