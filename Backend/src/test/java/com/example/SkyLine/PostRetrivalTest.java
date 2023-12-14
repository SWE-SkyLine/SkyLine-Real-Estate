package com.example.SkyLine;

import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.EstateTypeEnum;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PostRetrivalTest {

    @Test
    public void testPostRetrievalDTO() {
        // Create a mock Post object for testing
        Post mockPost = new Post();
        mockPost.setId(1);
        mockPost.setPublishDate(new Date(System.currentTimeMillis()));
        mockPost.setExpiryDate(new Date(System.currentTimeMillis() + 86400000)); // 1 day from now
        mockPost.setTitle("Test Post");
        mockPost.setPrice(1000);
        mockPost.setRent(true);
        mockPost.setArea((short) 100);
        mockPost.setDescription("Test Description");
        mockPost.setEstateType(EstateTypeEnum.APARTMENT);
        mockPost.setBedroom((byte) 2);
        mockPost.setBathroom((byte) 1);
        mockPost.setLevel((byte) 2);
        mockPost.setMapLink("https://example.com/map");
        mockPost.setAddress("123 Test St");
        mockPost.setCity("Test City");
        mockPost.setClient(new User());

        // Instantiate the PostRetrievalDTO object
        PostRetrievalDTO postRetrievalDTO = null;
        try {
            postRetrievalDTO = new PostRetrievalDTO(mockPost);
        } catch (Exception e) {
            fail("Exception thrown while creating PostRetrievalDTO: " + e.getMessage());
        }

        // Check if the getters return non-null values
        assertNotNull(postRetrievalDTO);
        assertNotNull(postRetrievalDTO.getId());
        assertNotNull(postRetrievalDTO.getPublishDate());
        assertNotNull(postRetrievalDTO.getExpiryDate());
        assertNotNull(postRetrievalDTO.getTitle());
        assertNotNull(postRetrievalDTO.getPrice());
        assertNotNull(postRetrievalDTO.isRent());
        assertNotNull(postRetrievalDTO.getArea());
        assertNotNull(postRetrievalDTO.getDescription());
        assertNotNull(postRetrievalDTO.getEstateType());
        assertNotNull(postRetrievalDTO.getBedroom());
        assertNotNull(postRetrievalDTO.getBathroom());
        assertNotNull(postRetrievalDTO.getLevel());
        assertNotNull(postRetrievalDTO.getMapLink());
        assertNotNull(postRetrievalDTO.getAddress());
        assertNotNull(postRetrievalDTO.getCity());
        assertNotNull(postRetrievalDTO.getFullName());
    }
}
