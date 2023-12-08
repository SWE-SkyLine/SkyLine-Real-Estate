package com.example.SkyLine.controller;

import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/filter")
    public List<Post> getFilteredPosts(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) EstateTypeEnum estateType
    ) {
        return postService.getFilteredPosts(location, minPrice, maxPrice, estateType);
    }

    @GetMapping("/sort")
    public List<Post> getSortedPosts(
            @RequestParam String sortBy,
            @RequestParam String sortOrder
    ) {
        return postService.getSortedPosts(sortBy, sortOrder);
    }
}
