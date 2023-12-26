package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Company;
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
    public User findUserById(int userId);



    @Transactional
    @Modifying
    @Query("UPDATE User n SET n.userRole = :newValue WHERE n.id = :UserId")
    void promoteToAdmin(@Param("UserId") int UserId, @Param("newValue") UserRoleEnum userRoleEnum);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Company n SET n.clients = :newValue WHERE n.id = :CompanyId")
    void setCompany(@Param("CompanyId") int CompanyId, @Param("newValue") int ClientId);


    @Query(value = "SELECT * FROM User WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<User> getAllUsersByAccountType(@Param("Account_Type") UserRoleEnum userRoleEnum);
    @Query(value = "SELECT user_id FROM User WHERE account_type=:Account_Type ", nativeQuery = true)
    ArrayList<Integer> getSuperAdminsID(@Param("Account_Type") String Account_Type);
}
