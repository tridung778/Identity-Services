package com.example.identity_services.services;

import com.example.identity_services.dto.request.AuthenticationRequest;
import com.example.identity_services.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public boolean authenticate(AuthenticationRequest username) {
        var user = userRepository.findByUsername(username.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(username.getPassword(), user.getPassword());
    }
}
