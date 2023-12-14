// package com.example.SkyLine;

// import com.example.SkyLine.DTO.PostRetrievalDTO;
// import com.example.SkyLine.entity.Photo;
// import com.example.SkyLine.entity.Post;
// import com.example.SkyLine.repository.PhotoRepository;
// import com.example.SkyLine.repository.PostRepository;
// import com.example.SkyLine.service.PostCreationService;
// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.mock.web.MockMultipartFile;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.net.MalformedURLException;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// public class PostCreationServiceTest {

//     @Mock
//     private PostRepository postRepository;

//     @Mock
//     private PhotoRepository photoRepository;

//     @InjectMocks
//     private PostCreationService postCreationService;

//     @Before
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }



//     @Test
//     public void testPostToRetrievalEntity() throws MalformedURLException {
//         Post post = new Post();
//         post.setId(1);

//         List<Post> posts = new ArrayList<>();
//         posts.add(post);

//         List<PostRetrievalDTO> retrievalDTOS = postCreationService.PostToRetrievalEntity(posts);

//         assertEquals(1, retrievalDTOS.size());
//         assertEquals(1, retrievalDTOS.get(0).getId());

//         // Verify that the constructor of PostRetrievalDTO is called for each post
//         // This is the correct verification for this test
//     }

// }
