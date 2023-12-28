package com.example.SkyLine.DTO;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class UserPromoteDTOTest {
    /**
     * Method under test:
     * {@link UserPromoteDTO#UserPromoteDTO(Integer, String, String, byte[], boolean)}
     */
    @Test
    void testConstructor() throws UnsupportedEncodingException {
        byte[] profile_photo = "AXAXAXAX".getBytes("UTF-8");
        UserPromoteDTO actualUserPromoteDTO = new UserPromoteDTO(1, "Jane", "Doe", profile_photo, true);

        assertEquals("Doe", actualUserPromoteDTO.getLastName());
        assertEquals("Jane", actualUserPromoteDTO.getFirstName());
        assertEquals(1, actualUserPromoteDTO.getId().intValue());
        assertTrue(actualUserPromoteDTO.isRequested());
        byte[] profilePhoto = actualUserPromoteDTO.getProfilePhoto();
        assertSame(profile_photo, profilePhoto);
        assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), profilePhoto);
    }
}
