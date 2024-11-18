package com.stackbytes.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterClientContextRequestDto {
    private String serviceName;
    private Integer port;
    private String host;
}
