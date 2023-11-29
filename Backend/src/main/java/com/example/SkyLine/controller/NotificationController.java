package com.example.SkyLine.controller;
import com.example.SkyLine.model.Notification;
import com.example.SkyLine.service.NotificationService;
import com.example.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }
    @GetMapping("/admin/pending-requests")
    public ArrayList<Notification> getAdminPendingRequests(@RequestParam int adminId) {
        return notificationService.getAdminPendingRequests(adminId);
    }

    @GetMapping("/admin/answered-requests")
    @ResponseBody
    public ArrayList<Notification> getAdminAnsweredRequests(@RequestParam int adminId) {
        return notificationService.getAdminAnsweredRequests(adminId);
    }

    @GetMapping("/super-admin/requests")
    public ArrayList<Notification> getSuperAdminRequests(@RequestParam int superAdminId) {
        return notificationService.getSuperAdminRequests(superAdminId);
    }

    @PutMapping("/update/approve")
    public void approveRequest(@RequestBody Notification request) {
        notificationService.approveRequest(request);
        userService.PromoteUser(request.getCandidate_id());
    }

    @PutMapping("/update/reject")
    public void rejectRequest(@RequestBody Notification request) {
        notificationService.rejectRequest(request);
    }

    @PutMapping("/update/notify")
    public boolean notify(@RequestParam int requesterId, @RequestParam int candidateId) {
        return notificationService.notify(requesterId, candidateId);
    }
    @PutMapping("/update/markSeen")
    public void markSeen(@RequestBody Notification request){
        notificationService.markSeen(request);
    }
}
