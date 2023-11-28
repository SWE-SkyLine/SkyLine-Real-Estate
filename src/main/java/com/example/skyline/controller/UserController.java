package com.example.skyline.controller;

import com.example.skyline.model.Notification;
import com.example.skyline.service.NotificationService;
import com.example.skyline.service.UserService;
import com.example.skyline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/pending-requests")
    public ArrayList<Notification> getAdminPendingRequests(@RequestParam int adminId) {
        return notificationService.getAdminPendingRequests(adminId);
    }

    @GetMapping("/admin/answered-requests")
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
        userService.PromoteUser(request.getCandidateID());
    }

    @PutMapping("/update/reject")
    public void rejectRequest(@RequestBody Notification request) {
        notificationService.rejectRequest(request);
    }

    @PutMapping("/update/notify")
    public void notify(@RequestParam int requesterId, @RequestParam int candidateId) {
        notificationService.notify(requesterId, candidateId);
    }
    @PutMapping("/update/markSeen")
    public void markSeen(@RequestBody Notification request){
        notificationService.markSeen(request);
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        System.out.println("Received user: " + user.toString());
        return userService.createUser(user);
    }

    // Add more endpoint methods as needed
}
