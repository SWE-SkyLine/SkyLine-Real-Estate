package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByAreaAndEstateTypeEnumAndStatus(
            Integer area,
            EstateTypeEnum estateType,
            String status
    );

    @Query("SELECT p FROM Post p WHERE (:area IS NULL OR p.area = :area) " +
            "AND (:estateType IS NULL OR p.estateTypeEnum = :estateType) " +
            "AND (:status IS NULL OR p.status = :status)")
    List<Post> findFilteredPosts(
            @Param("area") Integer area,
            @Param("estateType") EstateTypeEnum estateType,
            @Param("status") String status
    );
}
