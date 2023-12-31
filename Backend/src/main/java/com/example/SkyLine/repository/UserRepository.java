package com.example.SkyLine.repository;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.sun.jdi.IntegerType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsUserByEmail(String email);
    User findByEmail(String email);
    public User findUserByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE Users n SET n.userRole = :newValue WHERE n.id = :UserId")
    void promoteToAdmin(@Param("UserId") int UserId, @Param("newValue") UserRoleEnum userRoleEnum);


    @Query(value = "SELECT * FROM Users WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<User> getAllUsersByAccountType(@Param("Account_Type") UserRoleEnum userRoleEnum);
    @Query(value = "SELECT user_id FROM Users WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<Integer> getSuperAdminsID(@Param("Account_Type") String Account_Type);
}
