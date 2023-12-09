package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Admin;
import com.example.SkyLine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
