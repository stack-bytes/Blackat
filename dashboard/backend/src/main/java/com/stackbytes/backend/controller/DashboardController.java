package com.stackbytes.backend.controller;


import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/data")
public class DashboardController {

    private final ClientsService clientsService;

    public DashboardController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @GetMapping("/contexts")
    public ResponseEntity<RegisterClientContextRequestDto> getClientsContext() {
        return ResponseEntity.ok(clientsService.getClientsContext());
    }

}
