package com.example.SkyLine.DTO;

import java.util.Date;

public class NotificationRequestDTO {

    public int getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getResponderId() {
        return responderId;
    }

    public void setResponderId(int responderId) {
        this.responderId = responderId;
    }



    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private int notificationId;

    private int responderId;
    private Date date_requested;

    private Date date_answered;
    private int requesterId;

    private int candidateId;


    private String message;


    private boolean decide;

    public NotificationRequestDTO( int notificationId,int responderId, int requesterId, int candidateId, String message, Date date_requested, Date date_answered, boolean decide) {
        this.responderId = responderId;
        this.requesterId = requesterId;
        this.candidateId = candidateId;
        this.message = message;
        this.date_requested = date_requested;
        this.date_answered = date_answered;
        this.decide = decide;
        this.notificationId = notificationId;
    }


}
