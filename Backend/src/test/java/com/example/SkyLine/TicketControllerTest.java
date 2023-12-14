package com.example.SkyLine;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SkyLine.DTO.TicketRequestDTO;
import com.example.SkyLine.controller.TicketController;
import com.example.SkyLine.enums.TicketCategoryEnum;
import com.example.SkyLine.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;


    @InjectMocks
    private TicketController ticketController;

    @Test
    void testGetTicketSuccess() throws Exception {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setCategory(TicketCategoryEnum.OTHER);
        ticketRequestDTO.setEmail("a");
        ticketRequestDTO.setMessage("a");
        ticketRequestDTO.setReported("a");
        doNothing().when(ticketService).sendTicket(ticketRequestDTO);

        mockMvc.perform(post("/api/ticket")
                .content(new ObjectMapper().writeValueAsString(ticketRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTicketFailure() throws Exception {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        doThrow(new RuntimeException("Test exception")).when(ticketService).sendTicket(ticketRequestDTO);

        mockMvc.perform(post("/api/ticket")
                .content(new ObjectMapper().writeValueAsString(ticketRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    
}
