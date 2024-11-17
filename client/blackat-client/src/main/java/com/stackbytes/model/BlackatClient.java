package com.stackbytes.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BlackatClient {
    private String name;
    private String description;
    private String version;

    private String id; // Service ID that is persistent
    
    /*  Endpoint - Method Description */
    private Map<String, List<BlackatEndpoint>> endpoints; // For each path it creates more entities for each method
}
