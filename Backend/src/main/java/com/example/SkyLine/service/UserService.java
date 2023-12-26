package com.example.SkyLine.service;

import com.example.SkyLine.DTO.UserPromoteDTO;
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
    public ArrayList<User> getAllUsersByAccountType(String accountType) {
        return userRepository.getAllUsersByAccountType(UserRoleEnum.valueOf(accountType));
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        // You may want to perform additional validation or business logic before saving the user
        System.out.println("Received user in service: " + user.toString());
        return userRepository.save(user);
    }

    public void PromoteUser(String UserID){
        userRepository.promoteToAdmin(Integer.parseInt(UserID), UserRoleEnum.ADMIN);
        // update the account_type field of the candidate user in the user table in DB to Admin
    }

    public List<UserPromoteDTO> searchClientUsers(String query) {
        List<User> matchingUsers = userRepository.findClientsByFirstNameOrLastName(query, UserRoleEnum.CLIENT);

        List<UserPromoteDTO> userDTOs = new ArrayList<>();

        for (User user : matchingUsers) {
            // Assuming User entity has getters for first name, last name, and profile photo
            UserPromoteDTO userDTO = new UserPromoteDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getProfile_photo());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }
    


}

