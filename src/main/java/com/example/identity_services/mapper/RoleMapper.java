package com.example.identity_services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.identity_services.dto.request.RoleRequest;
import com.example.identity_services.dto.response.RoleResponse;
import com.example.identity_services.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
