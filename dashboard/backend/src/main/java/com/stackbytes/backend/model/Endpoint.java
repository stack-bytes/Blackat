package com.stackbytes.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endpoint {
    private String method;
    private String name;
    private String url;
    private List<Param> parameters;
}
