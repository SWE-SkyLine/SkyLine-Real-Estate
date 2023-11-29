package com.example.SkyLine.utility;

import java.util.Random;

public class VerificationCodeGenerator {
    public static String generateVerificationCode() {
        int codeLength = 5;
        Random random = new Random();
        String verificationCode = "";
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char character = allowedChars.charAt(randomIndex);
            verificationCode += character;
        }
        return verificationCode;
    }

}
