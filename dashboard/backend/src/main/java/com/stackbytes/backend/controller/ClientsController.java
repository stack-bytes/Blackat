package com.stackbytes.backend.controller;

import com.stackbytes.backend.model.dto.RegisterClientRequestDto;
import com.stackbytes.backend.model.dto.RegisterClientResponseDto;
import com.stackbytes.backend.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ClientsController {

    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<String> registerClientContext(@RequestBody RegisterClientRequestDto registerClientRequestDto) {
        //clientsService.registerClientContext(registerClientRequestDto);
        System.out.println("RegisterClientRequestDto: " + registerClientRequestDto.getHost());
        return ResponseEntity.ok("Client succesfully registered");
    }

}
