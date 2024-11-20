package com.stackbytes.backend.controller;


import com.stackbytes.backend.model.Client;
import com.stackbytes.backend.model.ClientContext;
import com.stackbytes.backend.model.Endpoint;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/dashboard")
public class DashboardController {

    private final ClientsService clientsService;

    public DashboardController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Dashboard controllers up and running!");
    }

    @CrossOrigin
    @GetMapping("/contexts")
    public ResponseEntity<List<ClientContext>> getClientsContext() {
        return ResponseEntity.ok(clientsService.getClientsContext());
    }

    @CrossOrigin
    @GetMapping("/sanity")
    public ResponseEntity<Boolean> sanityCheck(){
        return ResponseEntity.ok(true);
    }

    @CrossOrigin
    @GetMapping("/methods")
    public ResponseEntity<List<Endpoint>> getClientEndpoints(@RequestParam String clientId) {
        return ResponseEntity.ok(clientsService.getClientEndpoints(clientId));
    }

    @CrossOrigin
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientsService.getClients());
    }

}
