package com.example.SkyLine.service;

import com.example.SkyLine.DTO.BidDTO;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.FilterData;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.repository.BidAuctionsRepository;
import com.example.SkyLine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findByClient_Id(userId);
    }

    public List<Post> getSortedPosts(String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        return postRepository.findAll(sort);
    }

    // New method for handling search requests
    public List<Post> search(String query) {
        // Perform search logic here, searching in both title and description
        return postRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    // New method for handling filter requests
    public List<Post> filter(FilterData filterData) {
        // Perform filter logic here, using priceFrom and priceTo

        System.out.println(filterData.isRent());
        return postRepository.findFilteredPosts(
                filterData.getPriceFrom(),
                filterData.getPriceTo(),
                filterData.getEstateType(),
                filterData.isRent());
    }

    // New method for handling sort requests
    public List<Post> sort(String sortBy, String sortOrder) {
        Sort sort;
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        } else {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        }
        return postRepository.findAll(sort);
    }

    public Post getPostById(Integer postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

}
