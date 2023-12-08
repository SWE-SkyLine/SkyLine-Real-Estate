package com.example.SkyLine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "Company")
public class Company extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    public List<Client> clients =new ArrayList<>();
}

