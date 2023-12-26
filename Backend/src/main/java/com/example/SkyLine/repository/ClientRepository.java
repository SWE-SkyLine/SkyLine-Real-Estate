package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientById(int responderId);
}
