package com.example.SkyLine.entity;

import com.example.SkyLine.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType"
        , discriminatorType = DiscriminatorType.STRING)
@Table(name = "User")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type")
    private UserRoleEnum userRole;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "profile_photo")
    private byte[] profile_photo;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "ver_code")
    private Integer verificationCodeForgetPassword;

    @Column(name = "is_enable", columnDefinition = "boolean default false")
    private boolean isEnable;

}
