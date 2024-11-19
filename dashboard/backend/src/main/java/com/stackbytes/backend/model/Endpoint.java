package com.stackbytes.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endpoint {
    private String method;
    private String summary;
    private String url;
}
