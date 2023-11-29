package com.example.SkyLine.service;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.UserRepository;
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
    public ArrayList<User> getAllUsersByAccountType(UserRoleEnum roleEnum) {
        return userRepository.getAllUsersByAccountType(roleEnum);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        // You may want to perform additional validation or business logic before saving the user
        System.out.println("Received user in service: " + user.toString());
        return userRepository.save(user);
    }

    public void PromoteUser(int UserID){
        userRepository.promoteToAdmin(UserID, UserRoleEnum.ADMIN);
        // update the account_type field of the candidate user in the user table in DB to Admin
    }


    // Add more methods as needed

}

