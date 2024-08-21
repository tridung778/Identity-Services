package com.example.identity_services.controllers;

import com.example.identity_services.dto.request.PermissionRequest;
import com.example.identity_services.dto.response.ApiResponse;
import com.example.identity_services.dto.response.PermissionResponse;
import com.example.identity_services.services.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Permission created successfully")
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get permissions successfully")
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permissionName}")
    public ApiResponse<Void> delete(@PathVariable("permissionName") String permissionName) {
        permissionService.delete(permissionName);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Permission deleted successfully")
                .build();
    }

}
