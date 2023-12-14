package com.example.SkyLine.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SkyLine.DTO.TicketRequestDTO;

@Service
public class TicketService {

    @Autowired
    private EmailService emailService;

    private String techSupport = "ahmedhesham1652001@gmail.com";

    public void sendTicket(TicketRequestDTO ticket){     
        String subject = ticket.getCategory().toString() + " from " + ticket.getEmail();
        StringBuilder message = new StringBuilder();
        message.append("From ");
        message.append(ticket.getEmail());
        message.append("\n");
        if(ticket.getReported().length() != 0){
            message.append("Reported user is ");
            message.append(ticket.getReported());
            message.append("\n");
        }
        message.append(ticket.getMessage());
        emailService.sendEmail(techSupport, subject, message.toString());
    }
}

