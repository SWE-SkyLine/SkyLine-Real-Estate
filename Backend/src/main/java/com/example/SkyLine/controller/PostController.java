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

    // test photo upload
    @GetMapping("/get_posts")
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/get_photo")
    public List<Photo> getPhoto() {
        return photoRepository.findAll();
    }

    @GetMapping("/get_posts_with_photos")
    public ResponseEntity<List<PostRetrievalDTO>> getFullPosts() throws MalformedURLException {
        return new ResponseEntity<List<PostRetrievalDTO>>(postCreationService.PostToRetrievalEntity(postRepository.findAll()), HttpStatus.OK);
    }

//    @GetMapping("/get_ss")
//    public ResponseEntity<?> getPhotoTest() throws MalformedURLException {
//        File file1 = new File("uploads/1-installing deb files.png");
//        Path path1 = Paths.get(file1.getAbsolutePath());
//
//        File file2 = new File("uploads/2-Screenshot from 2023-10-24 20-21-50.png");
//        Path path2 = Paths.get(file2.getAbsolutePath());
//
//        File file3 = new File("uploads/52-20011502_FFT_Output_(missing in the pdf).png");
//        Path path3 = Paths.get(file3.getAbsolutePath());
//        ByteArrayResource[] arr = new ByteArrayResource[3];
//        try {
//            arr[0] = new ByteArrayResource(Files.readAllBytes(path1));
//            arr[1] = new ByteArrayResource(Files.readAllBytes(path2));
//            arr[2] = new ByteArrayResource(Files.readAllBytes(path3));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(arr, HttpStatus.OK);
//    }

    //        ByteArrayResource resource = null;
//        try {
//            resource = new ByteArrayResource(Files.readAllBytes(path));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(resource, HttpStatus.OK);
//    }
//    @GetMapping("/get_k")
//    public ResponseEntity<?> getPhotoTest1() throws MalformedURLException {
//        List<byte[]> arr = new ArrayList<>();
//        List<int[]> arr1 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            arr1.add(new int[]{1, 2, 3});
//        }
//
//        return new ResponseEntity<>(arr1, HttpStatus.OK);
//    }
    @GetMapping("/get_m")
    public ResponseEntity<Resource> getPhotoTest2() throws MalformedURLException {
        Resource resource = new UrlResource(Paths.get("uploads/3-Screenshot from 2023-10-24 20-55-32.png").toUri());

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
