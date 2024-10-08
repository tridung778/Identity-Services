package com.example.identity_services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.identity_services.dto.request.UserCreationRequest;
import com.example.identity_services.dto.response.UserResponse;
import com.example.identity_services.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget User user, UserCreationRequest request);
}
