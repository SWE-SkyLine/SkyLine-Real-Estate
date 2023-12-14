package com.example.SkyLine.service;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.LogInResponseDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserOauthRepository;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.RepositoryFactory;
import com.example.SkyLine.utility.UserFactory;
import com.example.SkyLine.utility.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

@Service
public class RegesterationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOauthRepository userOauthRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService EmailService;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean userExists(String Email) {
        return (userRepository.existsUserByEmail(Email) || userOauthRepository.existsById(Email)); // check if the email exists in basic registered or oAuth
    }

    // here we can direct the creation respect to the userType
    public User register(UserRequestDTO user) {
        User userToBeSaved = userFactory.userFactory(user.getUserRole().toString());
        String VerificationCode = VerificationCodeGenerator.generateVerificationCode();
        EmailService.setEmail(user.getEmail());
        EmailService.setVerificationCode(VerificationCode);
        Thread verificationCodeSenderThread = new Thread(EmailService);
        verificationCodeSenderThread.start();
        userToBeSaved.setFirstName(user.getFirstName());
        userToBeSaved.setLastName(user.getLastName());
        userToBeSaved.setEmail(user.getEmail());
        userToBeSaved.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeSaved.setPhoneNumber(user.getPhone_number());
        userToBeSaved.setUserRole(user.getUserRole());
        userToBeSaved.setVerificationCode(VerificationCode);

        return (User) repositoryFactory.repositoryFactory(user.getUserRole().toString()).save(userToBeSaved);
    }
    public User sendVerifyCodeAgain(String email) throws TimeoutException {
        try {
            String verificationCode = VerificationCodeGenerator.generateVerificationCode();
            EmailService.sendCodeVerifySignup(email, verificationCode);
            User user = userRepository.findByEmail(email);
            user.setVerificationCode(verificationCode);
            return userRepository.save(user);
        } catch (Exception e) {
            // Optionally, you can throw a custom exception or return an error response
            throw new TimeoutException("Error sending verification code: " + e.getMessage());
        }
    }


    public boolean UserVerify(String Email, String code) {
        User user = userRepository.findByEmail(Email);
        if (Objects.equals(user.getVerificationCode(), code)) {
            user.setIs_enable(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public int signIn(LogInRequestDTO login){
        System.out.println("logged in " + login.getEmail()+ " "+ login.getPassword());
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(login.getEmail(), login.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        //LogInResponseDTO logInResposne = new LogInResponseDTO();
        return userRepository.findByEmail(login.getEmail()).getId();
    }

    public boolean signInOauth(String emailOauth) {
        return userOauthRepository.existsById(emailOauth);
    }

}
