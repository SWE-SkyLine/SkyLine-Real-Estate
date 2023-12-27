package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Post;

import com.example.SkyLine.enums.EstateTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
        List<Post> findAll();

        List<Post> findByTitleContainingIgnoreCase(String query);

        // PostRepository.java
        List<Post> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

        @Query("SELECT p FROM Post p WHERE " +
                        "(:priceFrom IS NULL OR p.price >= :priceFrom) " +
                        "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
                        "AND (:estateType IS NULL OR p.estateType = :estateType) " +
                        "AND (:rent IS NULL OR p.rent = :rent)")
        List<Post> findFilteredPosts(
                        @Param("priceFrom") Integer priceFrom,
                        @Param("priceTo") Integer priceTo,
                        @Param("estateType") EstateTypeEnum estateType,
                        @Param("rent") Boolean rent);

        List<Post> findByClient_Id(Integer userId);

}
