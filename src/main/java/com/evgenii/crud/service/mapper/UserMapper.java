package com.evgenii.crud.service.mapper;


import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    User updateUser(UserDto newUser, @MappingTarget User userToUpdate);
}
