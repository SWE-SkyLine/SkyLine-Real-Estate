package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.repository.PhotoRepository;
import com.example.SkyLine.repository.PostRepository;

import com.example.SkyLine.service.PostCreationService;
import com.example.SkyLine.utility.ContollerDataToPostAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostCreationService postCreationService;
    //
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PostRepository postRepository;
    //

    @PostMapping("/publish_post")
    public ResponseEntity<?> publishPost(
            @RequestParam("title") String title,
            @RequestParam("price") String price,
            @RequestParam("isRent") String isRent,
            @RequestParam("area") String area,
            @RequestParam("description") String description,
            @RequestParam("estateType") String estateType,
            @RequestParam("mapLink") String mapLink,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("bedroom") String bedroom,
            @RequestParam("bathroom") String bathroom,
            @RequestParam("level") String level,
            @RequestPart("photos") MultipartFile[] photos
    ) {
        System.out.println("there is a request to publish a post");
        System.out.println(title + " " + price + " " + isRent + " " + area + " "
                + description + " " + estateType + " " + mapLink
                + " " + address + " " + city + " "
                + bedroom + " " + bathroom + " " + level + " " + photos.length);
        Post post = ContollerDataToPostAdapter.contollerDataToPost(
                title, price, isRent, area, description, estateType,
                mapLink, address, city, bedroom, bathroom, level
        );
        int postId = postCreationService.createPost(post, photos);

        return new ResponseEntity<String>("Post Added with ID : " + postId, HttpStatus.OK);

    }

    

    @GetMapping("/get_posts_with_photos")
    public ResponseEntity<List<PostRetrievalDTO>> getFullPosts() throws MalformedURLException {
        return new ResponseEntity<List<PostRetrievalDTO>>(postCreationService.PostToRetrievalEntity(postRepository.findAll()), HttpStatus.OK);
    }


  
}
