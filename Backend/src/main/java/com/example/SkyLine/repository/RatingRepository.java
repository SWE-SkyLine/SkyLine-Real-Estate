package com.example.SkyLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.SkyLine.entity.Rating;
import com.example.SkyLine.entity.RatingKey;

public interface RatingRepository extends JpaRepository<Rating, RatingKey> {
    // Find a Rating by its composite key
    Rating findByIdUserIdAndIdTargetId(int userId, int targetId);

    @Query("SELECT AVG(r.rate) FROM Rating r WHERE r.target.id = :targetId")
    Double findAverageRatingByTargetId(@Param("targetId") int targetId);

}
