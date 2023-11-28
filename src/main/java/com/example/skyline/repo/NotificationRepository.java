package com.example.skyline.repo;
import com.example.skyline.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    //get admin pending requests where approved attribute=null ...group by requesterID
    // get admin answered requests where approved attribute=true or false...group by requesterID
    //get Super Admin requests where approved attribute=null ...group by responderID
    // update approved field in notification table in DB to true where notification_id=id
    // update the dateAnswered field in notification table in DB
    //mark the seen attribute true in the notification table



}
