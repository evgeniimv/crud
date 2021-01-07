package com.evgenii.crud.service;


import com.evgenii.crud.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto getUser(UUID userId);

    UserDto createUser(UserDto newUser);

    UserDto updateUser(UserDto newUser);

    void deleteUser(UUID userId);
}
