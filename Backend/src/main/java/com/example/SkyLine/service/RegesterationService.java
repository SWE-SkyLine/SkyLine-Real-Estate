package com.example.SkyLine.service;

import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.UserTypeToUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegesterationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserTypeToUserRoleMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean userExists(String Email) {
        return userRepository.existsUserByEmail(Email);
    }
    // here we can direct the creation respect to the userType
    public User register(UserRequestDTO user) {
        User userToBeSaved = new User();
        userToBeSaved.setFirstName(user.getFirstName());
        userToBeSaved.setLastName(user.getLastName());
        userToBeSaved.setEmail(user.getEmail());
        userToBeSaved.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeSaved.setPhoneNumber(user.getPhone_number());
        userToBeSaved.setUserRole(mapper.map(user.getUserType()));

        return userRepository.save(userToBeSaved);
    }
}
