package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.PostDTO;
import com.example.SkyLine.DTO.VerifyCodeRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {
    @PostMapping("/publish_post")
    public ResponseEntity<?> publishPost(@RequestBody PostDTO post){
        System.out.println("1");
        System.out.println(post);
       return new ResponseEntity<String>("Post Added", HttpStatus.OK);
    }
    @PostMapping("/publish")
    public int publishPost(){
        System.out.println("1");
        System.out.println();
        return 99;
       // return new ResponseEntity<String>("Post Added", HttpStatus.OK);
    }
    @PostMapping("/test")
    public int test(){
        return 1;
    }
}
