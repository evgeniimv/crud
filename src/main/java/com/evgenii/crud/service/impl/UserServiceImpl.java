package com.evgenii.crud.service.impl;


import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.entity.User;
import com.evgenii.crud.repository.UserRepository;
import com.evgenii.crud.service.UserService;
import com.evgenii.crud.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(UUID userId) {
        return userMapper.toDto(userRepository.getOne(userId));
    }

    @Override
    public UserDto createUser(UserDto newUser) {
        User createdUser = userRepository.save(userMapper.toEntity(newUser));
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto newUser) {
        User userToUpdate = userRepository.getOne(newUser.getId());
        User updatedUser = userRepository.save(userMapper.updateUser(newUser, userToUpdate));
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
