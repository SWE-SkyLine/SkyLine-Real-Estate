package com.example.SkyLine.utility;

import com.example.SkyLine.entity.Admin;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class UserFactory {
    public User userFactory(String userType){
        if (Objects.equals(userType, "ADMIN") || Objects.equals(userType, "SUPERADMIN")) return new Admin();
        else if (Objects.equals(userType, "CLIENT") || Objects.equals(userType, "AGENT")) return new Client();
        else return new Company();

    }
}
