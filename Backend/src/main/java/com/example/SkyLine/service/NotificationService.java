package com.example.SkyLine.service;

import com.example.skyline.model.Notification;
import com.example.skyline.repo.NotificationRepository;
import com.example.skyline.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }
    public boolean notify(int requesterID, int candidateID){
        long previousRequests=notificationRepository.countCandidatePreviousRequests(candidateID);
        if(previousRequests>0){return false;}
        ArrayList<Integer> SuperAdminsIDs=userRepository.getSuperAdminsID("SuperAdmin");
        for (Integer SuperAdminsID : SuperAdminsIDs
             ) {
            Notification request=new Notification(requesterID,candidateID,SuperAdminsID);
            notificationRepository.save(request);
        }



        return true;

        
    }
 public void approveRequest(Notification request){
        notificationRepository.deleteNotificationById(request.getRequester_id(), request.getCandidate_id(), request.getResponder_id());
        notificationRepository.updateNotificationById(request.getRequester_id(), request.getCandidate_id(), true, new java.sql.Date(System.currentTimeMillis()) );

 }
    public void rejectRequest(Notification request){
        notificationRepository.deleteNotificationById(request.getRequester_id(), request.getCandidate_id(), request.getResponder_id());
        notificationRepository.updateNotificationById(request.getRequester_id(), request.getCandidate_id(), false, new java.sql.Date(System.currentTimeMillis()) );
    }

    public ArrayList<Notification> getAdminPendingRequests(int AdminID){

        ArrayList<Notification> n= notificationRepository.getAdminPendingRequests(AdminID);

return n;
    }
    public ArrayList<Notification> getAdminAnsweredRequests(int AdminID){

      return notificationRepository.getAdminAnsweredRequests(AdminID);

    }
    public ArrayList<Notification> getSuperAdminRequests(int SuperAdminID){

       return notificationRepository.getSuperAdminRequests(SuperAdminID);

    }

    public void markSeen(Notification notification){
        notification.setSeen(true);
//        notificationRepository.updateNotificationById(notification.getNotification_id(), notification );
        //mark the seen attribute true in the notification table
    }
    


}
