package com.example.SkyLine.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PhotoTest {
    /**
     * Method under test: {@link Photo#Photo(String)}
     */
    @Test
    void testConstructor() {
        Photo actualPhoto = new Photo("https://example.org/example");
        assertEquals("https://example.org/example", actualPhoto.getPostPhotoURL());
        assertEquals(0, actualPhoto.getId());
    }
}
