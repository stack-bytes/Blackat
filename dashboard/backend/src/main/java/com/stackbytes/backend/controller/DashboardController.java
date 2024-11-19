package com.stackbytes.backend.controller;


import com.stackbytes.backend.model.ClientContext;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
