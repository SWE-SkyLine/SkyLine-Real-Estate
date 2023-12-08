package com.example.SkyLine;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.SkyLine.DTO.TicketRequestDTO;
import com.example.SkyLine.enums.TicketCategoryEnum;
import com.example.SkyLine.service.EmailService;
import com.example.SkyLine.service.TicketService;

@SpringBootTest
public class TicketServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testSendEmailParam(){
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setCategory(TicketCategoryEnum.OTHER);
        ticketRequestDTO.setEmail("test@example.com");
        ticketRequestDTO.setReported("User123");
        ticketRequestDTO.setMessage("Test message");

        ticketService.sendTicket(ticketRequestDTO);

        String expectedSubject = "OTHER from test@example.com";
        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append("From test@example.com\n");
        expectedMessage.append("Reported user is User123\n");
        expectedMessage.append("Test message");

        verify(emailService).sendEmail("ahmedhesham1652001@gmail.com", expectedSubject, expectedMessage.toString());
    }
}
