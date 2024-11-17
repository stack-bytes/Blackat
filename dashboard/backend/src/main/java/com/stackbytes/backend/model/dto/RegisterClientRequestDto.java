package com.stackbytes.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterClientRequestDto {
    private String serviceName;
    private Integer port;
    private String host;
}
