package com.example.skyline.service;

import com.example.skyline.repo.UserRepository;
import com.example.skyline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        System.out.println("Received user in service: " + user.toString());
        return userRepository.save(user);
    }

    public ArrayList<User> getAllSuperAdmins() {
        return null;
    }

    public void PromoteUser(int UserID){
        // update the account_type field of the candidate user in the user table in DB to Admin
    }


    // Add more methods as needed

}

