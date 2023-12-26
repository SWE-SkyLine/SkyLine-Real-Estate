package com.example.SkyLine;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<User> userList = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(userList, result);
    }

    @Test
    void getAllUsersByAccountType() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        UserRoleEnum roleEnum = UserRoleEnum.CLIENT;
        when(userRepository.getAllUsersByAccountType(roleEnum)).thenReturn(userList);

        // Act
        ArrayList<User> result = userService.getAllUsersByAccountType(String.valueOf(roleEnum));

        // Assert
        assertEquals(userList, result);
    }

    @Test
    void getUserById() {
        // Arrange
        int userId = 1;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void getUserById_UserNotFound() {
        // Arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNull(result);
    }

    @Test
    void createUser() {
        // Arrange
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void promoteUser() {
        // Arrange
        int userId = 1;

        // Act
        userService.PromoteUser(String.valueOf(userId));

        // Assert
        verify(userRepository, times(1)).promoteToAdmin(userId, UserRoleEnum.ADMIN);
    }
}
