package com.example.SkyLine.controller;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String addUser(){
        User user = new User();
        user.setId(2);
        user.setEmail("amin@hasdp.com");
        user.setAccountType("yohohoh");
        user.setPassword("hahahah");
        user.setPhoneNumber("0123123");
        userRepository.save(user);
        return "OK";
    }
}
