package com.example.SkyLine.service;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.LogInResponseDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserOauthRepository;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.security.JwtService;
import com.example.SkyLine.utility.RepositoryFactory;
import com.example.SkyLine.utility.UserFactory;
import com.example.SkyLine.utility.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOauthRepository userOauthRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public boolean userExists(String Email) {
        return (userRepository.existsUserByEmail(Email) || userOauthRepository.existsById(Email)); // check if the email exists in basic registered or oAuth
    }

    // here we can direct the creation respect to the userType
    public User register(UserRequestDTO user) {
        User userToBeSaved = userFactory.userFactory(user.getUserRole().toString());
        String verificationCode = sendCodeVerifySignup(userToBeSaved);
        userToBeSaved.setFirstName(user.getFirstName()).setLastName(user.getLastName())
                .setEmail(user.getEmail()).setPassword(passwordEncoder.encode(user.getPassword()))
                .setPhoneNumber(user.getPhone_number()).setUserRole(user.getUserRole())
                .setVerificationCode(verificationCode);

        return (User) repositoryFactory.repositoryFactory(user.getUserRole().toString()).save(userToBeSaved);
    }

    private String sendCodeVerifySignup(User user) {
        String verificationCode = VerificationCodeGenerator.generateVerificationCode();
        emailService.setEmail(user.getEmail()).setVerificationCode(verificationCode);
        Thread verificationCodeSenderThread = new Thread(emailService);
        verificationCodeSenderThread.start();
        return verificationCode;
    }


    public User sendVerifyCodeAgain(String email) throws TimeoutException {
        try {
            String verificationCode = VerificationCodeGenerator.generateVerificationCode();
            emailService.sendCodeVerifySignup(email, verificationCode);
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
            user.setEnable(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public LogInResponseDTO signIn(LogInRequestDTO login) {
        System.out.println("logged in " + login.getEmail() + " " + login.getPassword());
        var authenticationResponse = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse); //throws unchecked exception
        return generateResponse(login, (UserDetails) authenticationResponse.getPrincipal());
    }

    private LogInResponseDTO generateResponse(LogInRequestDTO login, UserDetails userDetails) {
        User user = userRepository.findByEmail(login.getEmail());
        return new LogInResponseDTO().setId(user.getId()).setEmail(user.getEmail())
                .setName(user.getFirstName() + " " + user.getLastName())
                .setJwtToken(jwtService.generateToken(userDetails));
    }

    public boolean signInOauth(String emailOauth) {
        return userOauthRepository.existsById(emailOauth);
    }

}
