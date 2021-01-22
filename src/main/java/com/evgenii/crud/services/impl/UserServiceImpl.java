package com.evgenii.crud.services.impl;


import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.entities.User;
import com.evgenii.crud.repos.UserRepository;
import com.evgenii.crud.services.UserService;
import com.evgenii.crud.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(UUID userId) {
        return userMapper.toDto(userRepository.getOne(userId));
    }

    @Override
    public UserDto createUser(UserDto newUser) {
        User createdUser = userRepository.save(userMapper.toEntity(newUser));
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto newUser) {
        User userToUpdate = userRepository.getOne(userId);
        User updatedUser = userRepository.save(userMapper.updateUser(newUser, userToUpdate));
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
