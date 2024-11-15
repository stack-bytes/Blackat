package com.stackbytes.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BlackatService {
    private String name;
    private String description;
    private String version;

    private Map<String, List<BlackatEndpoint>> endpoints; // For each path it creates more entities for each method
}
