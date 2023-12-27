package com.example.SkyLine.DTO;

import com.example.SkyLine.enums.RatingEnum;

public class RateDTO {
    private int userId;
    private int targetId;
    private RatingEnum rate;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public RatingEnum getRate() {
        return this.rate;
    }

    public void setRate(RatingEnum rate) {
        this.rate = rate;
    }
    
}
