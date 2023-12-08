package com.example.SkyLine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkyLine.DTO.TicketRequestDTO;
import com.example.SkyLine.service.TicketService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    @PostMapping("/ticket")
    public String getTicket(@RequestBody TicketRequestDTO ticket){
        try{
            ticketService.sendTicket(ticket);
            System.out.println("ticket sent");
            return "Ticket is sent";
        }catch(Exception e){
            return "Fail to send ticket";
        } 
    }
}
