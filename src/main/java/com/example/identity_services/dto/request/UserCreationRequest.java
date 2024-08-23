package com.example.identity_services.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.identity_services.enums.Role;
import com.example.identity_services.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    String firstname;
    String lastname;

    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;

    Role role;
}
