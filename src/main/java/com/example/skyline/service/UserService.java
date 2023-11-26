package com.example.skyline.service;

import com.example.skyline.repo.UserRepository;
import com.example.skyline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        // You may want to perform additional validation or business logic before saving the user
        return userRepository.save(user);
    }


    // Add more methods as needed

}

