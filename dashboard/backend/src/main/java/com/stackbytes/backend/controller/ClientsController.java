package com.stackbytes.backend.controller;

import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import com.stackbytes.backend.model.dto.RegisterClientContextResponseDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<RegisterClientContextResponseDto> registerClientContext(@RequestBody RegisterClientContextRequestDto registerClientContextRequestDto) throws UnknownHostException {
        return ResponseEntity.ok(clientsService.registerClientContext(registerClientContextRequestDto));
    }

}
