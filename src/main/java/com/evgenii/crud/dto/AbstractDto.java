package com.evgenii.crud.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
public class AbstractDto {

    String message;

    Instant messageDate;
}
