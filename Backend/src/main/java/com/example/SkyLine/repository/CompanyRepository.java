package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
