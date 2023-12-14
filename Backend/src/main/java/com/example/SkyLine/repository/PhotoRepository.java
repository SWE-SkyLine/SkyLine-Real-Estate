package com.example.SkyLine.repository;

import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Override
    List<Photo> findAll();
}
