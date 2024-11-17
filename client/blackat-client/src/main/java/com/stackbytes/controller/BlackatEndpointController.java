package com.stackbytes.controller;

import com.stackbytes.model.BlackatContext;
import com.stackbytes.service.BlackatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackat")
public class BlackatEndpointController {


    private final BlackatClientService blackatClientService;

    @Autowired
    BlackatEndpointController(BlackatClientService blackatClientService) {
        this.blackatClientService = blackatClientService;
    }

    @GetMapping("sanity")
    public ResponseEntity<Boolean> sanityCheck(){
        return ResponseEntity.ok(true);
    }

    @GetMapping("context")
    public ResponseEntity<BlackatContext> contextCheck(){
        return ResponseEntity.ok(blackatClientService.getRunningContext());
    }

    @GetMapping("alert-trace")
    public ResponseEntity<String> alertTraceCheck(){
        return ResponseEntity.ok("To be implemented!");
    }

}
