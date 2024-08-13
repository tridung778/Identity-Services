package com.example.identity_services.mapper;

import com.example.identity_services.dto.request.PermissionRequest;
import com.example.identity_services.dto.response.PermissionResponse;
import com.example.identity_services.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
