package com.example.identity_services.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "Username must be at least 3 characters long")
    String username;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
    String firstname;
    String lastname;
    LocalDate dob;
}
