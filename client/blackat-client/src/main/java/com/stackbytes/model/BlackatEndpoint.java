package com.stackbytes.model;


import java.util.Map;

public class BlackatEndpoint<T> {
    private String method;
    private String summary;
    private String url;

    private T jsonFormat;

    private Map<Integer, String> responseCodes;
}
