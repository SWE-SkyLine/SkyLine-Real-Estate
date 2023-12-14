package com.example.SkyLine.controller;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.model.EmailRequest;
import com.example.SkyLine.model.UpdatePasswordRequest;

import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository, EmailService emailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/allUsersToPromote")
    public List<User> getAllUsersToPromote() {
        return userService.getAllUsersByAccountType("Client");
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/send-email")
    public ResponseEntity<String> sendVerificationCode(@RequestBody EmailRequest emailRequest) {
        String recipientEmail = emailRequest.getEmail();

        User user = userRepository.findByEmail(recipientEmail);

        try {
            if (user != null) {
                // Generate the verification code
                Random random = new Random();
                int code = random.nextInt(999999 - 100000 + 1) + 100000;

                // Set the verification code in the user entity
                user.setVerificationCodeForgetPassword(code);

                // Save the user entity to update the verification code
                userRepository.save(user);

                // Call a service method to send the email
                emailService.sendEmail(recipientEmail, "Subject", "Code: " + code);

                return ResponseEntity.ok("Email sent successfully");
            } else {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }

    @GetMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam int code) {
        // Retrieve the user from the database
        User user = userRepository.findByEmail(email);

        try {
            if (user != null) {
                // Get the stored verification code
                int storedCode = user.getVerificationCodeForgetPassword();

                if (storedCode == code) {
                    // Verification code matches
                    return ResponseEntity.ok("Verification successful");
                } else {
                    // Verification code does not match
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect verification code");
                }
            } else {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification failed");
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        String email = updatePasswordRequest.getEmail();
        String newPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());


        // Retrieve the user from the database
        User user = userRepository.findByEmail(email);

        try {
            if (user != null) {
                // Update the user's password
                user.setPassword(newPassword);

                // Save the updated user entity
                userRepository.save(user);


                return ResponseEntity.ok(newPassword);
            } else {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password update failed");
        }
    }


    @PostMapping("/promote-user")
    public boolean promoteUser(@RequestParam String id){
       try {
           System.out.println("id: " + id);
           userService.PromoteUser(id);
           return true;
       }
       catch (Exception e){
           return false;
       }

    }




}
