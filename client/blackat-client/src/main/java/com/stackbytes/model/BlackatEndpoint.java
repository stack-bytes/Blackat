package com.stackbytes.model;


import lombok.Data;

import java.util.Map;

@Data
public class BlackatEndpoint {
    private String method;
    private String summary;
    private String url;

    private String jsonFormat;

    private Map<Integer, String> responseCodes;
}
