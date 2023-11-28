package com.example.skyline.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Entity(name = "Notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;
    private int responder_id;
    private int requester_id;
    private int candidate_id;

    private Date date_requested;

    private Date date_answered;
    private Boolean approved;

    private Boolean seen;

    public Notification( int requesterID,int candidateID, int responderID){
        this.responder_id = responderID;
        this.candidate_id = candidateID;
        this.requester_id = requesterID;
        this.date_requested = new java.sql.Date(System.currentTimeMillis());
        this.date_answered =null;
        this.approved =null;
        this.seen =false;

    }


    public void setDateAnswered(Date dateAnswered) {
        this.date_answered = dateAnswered;
    }

    public void setDateRequested(Date dateRequested) {
        this.date_requested = dateRequested;
    }

    public Date getDateAnswered() {
        return date_answered;
    }

    public Date getDateRequested() {
        return date_requested;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }



    public int getNotification_id() {
        return notification_id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public int getCandidateID() {
        return candidate_id;
    }

    public Date getDate_answered() {
        return date_answered;
    }

    public Date getDate_requested() {
        return date_requested;
    }

    public int getRequester_id() {
        return requester_id;
    }

    public int getResponder_id() {
        return responder_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
