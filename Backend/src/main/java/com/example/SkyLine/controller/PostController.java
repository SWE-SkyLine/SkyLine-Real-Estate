package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.FilterData;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.repository.PhotoRepository;
import com.example.SkyLine.repository.PostRepository;

import com.example.SkyLine.service.PostCreationService;
import com.example.SkyLine.service.PostService;
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

        @Autowired
        private PostService postService;
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
                        @RequestPart("photos") MultipartFile[] photos) {
                System.out.println("there is a request to publish a post");
                System.out.println(title + " " + price + " " + isRent + " " + area + " "
                                + description + " " + estateType + " " + mapLink
                                + " " + address + " " + city + " "
                                + bedroom + " " + bathroom + " " + level + " " + photos.length);
                Post post = ContollerDataToPostAdapter.contollerDataToPost(
                                title, price, isRent, area, description, estateType,
                                mapLink, address, city, bedroom, bathroom, level);
                int postId = postCreationService.createPost(post, photos);

                return new ResponseEntity<String>("Post Added with ID : " + postId, HttpStatus.OK);

        }

        @GetMapping("/get_posts_with_photos")
        public ResponseEntity<List<PostRetrievalDTO>> getFullPosts(
                        @RequestParam(required = false) String searchQuery,
                        @RequestBody(required = false) FilterData filterData,
                        @RequestParam(required = false) String sortBy,
                        @RequestParam(required = false) String sortOrder) throws MalformedURLException {

                List<Post> posts;

                if (searchQuery != null) {
                        // Search logic
                        posts = postService.search(searchQuery);
                } else if (filterData != null) {
                        // Filter logic
                        posts = postService.filter(filterData);
                } else if (sortBy != null && sortOrder != null) {
                        // Sort logic
                        posts = postService.sort(sortBy, sortOrder);
                } else {
                        // Default: Get all posts
                        posts = postRepository.findAll();
                }

                // Convert to DTOs and return
                List<PostRetrievalDTO> retrievalDTOS = postCreationService.PostToRetrievalEntity(posts);
                return new ResponseEntity<>(retrievalDTOS, HttpStatus.OK);
        }

        // @GetMapping("/test")
        // public ResponseEntity<Resource> test() throws MalformedURLException {
        // Resource resource = new
        // UrlResource(Paths.get("Backend\\uploads\\52-cat.jpeg").toUri());
        // return new ResponseEntity<Resource>(resource, HttpStatus.OK);
        // }

        @GetMapping("/search")
        public ResponseEntity<List<Post>> search(@RequestParam String query) {
                List<Post> result = postService.search(query);
                return new ResponseEntity<>(result, HttpStatus.OK);
        }

        @PostMapping("/filter")
        public ResponseEntity<List<Post>> filter(@RequestBody FilterData filterData) {
                List<Post> result = postService.filter(filterData);
                return new ResponseEntity<>(result, HttpStatus.OK);
        }

        @GetMapping("/sort")
        public ResponseEntity<List<Post>> sort(@RequestParam String sortBy, @RequestParam String sortOrder) {
                List<Post> result = postService.sort(sortBy, sortOrder);
                return new ResponseEntity<>(result, HttpStatus.OK);
        }

}
