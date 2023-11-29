package com.example.skyline.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String password;
    private String first_name;
    private String last_name;
    private byte[] profile_photo;
    private String phone_number;
    private String account_type;
    private String email;



}


    // Getters and setters (omitted for brevity)

