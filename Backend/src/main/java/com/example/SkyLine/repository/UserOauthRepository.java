package com.example.SkyLine.repository;

import com.example.SkyLine.entity.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthRepository extends JpaRepository<UserOauth, String> {
}
