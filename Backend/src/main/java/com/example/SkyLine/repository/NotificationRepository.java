package com.example.SkyLine.repository;
import com.example.SkyLine.model.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM Notifications WHERE requester_id = ?1 AND answered=false AND responder_id=1 ", nativeQuery = true)
    ArrayList<Notification> getAdminPendingRequests(int AdminID);



    @Query(value = "SELECT * FROM Notifications WHERE requester_id = ?1 AND (approved=true OR approved=false)AND answered=true ", nativeQuery = true)
    ArrayList<Notification> getAdminAnsweredRequests(int AdminID);



    @Query(value = "SELECT * FROM Notifications WHERE responder_id = ?1 AND answered=false", nativeQuery = true)
    ArrayList<Notification> getSuperAdminRequests(int SuperAdminID);


    @Transactional
    @Modifying
    @Query("UPDATE Notifications n SET n.approved = :Response, n.date_answered = :DateTime, n.answered= true WHERE n.requester_id = :RequesterID and n.candidate_id = :CandidateID")
    void updateNotificationById(
            @Param("RequesterID") int RequesterID,
            @Param("CandidateID") int CandidateID,
            @Param("Response") Boolean Response,
            @Param("DateTime") Date DateTime
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Notifications n WHERE n.requester_id = :RequesterID and n.candidate_id=:CandidateID and n.responder_id!=:ResponderID ")
    void deleteNotificationById(


            @Param("RequesterID") int RequesterID,
            @Param("CandidateID") int CandidateID,
            @Param("ResponderID") int ResponderID


    );

    @Query("SELECT COUNT(n) FROM Notifications n WHERE n.candidate_id = :Candidate_id and n.answered=false")
    long countCandidatePreviousRequests(@Param("Candidate_id") int Candidate_id);

    //mark the seen attribute true in the notification table



}
