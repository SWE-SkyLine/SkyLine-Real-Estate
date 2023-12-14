package com.example.SkyLine.service;

import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.PhotoRepository;
import com.example.SkyLine.repository.PostRepository;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostCreationService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public int createPost(Post post, MultipartFile[] photos, String postCreatorUID) {
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
        // save UID post creator
        User postCreator = userRepository.findUserById(Integer.valueOf(postCreatorUID));

        newPost.setClient(postCreator);

        postRepository.save(newPost);

        return postId;

    }

    public List<PostRetrievalDTO> PostToRetrievalEntity(List<Post> posts) throws MalformedURLException {
        List<PostRetrievalDTO> retrievalDTOS = new ArrayList<>();
        for (Post p : posts) {
            try {
                retrievalDTOS.add(new PostRetrievalDTO(p));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return retrievalDTOS;
    }

}
