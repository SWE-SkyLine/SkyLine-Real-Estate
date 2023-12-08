package com.example.SkyLine;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.controller.RegisterationController;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.service.RegesterationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegisterationControllerTest {

    @Mock
    private RegesterationService regesterationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private RegisterationController registerationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(registerationController).build();
    }

    @Test
    void signUp() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password");

        User mockUser = new User(); // Change this line based on your actual User entity
        mockUser.setEmail("test@example.com");

        when(regesterationService.userExists("test@example.com")).thenReturn(false);
        when(regesterationService.register(any(UserRequestDTO.class))).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/register/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"password\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void signIn() throws Exception {
        LogInRequestDTO logInRequestDTO = new LogInRequestDTO();
        logInRequestDTO.setEmail("test@example.com");
        logInRequestDTO.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/register/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"password\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Location", "/logged in"));
    }
}
