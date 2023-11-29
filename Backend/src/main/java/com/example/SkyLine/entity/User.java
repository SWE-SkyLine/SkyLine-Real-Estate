package com.example.SkyLine.entity;

import com.example.SkyLine.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Users")
@Setter
@Getter
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_type")
    private UserRoleEnum userRole;
    @Column(name = "email")
    private String email;
    private byte[] profile_photo;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "ver_code")
    private Integer ver_code;

   @Column(name ="first_name")
   private String firstName;

    @Column(name ="last_name")
    private String lastName;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVer_code(Integer ver_code) {
        this.ver_code = ver_code;
    }

    public Integer getVer_code() {
        return ver_code;
    }




}
