package com.evgenii.crud.services.mapper;


import com.evgenii.crud.dto.UserDto;
import com.evgenii.crud.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User updateUser(UserDto from, @MappingTarget User to);
}
