package com.example.skyline.repo;

import com.example.skyline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // You can add custom query methods here if needed

    // get Super Admins from users table where attribute account_type=SuperAdmin



}
