package com.example.SkyLine;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.DTO.VerifyCodeRequestDTO;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.concurrent.TimeoutException;

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
    void testSignUpAlreadyExists(){
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        doReturn(Boolean.TRUE).when(regesterationService).userExists(userRequestDTO.getEmail());
        ResponseEntity<?> responseEntity = registerationController.signUp(userRequestDTO);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("user already exists", responseEntity.getBody());
    }

    @Test
    void testSignUpNew(){
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        doReturn(Boolean.FALSE).when(regesterationService).userExists(userRequestDTO.getEmail());
        ResponseEntity<?> responseEntity = registerationController.signUp(userRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testVerifyTrue(){
        VerifyCodeRequestDTO  verifyCodeRequestDTO = new VerifyCodeRequestDTO();
        verifyCodeRequestDTO.setCode("1234");
        verifyCodeRequestDTO.setEmail("test@example.com");
        doReturn(Boolean.TRUE).when(regesterationService).UserVerify(verifyCodeRequestDTO.getEmail(), verifyCodeRequestDTO.getCode());
        assertEquals(HttpStatus.OK, registerationController.verify(verifyCodeRequestDTO).getStatusCode());
    }

    @Test
    void testVerifyFalse(){
        VerifyCodeRequestDTO  verifyCodeRequestDTO = new VerifyCodeRequestDTO();
        verifyCodeRequestDTO.setCode("1234");
        verifyCodeRequestDTO.setEmail("test@example.com");
        doReturn(Boolean.FALSE).when(regesterationService).UserVerify(verifyCodeRequestDTO.getEmail(), verifyCodeRequestDTO.getCode());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, registerationController.verify(verifyCodeRequestDTO).getStatusCode());
    }

    @Test 
    void testLogin(){
        LogInRequestDTO logInRequestDTO = new LogInRequestDTO();
        logInRequestDTO.setEmail("Test@example.com");
        logInRequestDTO.setPassword("password");
        assertEquals(logInRequestDTO.getPassword(), "password");
        assertEquals(logInRequestDTO.getEmail(), "Test@example.com");
        assertEquals(HttpStatus.OK, registerationController.signIn(logInRequestDTO).getStatusCode());
    }

    @Test 
    void testSendAgain() throws TimeoutException{
        VerifyCodeRequestDTO verifyCodeRequestDTO = new VerifyCodeRequestDTO();
        assertEquals(HttpStatus.OK, registerationController.sendCodeAgain(verifyCodeRequestDTO).getStatusCode());
    }
    
}
