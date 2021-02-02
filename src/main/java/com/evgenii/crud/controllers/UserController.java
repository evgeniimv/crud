package com.evgenii.crud.controllers;

import com.evgenii.crud.aspect.Auditable;
import com.evgenii.crud.aspect.EventCode;
import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.services.AuthorizationService;
import com.evgenii.crud.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{userId}")
    @Auditable(value = EventCode.CRUD_USERS_GET_BY_ID, params = {"userId"})
    public UserDto getUser(@PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    @Auditable(value = EventCode.CRUD_USERS_GET_ALL)
    public List<UserDto> getAllUser() {
//        final String authorizationResponse = authorizationService.checkAuthorization("Some user");
//        if (authorizationResponse == null) {
//            return userService.getAllUsers();
//        }
        return userService.getAllUsers();
    }

    @PostMapping
    @Auditable(value = EventCode.CRUD_USERS_CREATE, params = {"userDto:name", "userDto:email"})
    public UserDto createUser(@RequestBody UserDto userDto) {
        final Instant a = Instant.now();
        final UserDto user = userService.createUser(userDto);
        final Instant b = Instant.now();
        System.out.println("TIME: " + (b.toEpochMilli() - a.toEpochMilli()));
        return user;
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable UUID userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}
