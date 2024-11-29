package com.stackbytes.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BlackatContext {
    private String serviceName;
    private Integer port;
    private String host;
}
