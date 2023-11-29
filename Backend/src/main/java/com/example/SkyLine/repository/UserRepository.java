package com.example.SkyLine.repository;

import com.example.SkyLine.entity.User;
import com.sun.jdi.IntegerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsUserByEmail(String email);
    public User findUserByEmail(String email);
}
