package com.stackbytes.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;



@Builder
@Data
public class BlackatEndpoint {
    private String method;
    private String name;
    private String url;
    private List<Param> parameters;
    private String requestBody;
}
