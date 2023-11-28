package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.service.RegesterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class RegisterationController {
    @Autowired
    private RegesterationService regesterationService;
    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO user){
        if(regesterationService.userExists(user.getEmail()))
            return new ResponseEntity<String>("user already exists", HttpStatus.CONFLICT);
        return new ResponseEntity<User>(regesterationService.register(user), HttpStatus.OK);
    }
    @PostMapping("/test")
    public UserRequestDTO test(){
        return new UserRequestDTO();
    }
}
