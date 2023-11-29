package com.example.skyline.model;

import jakarta.persistence.GenerationType;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.persistence.Table;

@Entity(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String email;
    private String password;
    private byte[] profile_photo;
    private String phone_number;
    private String account_type;
    private Integer ver_code;
}

    // Getters and setters (omitted for brevity)

