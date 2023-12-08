package com.example.SkyLine.utility;

import com.example.SkyLine.repository.AdminRepository;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.CompanyRepository;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RepositoryFactory {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompanyRepository companyRepository;



    public JpaRepository repositoryFactory(String userType) {
        if (Objects.equals(userType, "ADMIN") || Objects.equals(userType, "SUPERADMIN")) return adminRepository;
        else if (Objects.equals(userType, "CLIENT") || Objects.equals(userType, "AGENT")) return clientRepository;
        else return companyRepository;

    }
}
