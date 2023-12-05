package com.example.SkyLine.service;

import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserOauthRepository;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.UserTypeToUserRoleMapper;
import com.example.SkyLine.utility.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegesterationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOauthRepository userOauthRepository;
    @Autowired
    UserTypeToUserRoleMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService EmailService;

    public boolean userExists(String Email) {
        return (userRepository.existsUserByEmail(Email) || userOauthRepository.existsById(Email)); // check if the email exists in basic registered or oAuth
    }
    // here we can direct the creation respect to the userType
    public User register(UserRequestDTO user) {
        User userToBeSaved = new User();
        String VerificationCode= VerificationCodeGenerator.generateVerificationCode();
        EmailService.SendCodeVerifySignup(user.getEmail(),VerificationCode);
        userToBeSaved.setFirstName(user.getFirstName());
        userToBeSaved.setLastName(user.getLastName());
        userToBeSaved.setEmail(user.getEmail());
        userToBeSaved.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeSaved.setPhoneNumber(user.getPhone_number());
        userToBeSaved.setUserRole(mapper.map(user.getUserType()));
        userToBeSaved.setVerificationCode(VerificationCode);
        return userRepository.save(userToBeSaved);
    }

    public boolean UserVerify(String Email,String code) {
            User user =userRepository.findByEmail(Email);
                if(Objects.equals(user.getVerificationCode(), code)){
                    user.setIs_enable(true);
                    userRepository.save(user);
                    return true;
                }
        return false;
    }
    public boolean signInOauth(String emailOauth){
        return userOauthRepository.existsById(emailOauth);
    }

}
