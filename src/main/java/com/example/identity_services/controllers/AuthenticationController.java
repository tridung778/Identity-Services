package com.example.identity_services.controllers;

import com.example.identity_services.dto.request.ApiResponse;
import com.example.identity_services.dto.request.AuthenticationRequest;
import com.example.identity_services.dto.request.IntrospectRequest;
import com.example.identity_services.dto.response.AuthenticationResponse;
import com.example.identity_services.dto.response.IntrospectResponse;
import com.example.identity_services.services.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(HttpStatus.OK.value())
                .message(result.isAuthenticated() ? "User authenticated successfully" : "User authentication failed")
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(HttpStatus.OK.value())
                .message(result.isValid() ? "Token is valid" : "Token is invalid")
                .result(result)
                .build();
    }
}
