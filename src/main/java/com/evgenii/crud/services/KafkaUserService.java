package com.evgenii.crud.services;

import com.evgenii.crud.dto.UserDto;

public interface KafkaUserService {
    void send(UserDto dto);

    void consume(UserDto dto);
}
