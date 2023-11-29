package com.example.skyline.repo;

import com.example.skyline.model.Notification;
import com.example.skyline.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Integer> {
    // You can add custom query methods here if needed
    @Transactional
    @Modifying
    @Query("UPDATE Users n SET n.account_type = :newValue WHERE n.user_id = :UserId")
    void promoteToAdmin(@Param("UserId") int UserId, @Param("newValue") String newValue);


    @Query(value = "SELECT * FROM Users WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<User> getAllUsersByAccountType(@Param("Account_Type") String Account_Type);

    @Query(value = "SELECT user_id FROM Users WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<Integer> getSuperAdminsID(@Param("Account_Type") String Account_Type);

}
