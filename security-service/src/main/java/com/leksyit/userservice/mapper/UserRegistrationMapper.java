package com.leksyit.userservice.mapper;


import com.leksyit.userservice.dto.UserRegistrationDto;
import com.leksyit.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    @Mapping(target = "userRoles", source = "user.roles")
    UserRegistrationDto userToUserRegistrationDto(User user);

    User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);
}
