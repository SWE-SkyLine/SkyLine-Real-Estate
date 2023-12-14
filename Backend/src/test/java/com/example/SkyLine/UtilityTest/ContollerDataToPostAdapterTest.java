package com.example.SkyLine.UtilityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.utility.ContollerDataToPostAdapter;

public class ContollerDataToPostAdapterTest {
    @Test
    void testContollerDataToPostAdapter(){
        Post post =  ContollerDataToPostAdapter.contollerDataToPost(
            "title test", "100", "True", "10", "description test",
            "HOUSE", "link test", "address test", "city test",
            "3", "2", "1");
        assertEquals(post.getTitle(), "title test");
        assertEquals(post.getPrice(), 100);
        assertEquals(post.getRent(), Boolean.TRUE);
        assertEquals(post.getArea(), 10);
        assertEquals(post.getDescription(), "description test");
        assertEquals(post.getEstateType(), EstateTypeEnum.HOUSE);
        assertEquals(post.getMapLink(), "link test");
        assertEquals(post.getAddress(), "address test");
        assertEquals(post.getCity(), "city test");
        assertEquals(post.getBathroom(), 2);
        assertEquals(post.getBedroom(), 3);
        assertEquals(post.getLevel(), 1);
        assertNotNull(post.getId());
        assertEquals(post.getExpiryDate().compareTo(post.getPublishDate()), 1);
    }
}
