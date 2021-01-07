package com.evgenii.crud.controllers;

import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto getUser(@RequestParam UUID userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public UserDto createUser(@RequestParam UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestParam UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam UUID userId) {
        userService.deleteUser(userId);
    }
}
