package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.DTO.VerifyCodeRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.RegesterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class RegisterationController {
    @Autowired
    private RegesterationService regesterationService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  EmailService EmailService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO user){
        if(regesterationService.userExists(user.getEmail())){
            return new ResponseEntity<String>("user already exists", HttpStatus.IM_USED);
        }
        else{
            return new ResponseEntity<User>(regesterationService.register(user), HttpStatus.OK);
        }
    }

    @PostMapping("/user/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyCodeRequestDTO Request){
        //continue ...
        try {
            if(regesterationService.UserVerify(Request.getEmail(),Request.getCode())){
                return new ResponseEntity<String>("Success", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Error", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception e){
            return new ResponseEntity<String>("Error", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/user/sendVerifyCodeAgain")
    public ResponseEntity<?> sendCodeAgain(@RequestBody VerifyCodeRequestDTO Request){
        //continue ...
        System.out.println(Request.getEmail());
        try {
            regesterationService.sendVerifyCodeAgain(Request.getEmail());
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Time Out", HttpStatus.REQUEST_TIMEOUT);

        }


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
