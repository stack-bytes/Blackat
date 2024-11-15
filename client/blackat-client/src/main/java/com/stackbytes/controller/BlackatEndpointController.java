package com.stackbytes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackat")
public class BlackatEndpointController {

    @GetMapping("sanity")
    public ResponseEntity<Boolean> sanityCheck(){
        return ResponseEntity.ok(true);
    }

}
