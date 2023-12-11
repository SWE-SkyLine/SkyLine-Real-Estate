package com.example.SkyLine.service;

import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PostCreationService {
    @Autowired
    private PostRepository postRepository;
    public int createPost(Post post, MultipartFile[] photos) {
        Post newPost = postRepository.save(post);
        int postId = newPost.getId();
        try {
            for (MultipartFile file : photos) {
                Path path = Paths.get("uploads/" + postId + "-" + file.getOriginalFilename());
                File directory = path.toFile().getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                // Save the file
                file.transferTo(path);

                //save the file path into DB after saving the photo in the server
                newPost.getPhotos().add(new Photo(path.toString()));
            }

        } catch (IOException e) {
            e.getMessage();
        }
        postRepository.save(newPost);

        return postId;

    }
}
