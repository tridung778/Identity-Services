package com.example.identity_services.services;

import com.example.identity_services.dto.request.UserCreationRequest;
import com.example.identity_services.dto.response.UserResponse;
import com.example.identity_services.entities.User;
import com.example.identity_services.enums.Role;
import com.example.identity_services.exceptions.AppException;
import com.example.identity_services.exceptions.ErrorCode;
import com.example.identity_services.mapper.UserMapper;
import com.example.identity_services.repositories.RoleRepository;
import com.example.identity_services.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;


    public User createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);
        return userRepository.save(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public UserResponse getMyInfo() {
        var contextHolder = SecurityContextHolder.getContext();
        String name = contextHolder.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String id, UserCreationRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = roleRepository.findById(request.getRole().toString());
        user.setRole(role.get());
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
