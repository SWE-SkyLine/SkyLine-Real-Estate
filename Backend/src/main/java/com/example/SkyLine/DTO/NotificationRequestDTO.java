package com.example.SkyLine.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private int notificationId;

    private int responderId;
    private String date_requested;

    private String date_answered;
    private int requesterId;

    private int candidateId;


    private String message;


    public String getDate_requested() {
        return date_requested;
    }

    public void setDate_requested(LocalDateTime date_requested) {

        this.date_requested = date_requested.format(formatter);;
    }

    public String getDate_answered() {
        return date_answered;
    }

    public void setDate_answered(LocalDateTime date_answered) {
        if(date_answered == null){
            this.date_answered = null;
        }
        else{this.date_answered = date_answered.format(formatter);}
    }

    public boolean isDecide() {
        return decide;
    }

    public void setDecide(boolean decide) {
        this.decide = decide;
    }

    private boolean decide;

    public NotificationRequestDTO( int notificationId,int responderId, int requesterId, int candidateId, String message, LocalDateTime date_requested, LocalDateTime date_answered, boolean decide) {
        this.responderId = responderId;
        this.requesterId = requesterId;
        this.candidateId = candidateId;
        this.message = message;
        this.date_requested = date_requested.format(formatter);
        if(date_answered == null){
            this.date_answered = null;
        }
        else{this.date_answered = date_answered.format(formatter);}

        this.decide = decide;
        this.notificationId = notificationId;
    }


}
