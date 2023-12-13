package com.example.SkyLine;

import com.example.SkyLine.service.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    private String testEmail = "test@example.com";
    private String testVerificationCode = "123456";

    @Before
    public void setUp() {
        emailService.setEmail(testEmail);
        emailService.setVerificationCode(testVerificationCode);
    }

    @Test
    public void testSendCodeVerifySignup() {
        String expectedSubject = "VerificationCode";
        String expectedBody = "Dear [" + testEmail + "],\n" +
                "\n" +
                "To complete your registration, please use the following verification code: "
                + testVerificationCode + "\n"
                + "This code is valid for a limited time, so please enter it as soon as possible to activate your account.\n" +
                "Thank you,\n" +
                "[Skyline RealEstate]";

        emailService.sendCodeVerifySignup(testEmail, testVerificationCode);

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();

        // Perform assertions on the sent message
        assertEquals(testEmail, sentMessage.getTo()[0]);
        assertEquals(expectedSubject, sentMessage.getSubject());
        assertEquals(expectedBody, sentMessage.getText());
    }

    @Test
    public void testRun() {
        // Mocking javaMailSender to throw an exception
        doThrow(new RuntimeException("Test exception")).when(javaMailSender).send(any(SimpleMailMessage.class));

        // Run the Runnable (which calls sendCodeVerifySignup)
        emailService.run();
    }

    // Additional test methods for other functionality in EmailService
}
