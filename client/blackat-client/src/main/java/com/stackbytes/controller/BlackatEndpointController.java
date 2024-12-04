package com.stackbytes.controller;

import com.stackbytes.annotations.BlackatOmmitEndpoint;
import com.stackbytes.model.BlackatContext;
import com.stackbytes.model.BlackatEndpoint;
import com.stackbytes.service.BlackatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackat")
public class BlackatEndpointController {


    private final BlackatClientService blackatClientService;

    @Autowired
    BlackatEndpointController(BlackatClientService blackatClientService) {
        this.blackatClientService = blackatClientService;
    }

    //TODO: Secure CORS

    @BlackatOmmitEndpoint
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<String> blackat(){
        return ResponseEntity.ok("Blackat up and running");
    }

    @BlackatOmmitEndpoint
    @CrossOrigin
    @GetMapping("sanity")
    public ResponseEntity<Boolean> sanityCheck(){
        return ResponseEntity.ok(true);
    }

    @BlackatOmmitEndpoint
    @CrossOrigin
    @GetMapping("context")
    public ResponseEntity<BlackatContext> contextCheck(){
        return ResponseEntity.ok(blackatClientService.getRunningContext());
    }


    @CrossOrigin
    @GetMapping("methods")
    public ResponseEntity<List<BlackatEndpoint>>  methodsCheck(@RequestParam String clientId){
        return ResponseEntity.ok(blackatClientService.getEndpoints(clientId));
    }

    @BlackatOmmitEndpoint
    @CrossOrigin
    @GetMapping("alert-trace")
    public ResponseEntity<String> alertTraceCheck(){
        return ResponseEntity.ok("To be implemented!");
    }

}
