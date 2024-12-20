package com.stackbytes.backend.model;

import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
public class ClientContext  {
    private String clientId;
    private String serviceName;
    private Integer port;
    private String host;
    private Boolean sameIp;
    private String url;

}
