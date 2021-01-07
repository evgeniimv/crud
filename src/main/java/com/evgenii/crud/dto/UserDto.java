package com.evgenii.crud.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserDto {
    private UUID id;
    private String name;
    private String email;
}
