package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByMapLinkAndPriceBetweenAndEstateTypeEnum(
            String mapLink,
            Integer minPrice,
            Integer maxPrice,
            EstateTypeEnum estateType);
}
