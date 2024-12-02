package com.stackbytes.backend.model;

import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class Client {
    private String clientId;
    private ClientContext context;
    private List<Endpoint> endpoints;
    private String requestBody;
}
