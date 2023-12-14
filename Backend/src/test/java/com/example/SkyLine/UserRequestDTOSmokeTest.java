package com.example.SkyLine;

import com.example.SkyLine.DTO.UserRequestDTO;
import com.example.SkyLine.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestDTOSmokeTest {

    @Test
    public void testUserRequestDTO() {
        // Create a UserRequestDTO object
        UserRequestDTO userRequestDTO = new UserRequestDTO();

        // Set values using setters
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setEmail("john.doe@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setPhone_number("1234567890");
        userRequestDTO.setUserRole(UserRoleEnum.ADMIN);

        // Check if getters return the correct values
        assertEquals("John", userRequestDTO.getFirstName());
        assertEquals("Doe", userRequestDTO.getLastName());
        assertEquals("john.doe@example.com", userRequestDTO.getEmail());
        assertEquals("password123", userRequestDTO.getPassword());
        assertEquals("1234567890", userRequestDTO.getPhone_number());
        assertEquals(UserRoleEnum.ADMIN, userRequestDTO.getUserRole());
    }
}
