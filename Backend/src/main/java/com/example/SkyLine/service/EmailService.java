package com.example.SkyLine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    public void  sendCodeVerifySignup(String Email, String VerificationCode){
        sendEmail(Email, "VerificationCode", "Dear [" + Email + "],\n" +
                "\n" +
                "To complete your registration, please use the following verification code: "
                + VerificationCode + "\n"
                +"This code is valid for a limited time, so please enter it as soon as possible to activate your account.\n" +
                "Thank you,\n" +
                "[Skyline RealEstate]");
    }

}
