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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController.setPasswordEncoder(passwordEncoder);
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

        // Mock the behavior of the userRepository.findByEmail method
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        // Mock the behavior of the emailService.sendEmail method to throw an exception
        doThrow(new RuntimeException("Simulated email sending failure"))
                .when(emailService).sendEmail(anyString(), anyString(), anyString());


        // Call the method that you want to test
        ResponseEntity<String> result = userController.sendVerificationCode(emailRequest);

        // Verify the result
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Failed to send email", result.getBody());

        // Verify that the emailService.sendEmail method was called with the expected parameters
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void sendVerificationCodeUserNotFound() {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmail("nonexistent@example.com");

        // Mock the behavior of the userRepository.findByEmail method for a non-existent user
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Call the method that you want to test
        ResponseEntity<String> result = userController.sendVerificationCode(emailRequest);

        // Verify the result
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("User not found", result.getBody());

        // Verify that the emailService.sendEmail method was not called
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void updatePasswordSuccess() {
        // Create an UpdatePasswordRequest with test data
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("test@example.com");
        updatePasswordRequest.setNewPassword("newPassword123");

        // Create a mock user with the given email
        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        // Mock the behavior of userRepository.findByEmail to return the mock user
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        // Mock the behavior of userRepository.save to return the mock user (or any other appropriate behavior)
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Mock the behavior of passwordEncoder.encode to return the encoded password
        when(passwordEncoder.encode("newPassword123")).thenReturn("encodedPassword123");

        // Call the method that you want to test
        ResponseEntity<String> result = userController.updatePassword(updatePasswordRequest);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("encodedPassword123", result.getBody());

        // Verify that the passwordEncoder.encode method was called once
        verify(passwordEncoder, times(1)).encode("newPassword123");

        // Verify that userRepository.save was called once with the updated user
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void updatePasswordUserNotFound() {
        // Create an UpdatePasswordRequest with test data
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("nonexistent@example.com");
        updatePasswordRequest.setNewPassword("newPassword123");

        // Mock the behavior of userRepository.findByEmail for a non-existent user
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Call the method that you want to test
        ResponseEntity<String> result = userController.updatePassword(updatePasswordRequest);

        // Verify the result
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("User not found", result.getBody());

        // Verify that passwordEncoder.encode and userRepository.save were not called
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updatePasswordException() {
        // Create an UpdatePasswordRequest with test data
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("test@example.com");
        updatePasswordRequest.setNewPassword("newPassword123");

        // Create a mock user with the given email
        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        // Mock the behavior of userRepository.findByEmail to return the mock user
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        // Mock the behavior of userRepository.save to throw an exception
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Simulated save failure"));

        // Mock the behavior of passwordEncoder.encode to return the encoded password
        when(passwordEncoder.encode("newPassword123")).thenReturn("encodedPassword123");

        // Call the method that you want to test
        ResponseEntity<String> result = userController.updatePassword(updatePasswordRequest);

        // Verify the result
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Password update failed", result.getBody());

        // Verify that the passwordEncoder.encode method was called once
        verify(passwordEncoder, times(1)).encode("newPassword123");

        // Verify that userRepository.save was called once with the updated user
        verify(userRepository, times(1)).save(mockUser);
    }





    @Test
    public void testVerifyCode() {
        // Create a test user
        User testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setVerificationCodeForgetPassword(1234);

        // Mock the behavior of the userRepository.findByEmail method
        when(userRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // Call the verifyCode method with the test parameters
        ResponseEntity<String> response = userController.verifyCode("test@example.com", 1234);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Verification successful", response.getBody());

        // Call the verifyCode method with incorrect code
        response = userController.verifyCode("test@example.com", 5678);

        // Verify the response for incorrect code
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect verification code", response.getBody());

        // Call the verifyCode method with non-existent user
        response = userController.verifyCode("nonexistent@example.com", 1234);

        // Verify the response for non-existent user
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // You can add more test cases as needed
    }

    @Test
    void verifyCodeVerificationSuccessful() {
        // Mock data
        String userEmail = "test@example.com";
        int storedCode = 1234;
        int providedCode = 1234;

        // Create a test user
        User testUser = new User();
        testUser.setEmail(userEmail);
        testUser.setVerificationCodeForgetPassword(storedCode);

        // Mock the behavior of the userRepository.findByEmail method
        when(userRepository.findByEmail(userEmail)).thenReturn(testUser);

        // Call the verifyCode method with the test parameters
        ResponseEntity<String> response = userController.verifyCode(userEmail, providedCode);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Verification successful", response.getBody());

        // Verify interactions with userRepository
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

    @Test
    void verifyCodeIncorrectVerificationCode() {
        // Mock data
        String userEmail = "test@example.com";
        int storedCode = 1234;
        int providedCode = 5678;

        // Create a test user
        User testUser = new User();
        testUser.setEmail(userEmail);
        testUser.setVerificationCodeForgetPassword(storedCode);

        // Mock the behavior of the userRepository.findByEmail method
        when(userRepository.findByEmail(userEmail)).thenReturn(testUser);

        // Call the verifyCode method with the test parameters
        ResponseEntity<String> response = userController.verifyCode(userEmail, providedCode);

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect verification code", response.getBody());

        // Verify interactions with userRepository
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

    @Test
    void verifyCodeUserNotFound() {
        // Mock data
        String userEmail = "nonexistent@example.com";
        int providedCode = 1234;

        // Mock the behavior of the userRepository.findByEmail method for a non-existent user
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the verifyCode method with the test parameters
        ResponseEntity<String> response = userController.verifyCode(userEmail, providedCode);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // Verify interactions with userRepository
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

}
