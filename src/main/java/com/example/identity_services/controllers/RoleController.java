package com.example.identity_services.controllers;

import com.example.identity_services.dto.request.RoleRequest;
import com.example.identity_services.dto.response.ApiResponse;
import com.example.identity_services.dto.response.RoleResponse;
import com.example.identity_services.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()

                .code(HttpStatus.OK.value())
                .message("Role created successfully")
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get roles successfully")
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{roleName}")
    public ApiResponse<Void> delete(@PathVariable("roleName") String roleName) {
        roleService.delete(roleName);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }
}
