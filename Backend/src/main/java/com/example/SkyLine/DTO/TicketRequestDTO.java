package com.example.SkyLine.DTO;

import com.example.SkyLine.enums.TicketCategoryEnum;

public class TicketRequestDTO {
    private String email;
    private TicketCategoryEnum category;
    private String reported;
    private String message;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TicketCategoryEnum getCategory() {
        return this.category;
    }

    public void setCategory(TicketCategoryEnum category) {
        this.category = category;
    }

    public String getReported() {
        return this.reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    

}
