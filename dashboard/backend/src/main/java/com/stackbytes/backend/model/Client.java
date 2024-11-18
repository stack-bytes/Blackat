package com.stackbytes.backend.model;

import com.stackbytes.backend.model.dto.RegisterClientContextRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Client {
    private RegisterClientContextRequestDto context;
}
