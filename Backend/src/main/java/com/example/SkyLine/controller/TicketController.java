package com.example.SkyLine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkyLine.DTO.TicketRequestDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TicketController {
    @PostMapping("/ticket")
    public String getTicket(@RequestBody TicketRequestDTO ticket){
        System.out.println(ticket.getEmail());
        System.out.println(ticket.getCategory());
        System.out.println(ticket.getReported());
        System.out.println(ticket.getMessage());
        return "Ticket is sent";
    }
}
