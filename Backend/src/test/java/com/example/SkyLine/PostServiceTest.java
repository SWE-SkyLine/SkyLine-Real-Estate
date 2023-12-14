package com.example.SkyLine;

import com.example.SkyLine.entity.FilterData;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.repository.PostRepository;
import com.example.SkyLine.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPosts() {
        // Mock data
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postRepository.findAll()).thenReturn(mockPosts);

        // Call the method under test
        List<Post> result = postService.getAllPosts();

        // Verify the interaction
        verify(postRepository, times(1)).findAll();

        // Verify the result
        assertEquals(mockPosts, result);
    }

    @Test
    public void testGetSortedPosts() {
        // Mock data
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postRepository.findAll(any(Sort.class))).thenReturn(mockPosts);

        // Call the method under test
        List<Post> result = postService.getSortedPosts("sortBy", "asc");

        // Verify the interaction
        verify(postRepository, times(1)).findAll(any(Sort.class));

        // Verify the result
        assertEquals(mockPosts, result);
    }

    @Test
    public void testSearch() {
        // Mock data
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase("query", "query"))
                .thenReturn(mockPosts);

        // Call the method under test
        List<Post> result = postService.search("query");

        // Verify the interaction
        verify(postRepository, times(1)).findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase("query", "query");

        // Verify the result
        assertEquals(mockPosts, result);
    }

    @Test
    public void testFilter() {
        // Mock data
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postRepository.findFilteredPosts(0, 1000, EstateTypeEnum.APARTMENT, true))
                .thenReturn(mockPosts);

        // Call the method under test
        List<Post> result = postService.filter(new FilterData(0, 1000, EstateTypeEnum.APARTMENT, true));

        // Verify the interaction
        verify(postRepository, times(1)).findFilteredPosts(0, 1000, EstateTypeEnum.APARTMENT, true);

        // Verify the result
        assertEquals(mockPosts, result);
    }

    @Test
    public void testSort() {
        // Mock data
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postRepository.findAll(any(Sort.class))).thenReturn(mockPosts);

        // Call the method under test
        List<Post> result = postService.sort("sortBy", "desc");

        // Verify the interaction
        verify(postRepository, times(1)).findAll(any(Sort.class));

        // Verify the result
        assertEquals(mockPosts, result);
    }
}
