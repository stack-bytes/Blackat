package com.stackbytes.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlackatContext {
    private String serviceName;
    private Integer port;
    private String host;
}