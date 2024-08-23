package com.example.identity_services.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.identity_services.dto.request.UserCreationRequest;
import com.example.identity_services.dto.response.ApiResponse;
import com.example.identity_services.dto.response.UserResponse;
import com.example.identity_services.services.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping()
    public ApiResponse<UserResponse> createUser(@RequestBody @Validated UserCreationRequest request) {
        log.info("Controller: create user");
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());

        return ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get users successfully")
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Get user successfully");
        apiResponse.setResult(userService.getUser(id));
        return apiResponse;
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get info successfully")
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id) {

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("User deleted successfully");
        userService.deleteUser(id);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserCreationRequest request) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("User updated successfully");
        apiResponse.setResult(userService.updateUser(id, request));
        return apiResponse;
    }
}
