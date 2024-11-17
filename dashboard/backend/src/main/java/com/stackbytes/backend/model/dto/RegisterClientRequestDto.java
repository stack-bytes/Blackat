package com.stackbytes.backend.model.dto;

import lombok.Data;

@Data
public class RegisterClientRequestDto {
    private String serviceName;
    private Integer port;
    private String host;
}
