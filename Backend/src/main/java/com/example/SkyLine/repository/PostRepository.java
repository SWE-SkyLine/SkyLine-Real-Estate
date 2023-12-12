package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Post;

import com.example.SkyLine.enums.EstateTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAll();


}
