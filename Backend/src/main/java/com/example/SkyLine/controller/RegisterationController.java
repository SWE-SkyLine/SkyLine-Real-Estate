package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.service.RegesterationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class RegisterationController {
    @Autowired
    private RegesterationService regesterationService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO user){
        if(regesterationService.userExists(user.getEmail()))
            return new ResponseEntity<String>("user already exists", HttpStatus.CONFLICT);
        return new ResponseEntity<User>(regesterationService.register(user), HttpStatus.OK);
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> signIn(@RequestBody LogInRequestDTO login){
        System.out.println("logged in " + login.getEmail()+ " "+ login.getPassword());
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(login.getEmail(), login.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        return ResponseEntity.ok()
                .header("Location", "/logged in")
                .build();
    }
    @PostMapping("/user/OauthLogin")
    public ResponseEntity<?> signInOauth(@RequestBody String emailOauth){
        return new ResponseEntity<Boolean>(this.regesterationService.signInOauth(emailOauth), HttpStatus.OK);
    }


    @PostMapping("/test")
    public UserRequestDTO test() {
        return new UserRequestDTO();
    }
}
