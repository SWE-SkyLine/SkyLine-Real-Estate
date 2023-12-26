package com.example.SkyLine.controller;
import com.example.SkyLine.DTO.NotificationRequestDTO;
import com.example.SkyLine.entity.Notification;
import com.example.SkyLine.service.NotificationService;
import com.example.SkyLine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController( NotificationService notificationService) {
        this.notificationService = notificationService;
    }



@GetMapping()
public ArrayList<NotificationRequestDTO> getNotifications(@RequestParam int userId){

        return this.notificationService.getUserNotifications(userId);
}


    @PutMapping("/update/approve")
    public void approveRequest(@RequestParam int NotificationId) {
        notificationService.approveRequest(NotificationId);
    }

    @PutMapping("/update/reject")
    public void rejectRequest(@RequestParam int NotificationId) {
        notificationService.rejectRequest(NotificationId);
    }

    @PutMapping("/update/notify")
    public boolean notify(@RequestParam int requesterId, @RequestParam int candidateId) {
        return notificationService.request(requesterId, candidateId);
    }
    @PutMapping("/update/markSeen")
    public void markSeen(@RequestBody Notification request){
        notificationService.markSeen(request);
    }
}
