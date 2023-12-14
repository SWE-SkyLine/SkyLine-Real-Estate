package com.example.SkyLine;

import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.controller.PostController;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.repository.PhotoRepository;
import com.example.SkyLine.repository.PostRepository;
import com.example.SkyLine.service.PostCreationService;
import com.example.SkyLine.service.PostService;
import com.example.SkyLine.utility.ContollerDataToPostAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PostControllerTest {

    @Mock
    private PostCreationService postCreationService;

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void publishPost() {
        // Arrange
        when(postCreationService.createPost(any(), any(), any())).thenReturn(1);
        MultipartFile[] photos = {}; // adjust as needed

        // Act
        ResponseEntity<?> response = postController.publishPost("title", "231", "isRent", "231",
                "description", "VILLA", "mapLink", "address", "city", "2",
                "2", "2","1234" ,photos);

        // Assert
        assertEquals(new ResponseEntity<>("Post Added with ID : 1", HttpStatus.OK), response);
    }
}