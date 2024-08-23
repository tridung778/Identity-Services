package com.example.identity_services.services;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.example.identity_services.dto.request.UserCreationRequest;
import com.example.identity_services.dto.response.UserResponse;
import com.example.identity_services.entities.User;
import com.example.identity_services.exceptions.AppException;
import com.example.identity_services.exceptions.ErrorCode;
import com.example.identity_services.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2000, 1, 1);

        userCreationRequest = UserCreationRequest.builder()
                .username("test")
                .firstname("test")
                .lastname("test")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("c79c296a5ad7")
                .username("test")
                .firstname("test")
                .lastname("test")
                .dob(dob)
                .build();

        user = User.builder()
                .id("c79c296a5ad7")
                .username("test")
                .firstname("test")
                .lastname("test")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.any())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        var response = userService.createUser(userCreationRequest);
        Assertions.assertEquals(response.getId(), user.getId());
        Assertions.assertEquals(response.getUsername(), user.getUsername());
        Assertions.assertEquals(response.getFirstname(), user.getFirstname());
        Assertions.assertEquals(response.getLastname(), user.getLastname());
        Assertions.assertEquals(response.getDob(), user.getDob());
    }

    @Test
    void createUser_userExisted_fail() {
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.any())).thenReturn(true);

        var exception = Assertions.assertThrows(AppException.class, () -> {
            userService.createUser(userCreationRequest);
        });

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.USER_EXISTED);
    }

    @Test
    @WithMockUser(username = "test")
    void getMyInfo_valid_success() {
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.any())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertEquals(response, userResponse);
    }

    @Test
    @WithMockUser(username = "test")
    void getMyInfo_userNotExist_fail() {
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.any())).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(AppException.class, () -> {
            userService.getMyInfo();
        });

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.USER_NOT_EXISTED);
    }
}
