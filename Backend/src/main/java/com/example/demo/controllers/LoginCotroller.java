package com.example.demo.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginCotroller {

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public boolean login(@RequestBody Map<String, Object> loginData){
        String email = (String) loginData.get("email");
        String password = (String) loginData.get("password");
        String type = (String) loginData.get("type");
        System.out.println(email + " "+ password + " " + type);
        return false;
    }
}
