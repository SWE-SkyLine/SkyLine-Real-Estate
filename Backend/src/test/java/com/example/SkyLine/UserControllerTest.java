package com.example.SkyLine;

import com.example.SkyLine.controller.UserController;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.model.EmailRequest;
import com.example.SkyLine.model.UpdatePasswordRequest;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<User> mockUsers = Collections.singletonList(new User());
        when(userService.getAllUsers()).thenReturn(mockUsers);

        List<User> result = userController.getAllUsers();

        assertEquals(mockUsers, result);
    }

    @Test
    void sendVerificationCode() {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmail("test@example.com");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        ResponseEntity<String> result = userController.sendVerificationCode(emailRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Email sent successfully", result.getBody());
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }



    // Additional tests for other methods can be added similarly.
}
