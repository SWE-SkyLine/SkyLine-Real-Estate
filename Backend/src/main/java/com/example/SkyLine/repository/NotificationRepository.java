package com.example.SkyLine.repository;
import com.example.SkyLine.entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM Notification WHERE requester_id = ?1 AND answered=false AND responder_id=1 ", nativeQuery = true)
    ArrayList<Notification> getAdminPendingRequests(int AdminID);

    public Notification findNotificationById(int notification_id);

    @Query(value = "SELECT * FROM Notification WHERE requester_id = ?1 AND (approved=true OR approved=false)AND answered=true ", nativeQuery = true)
    ArrayList<Notification> getAdminAnsweredRequests(int AdminID);



    @Query(value = "SELECT * FROM Notification WHERE responder_id = ?1 AND answered=true", nativeQuery = true)
    ArrayList<Notification> getSuperAdminAnsweredRequests(int SuperAdminID);
    @Query(value = "SELECT * FROM Notification WHERE responder_id = ?1 AND answered=false", nativeQuery = true)
    ArrayList<Notification> getSuperAdminPendingRequests(int SuperAdminID);
    @Query(value = "SELECT * FROM Notification WHERE responder_id = ?1 AND answered=false", nativeQuery = true)
    ArrayList<Notification> getClientPendingRequests(int ClientID);
    @Query(value = "SELECT * FROM Notification WHERE responder_id = ?1 AND answered=true", nativeQuery = true)
    ArrayList<Notification> getClientAnsweredRequests(int ClientID);

    @Query(value = "SELECT * FROM Notification WHERE candidate_id = ?1 AND approved=true", nativeQuery = true)
    ArrayList<Notification> getClientPromotionNotification(int ClientID);


    @Query(value = "SELECT * FROM Notification WHERE requester_id = ?1 AND answered=false ", nativeQuery = true)
    ArrayList<Notification> getCompanyPendingRequests(int CompanyID);



    @Query(value = "SELECT * FROM Notification WHERE requester_id = ?1 AND (approved=true OR approved=false)AND answered=true ", nativeQuery = true)
    ArrayList<Notification> getCompanyAnsweredRequests(int CompanyID);

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.approved = :Response, n.date_answered = :DateTime, n.answered= true WHERE n.id = :NotificationId")
    void updateNotificationById(
            @Param("NotificationId") int NotificationId,
            @Param("Response") Boolean Response,
            @Param("DateTime") Date DateTime
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Notification n WHERE n.requester_id = :RequesterID and n.candidate_id=:CandidateID and n.responder_id!=:ResponderID ")
    void deleteNotificationById(


            @Param("RequesterID") int RequesterID,
            @Param("CandidateID") int CandidateID,
            @Param("ResponderID") int ResponderID


    );

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.candidate_id = :Candidate_id")
    long countCandidatePreviousRequests(@Param("Candidate_id") int Candidate_id);


    @Query("SELECT COUNT(n) FROM Notification n WHERE n.requester_id = :Company_id AND n.responder_id=:User_id")
    long countCompanyPreviousRequests(@Param("Company_id") int Company_id, @Param("User_id") int User_id);

    //mark the seen attribute true in the notification table



}
