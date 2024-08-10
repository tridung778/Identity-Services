package com.example.identity_services.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dob;
}
