package com.example.identity_services.mapper;

import org.mapstruct.Mapper;

import com.example.identity_services.dto.request.PermissionRequest;
import com.example.identity_services.dto.response.PermissionResponse;
import com.example.identity_services.entities.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
