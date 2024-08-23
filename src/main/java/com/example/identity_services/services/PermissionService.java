package com.example.identity_services.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.identity_services.dto.request.PermissionRequest;
import com.example.identity_services.dto.response.PermissionResponse;
import com.example.identity_services.entities.Permission;
import com.example.identity_services.mapper.PermissionMapper;
import com.example.identity_services.repositories.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionRepository.save(permissionMapper.toPermission(request));
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void delete(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }
}
