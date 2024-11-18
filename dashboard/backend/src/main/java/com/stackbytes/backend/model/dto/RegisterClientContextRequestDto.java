package com.stackbytes.backend.model.dto;

import lombok.Data;


@Data
public class RegisterClientContextRequestDto {
    private String serviceName;
    private Integer port;
    private String host;
}
