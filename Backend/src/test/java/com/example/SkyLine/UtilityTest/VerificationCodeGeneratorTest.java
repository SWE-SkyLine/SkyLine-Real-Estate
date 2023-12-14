package com.example.SkyLine.UtilityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.SkyLine.utility.VerificationCodeGenerator;

public class VerificationCodeGeneratorTest {
   @Test
    void testGenerateVerificationCode() {
        // Act
        String verificationCode = VerificationCodeGenerator.generateVerificationCode();

        // Assert
        assertNotNull(verificationCode);
        assertEquals(5, verificationCode.length());
        assertTrue(verificationCode.matches("[A-Z0-9]+"), "Verification code should only contain uppercase letters and digits");
    }
}
