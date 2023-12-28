package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.LogInRequestDTO;
import com.example.SkyLine.DTO.LogInResponseDTO;
import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.DTO.VerifyCodeRequestDTO;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.security.JwtService;
import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.RegistrationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
//
@RequestMapping("/register")
public class RegisterationController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EmailService EmailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO user) {
        System.out.println("In signUp controller - Email : " + user.getEmail()
                + " Password : " + user.getPassword());
        if (registrationService.userExists(user.getEmail())) {
            return new ResponseEntity<String>("user already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<User>(registrationService.register(user), HttpStatus.OK);
        }
    }

   //TODO : Handle unchecked exception caused by auth manager
    @PostMapping("/user/login")
    public ResponseEntity<?> signIn(@RequestBody LogInRequestDTO login, @NonNull HttpServletResponse response) {
        System.out.println("In LogIn controller - Email : " + login.getEmail()
                + " Password : " + login.getPassword());
        return new ResponseEntity<LogInResponseDTO>(registrationService.signIn(login, response), HttpStatus.OK);
    }

    @PostMapping("/user/sendVerifyCodeAgain")
    public ResponseEntity<?> sendCodeAgain(@RequestBody VerifyCodeRequestDTO Request) {
        //continue ...
        System.out.println(Request.getEmail());
        try {
            registrationService.sendVerifyCodeAgain(Request.getEmail());
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Time Out", HttpStatus.REQUEST_TIMEOUT);

        }
    }

    @PostMapping("/user/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyCodeRequestDTO Request) {
        boolean status = registrationService.UserVerify(Request.getEmail(), Request.getCode());
        if (status) return new ResponseEntity<String>("User is verifired", HttpStatus.OK);
        else return new ResponseEntity<String>("Verification code doesn't match", HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping("/user/OauthLogin")
    public ResponseEntity<?> signInOauth(@RequestBody String emailOauth) {
        return new ResponseEntity<Boolean>(this.registrationService.signInOauth(emailOauth), HttpStatus.OK);
    }


    @PostMapping("/test")
    public UserRequestDTO test() {
        return new UserRequestDTO();
    }
}
