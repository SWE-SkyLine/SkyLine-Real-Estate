package com.example.SkyLine;

import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserOauthRepository;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.RegesterationService;
import com.example.SkyLine.utility.RepositoryFactory;
import com.example.SkyLine.utility.UserFactory;
import com.example.SkyLine.utility.VerificationCodeGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.SkyLine.enums.UserRoleEnum;


import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegesterationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserOauthRepository userOauthRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @Mock
    private UserFactory userFactory;

    @Mock
    private RepositoryFactory repositoryFactory;

    @InjectMocks
    private RegesterationService registrationService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testUserVerify() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setVerificationCode("123456");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertTrue(registrationService.UserVerify("test@example.com", "123456"));

        assertTrue(user.getIsEnable());
        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSignInOauth() {
        when(userOauthRepository.existsById(any(String.class))).thenReturn(true);

        assertTrue(registrationService.signInOauth("test@example.com"));

        verify(userOauthRepository, times(1)).existsById("test@example.com");
    }
}
