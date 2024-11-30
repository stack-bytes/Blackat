package com.stackbytes.backend.controller;

import com.stackbytes.backend.model.Endpoint;
import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.model.dto.RegisterClientContextResponseDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    //TODO:Get request param name is provided in annotation
    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<RegisterClientContextResponseDto> registerClientContext(@RequestBody RegisterClientContextRequestDto registerClientContextRequestDto) throws UnknownHostException {
        System.out.println(registerClientContextRequestDto);
        return ResponseEntity.ok(clientsService.registerClientContext(registerClientContextRequestDto));
    }

    @CrossOrigin
    @PostMapping("context")
    public ResponseEntity<Boolean> addClientContext(@RequestParam String clientId, @RequestBody List<Endpoint> endpoints) throws UnknownHostException {
        return ResponseEntity.ok(clientsService.addMethodsToClient(clientId, endpoints));
    }

    @CrossOrigin
    @DeleteMapping("unregister")
    public ResponseEntity<Boolean> unregisterClientContext(@RequestParam String clientId) throws UnknownHostException {
        return ResponseEntity.ok(clientsService.unregisterClient(clientId));
    }

}
