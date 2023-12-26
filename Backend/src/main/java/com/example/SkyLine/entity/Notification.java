package com.example.SkyLine.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity(name = "Notification")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Notification")

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

    public Notification(int requester_id, int responder_id) {
        this.responder_id = responder_id;
        this.requester_id = requester_id;
        this.date_requested = new java.sql.Date(System.currentTimeMillis());
        this.date_answered =null;
        this.approved =null;
        this.seen =false;
        this.answered=false;
    }


    public boolean isApproved() {
        return approved;
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

    public void setId(int notification_id) {
        this.id = notification_id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
