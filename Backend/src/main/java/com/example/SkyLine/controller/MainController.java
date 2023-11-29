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
        return "OK";
    }
}
