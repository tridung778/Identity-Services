package com.example.identity_services.exceptions;

import com.example.identity_services.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handle(RuntimeException e) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handle(MethodArgumentNotValidException e) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
