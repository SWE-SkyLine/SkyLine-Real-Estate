package com.example.skyline.service;

import com.example.skyline.model.Notification;
import com.example.skyline.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public void notify(int requesterID, int candidateID){
        for (int i = 1; i <=5 ; i++) {
            Notification request=new Notification(requesterID,candidateID,i);
            notificationRepository.save(request);
        }

        
    }
 public void approveRequest(Notification request){
        request.setApproved(true);
        request.setDateAnswered(new java.sql.Date(System.currentTimeMillis()));
        // update approved field in notification table in DB to true
        // update the dateAnswered field in notification table in DB
      

 }
    public void rejectRequest(Notification request){
        request.setApproved(false);
        request.setDateAnswered(new java.sql.Date(System.currentTimeMillis()));
        // update approved field in notification table in DB to false
        // update the dateAnswered field in notification table in DB
    }

    public ArrayList<Notification> getAdminPendingRequests(int AdminID){
        // //get admin pending requests where approved attribute=null ...group by requesterID
        return null;
    }
    public ArrayList<Notification> getAdminAnsweredRequests(int AdminID){
        // get admin answered requests where approved attribute=true or false...group by requesterID

        return null;
    }
    public ArrayList<Notification> getSuperAdminRequests(int AdminID){
        //    //get Super Admin requests where approved attribute=null ...group by responderID
        return null;
    }

    public void markSeen(Notification notification){
        //mark the seen attribute true in the notification table
    }
    


}
