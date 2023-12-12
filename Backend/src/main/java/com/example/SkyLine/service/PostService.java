package com.example.SkyLine.service;

import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getFilteredPosts(
            String location,
            Integer minPrice,
            Integer maxPrice,
            Integer area,
            EstateTypeEnum estateType,
            String status
    ) {
        System.out.println("Location: " + location);
        System.out.println("Min Price: " + minPrice);
        System.out.println("Max Price: " + maxPrice);
        System.out.println("Area: " + area);
        System.out.println("Estate Type: " + estateType);
        System.out.println("Status: " + status);

        List<Post> result = postRepository.findFilteredPosts(area, estateType, status);


        System.out.println("Result: " + result);
        return result;
    }


    public List<Post> getSortedPosts(String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        return postRepository.findAll(sort);
    }
}
