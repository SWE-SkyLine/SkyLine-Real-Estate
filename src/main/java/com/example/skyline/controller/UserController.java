package com.example.skyline.controller;

import com.example.skyline.model.Notification;
import com.example.skyline.service.NotificationService;
import com.example.skyline.service.UserService;
import com.example.skyline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/allUsersToPromote")
    public List<User> getAllUsersToPromote() {
        return userService.getAllUsersByAccountType("SuperAdmin");
    }


    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        System.out.println("Received user: " + user.toString());
        return userService.createUser(user);
    }

    // Add more endpoint methods as needed
}
