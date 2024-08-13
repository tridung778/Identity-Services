package com.example.identity_services.mapper;

import com.example.identity_services.dto.request.PermissionRequest;
import com.example.identity_services.dto.request.RoleRequest;
import com.example.identity_services.dto.response.PermissionResponse;
import com.example.identity_services.dto.response.RoleResponse;
import com.example.identity_services.entities.Permission;
import com.example.identity_services.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
