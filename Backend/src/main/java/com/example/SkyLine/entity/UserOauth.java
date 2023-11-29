package com.example.SkyLine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "oauth")
public class UserOauth {
    @Id
    @NonNull
    private String emailOauth;

    @NonNull
    public String getEmailOauth() {
        return emailOauth;
    }

    public void setEmailOauth(@NonNull String emailOauth) {
        this.emailOauth = emailOauth;
    }
}
