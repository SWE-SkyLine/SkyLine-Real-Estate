package com.example.SkyLine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

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
    private Boolean answered;

    public Notification(int requester_id, int candidate_id, int responder_id){
        this.responder_id = responder_id;
        this.candidate_id = candidate_id;
        this.requester_id = requester_id;
        this.date_requested = new java.sql.Date(System.currentTimeMillis());
        this.date_answered =null;
        this.approved =null;
        this.seen =false;
        this.answered=false;

    }




    public int getCandidate_id() {
        return candidate_id;
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
