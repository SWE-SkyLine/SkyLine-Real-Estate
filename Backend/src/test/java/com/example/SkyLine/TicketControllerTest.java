package com.example.SkyLine;

import com.example.SkyLine.DTO.TicketRequestDTO;
import com.example.SkyLine.controller.TicketController;
import com.example.SkyLine.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @Test
    void testGetTicket() {
        // Arrange
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        doNothing().when(ticketService).sendTicket(ticketRequestDTO);

        // Act
        ResponseEntity<String> responseEntity = ticketController.getTicket(ticketRequestDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Ticket sent successfully", responseEntity.getBody());
        verify(ticketService, times(1)).sendTicket(ticketRequestDTO);
    }

    @Test
    void testGetTicketException() {
        // Arrange
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        doThrow(new RuntimeException("Test exception")).when(ticketService).sendTicket(ticketRequestDTO);

        // Act
        ResponseEntity<String> responseEntity = ticketController.getTicket(ticketRequestDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to send Ticket", responseEntity.getBody());
        verify(ticketService, times(1)).sendTicket(ticketRequestDTO);
    }
}