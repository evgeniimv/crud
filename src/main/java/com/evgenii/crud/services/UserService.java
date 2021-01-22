package com.evgenii.crud.services;


import com.evgenii.crud.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto getUser(UUID userId);

    UserDto createUser(UserDto newUser);

    UserDto updateUser(UUID userId, UserDto newUser);

    void deleteUser(UUID userId);

    List<UserDto> getAllUser();
}
