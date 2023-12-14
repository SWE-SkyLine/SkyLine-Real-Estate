package com.example.SkyLine.controller;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

//test
@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String addUser(){
        Path currentDir = Paths.get("");
        currentDir = currentDir.toAbsolutePath();
        System.out.println("Current directory: " + currentDir);
        return "OK";
    }
}
