package com.example.SkyLine.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "Admin")
public class Admin extends User{


}
