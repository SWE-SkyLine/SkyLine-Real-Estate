package com.example.SkyLine.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EditPostDTOTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EditPostDTO#setAddress(String)}
     *   <li>{@link EditPostDTO#setArea(short)}
     *   <li>{@link EditPostDTO#setBedroom(byte)}
     *   <li>{@link EditPostDTO#setDescription(String)}
     *   <li>{@link EditPostDTO#setId(Integer)}
     *   <li>{@link EditPostDTO#setLevel(byte)}
     *   <li>{@link EditPostDTO#setMapLink(String)}
     *   <li>{@link EditPostDTO#setPrice(Integer)}
     *   <li>{@link EditPostDTO#setTitle(String)}
     *   <li>{@link EditPostDTO#getAddress()}
     *   <li>{@link EditPostDTO#getArea()}
     *   <li>{@link EditPostDTO#getBedroom()}
     *   <li>{@link EditPostDTO#getDescription()}
     *   <li>{@link EditPostDTO#getId()}
     *   <li>{@link EditPostDTO#getLevel()}
     *   <li>{@link EditPostDTO#getMapLink()}
     *   <li>{@link EditPostDTO#getPrice()}
     *   <li>{@link EditPostDTO#getTitle()}
     * </ul>
     */
    @Test
    void testSetAddress() {
        EditPostDTO editPostDTO = new EditPostDTO();
        editPostDTO.setAddress("42 Main St");
        editPostDTO.setArea((short) 1);
        editPostDTO.setBedroom((byte) 'A');
        editPostDTO.setDescription("The characteristics of someone or something");
        editPostDTO.setId(1);
        editPostDTO.setLevel((byte) 'A');
        editPostDTO.setMapLink("Map Link");
        editPostDTO.setPrice(1);
        editPostDTO.setTitle("Dr");
        String actualAddress = editPostDTO.getAddress();
        short actualArea = editPostDTO.getArea();
        byte actualBedroom = editPostDTO.getBedroom();
        String actualDescription = editPostDTO.getDescription();
        Integer actualId = editPostDTO.getId();
        byte actualLevel = editPostDTO.getLevel();
        String actualMapLink = editPostDTO.getMapLink();
        Integer actualPrice = editPostDTO.getPrice();
        assertEquals("42 Main St", actualAddress);
        assertEquals("Dr", editPostDTO.getTitle());
        assertEquals("Map Link", actualMapLink);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId.intValue());
        assertEquals(1, actualPrice.intValue());
        assertEquals((short) 1, actualArea);
        assertEquals('A', actualBedroom);
        assertEquals('A', actualLevel);
    }
}
